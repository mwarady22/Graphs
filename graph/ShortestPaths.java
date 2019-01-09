package graph;

/* See restrictions in Graph.java. */


import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Comparator;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Michaela Warady
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** Fringe priority queue. */
    private UpdatingQueue fringe;

    /** Holds priorities. */
    private double[] priorities;

    /** Returns priorities[]. */
    public double[] getPriArray() {
        return priorities;
    }

    /** Holds preceding vertices. */
    private int[] precedingvertices;

    /** Returns precedingvertices. */
    public int[] getpreverArray() {
        return precedingvertices;
    }


    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        priorities = new double[_G.maxVertex() + 1];
        for (int x = 1; x < priorities.length; x += 1) {
            setWeight(x, Integer.MAX_VALUE);
        }
        precedingvertices = new int[_G.maxVertex() + 1];
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        setWeight(_source, 0);
        Astar trav = new Astar(_G, new UpdatingQueue());
        trav.traverse(_source);
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        if (v != getDest()) {
            throw new IllegalArgumentException(
                    "given v is not destination vertex");
        }
        ArrayList<Integer> result = new ArrayList<>();
        int preceding = v;
        while (preceding != _source) {
            result.add(0, preceding);
            preceding = getPredecessor(preceding);
        }
        result.add(0, preceding);
        return result;
    }

    /** Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;


    /** Tree class for priority queue. */
    private class UpdatingQueue extends PriorityQueue<Integer> {

        /** Field Q. */
        private TreeSet<Integer> q;

        /** Constructor for UpdatingQueue. */
        private UpdatingQueue() {
            q = new TreeSet<Integer>(new CompareVertices());
        }

        @Override
        public boolean add(Integer v) {
            return q.add(v);
        }

        @Override
        public boolean remove(Object v) {
            return q.remove(v);
        }

        @Override
        public Integer poll() {
            return q.pollFirst();
        }

        @Override
        public boolean isEmpty() {
            return q.isEmpty();
        }

        @Override
        public int size() {
            return q.size();
        }

        @Override
        public boolean contains(Object v) {
            return q.contains(v);
        }

        @Override
        public void clear() {
            q.clear();
        }
    }

    /** Comparator class. */
    class CompareVertices implements Comparator<Integer> {
        @Override
        public int compare(Integer u, Integer v) {
            double uval = getWeight(u) + estimatedDistance(u);
            double vval =  getWeight(v) + estimatedDistance(v);
            if (uval > vval) {
                return 1;
            } else if (vval > uval) {
                return -1;
            } else {
                return u - v;
            }
        }

        @Override
        public boolean equals(Object o) {
            return ((o instanceof CompareVertices)
                    && ((Object) this).equals(o));
        }

        @Override
        public int hashCode() {
            return hashCode();
        }
    }

    /** Class that extends traversal. */
    private class Astar extends Traversal {

        /** Constructor takes in graph G and Queue<Integer> F. */
        private Astar(Graph G, UpdatingQueue f) {
            super(G, f);
        }

        /** Returns boolean successor V of U. */
        protected boolean processSuccessor(int u, int v) {
            double vpri = getWeight(u) + getWeight(u, v);
            if (getWeight(v) > vpri) {
                setPredecessor(v, u);
                setWeight(v, vpri);
                _fringe.remove((Object) u);
            }
            return !marked(v);
        }

        /** Overrides visit(). */
        @Override
        protected boolean visit(int v) {
            return v != _dest;
        }

    }

}
