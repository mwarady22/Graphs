package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.PriorityQueue;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Michaela Warady
 */
abstract class GraphObj extends Graph {

    /** Holds vertices. */
    private ArrayList<Integer> vertices;

    /** Holds loop. */
    private int loops;

    /** Holds removed and not replaced vertices. */
    private PriorityQueue<Integer> removedvertices;

    /** Holds yams (which means neighbors but makes sense
     * if you're me or my yams). */
    private ArrayList<ArrayList<Integer>> yams;

    /** Returns yams. */
    public ArrayList<ArrayList<Integer>> getyams() {
        return yams;
    }

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<>();
        removedvertices = new PriorityQueue<>();
        yams = new ArrayList<>();
        loops = 0;

    }

    @Override
    public int vertexSize() {

        return vertices.size() - removedvertices.size();
    }

    @Override
    public int maxVertex() {
        if (vertexSize() == 0) {
            return 0;
        } else {
            for (int x = vertices.size() - 1; x >= 0; x -= 1) {
                int curr = vertices.get(x);
                if (curr > 0) {
                    return curr;
                }
            }
            return 0;
        }
    }

    @Override
    public int edgeSize() {
        int total = 0;
        for (ArrayList<Integer> list : yams) {
            total += list.size();
        }
        if (isDirected()) {
            return total;
        } else {
            return (total + loops) / 2;
        }
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!vertices.contains(v)) {
            return 0;
        } else {
            return yams.get(v - 1).size();
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return ((u > 0) && (u <= vertices.size())
                && (vertices.get(u - 1) == u));
    }

    @Override
    public boolean contains(int u, int v) {
        if (!contains(u) || !contains(v)) {
            return false;
        }
        return yams.get(u - 1).contains(v);
    }

    @Override
    public int add() {
        int nextnumber;
        if (!removedvertices.isEmpty()) {
            nextnumber = removedvertices.poll();
            vertices.set(nextnumber - 1, nextnumber);
        } else {
            nextnumber = maxVertex() + 1;
            vertices.add(nextnumber);
            yams.add(new ArrayList<>());
        }
        return nextnumber;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (!contains(u, v)) {
            if (!isDirected() && u != v) {
                yams.get(v - 1).add(u);
            }
            yams.get(u - 1).add(v);
            if (u == v) {
                loops += 1;
            }
        }
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        checkMyVertex(v);
        if (isDirected()) {
            for (int x = 1; x <= yams.size(); x += 1) {
                ArrayList<Integer> yam = yams.get(x - 1);
                if (yam.contains(v)) {
                    if (x == v) {
                        loops -= 1;
                    }
                    yam.remove((Object) v);
                }
            }
        } else {
            for (int u : yams.get(v - 1)) {
                if (v != u) {
                    yams.get(u - 1).remove((Object) v);
                }
            }
        }
        vertices.set(v - 1, 0);
        yams.set(v - 1, new ArrayList<>());
        removedvertices.add(v);
    }

    @Override
    public void remove(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (u == v) {
            loops -= 1;
        }
        if (!isDirected()) {
            yams.get(v - 1).remove((Object) u);
        }
        yams.get(u - 1).remove((Object) v);
    }

    @Override
    public Iteration<Integer> vertices() {
        ArrayList<Integer> nozeroes = new ArrayList<>();
        for (int x : vertices) {
            if (x != 0) {
                nozeroes.add(x);
            }
        }
        return Iteration.iteration(nozeroes);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        if (contains(v)) {
            return Iteration.iteration(yams.get(v - 1));
        } else {
            return Iteration.iteration(new ArrayList<>());
        }
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edgelist = new ArrayList<>();
        if (isDirected()) {
            for (int x = 1; x <= yams.size(); x += 1) {
                for (int y : yams.get(x - 1)) {
                    edgelist.add(new int[]{x, y});
                }
            }
        } else {
            for (int x = 1; x <= yams.size(); x += 1) {
                for (int y : yams.get(x - 1)) {
                    if (x <= y) {
                        edgelist.add(new int[]{x, y});
                    }
                }
            }
        }
        return Iteration.iteration(edgelist);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not in this graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected()) {
            if (u > v) {
                int w = u;
                u = v;
                v = w;
            }
        }
        return ((u + v) * (u + v + 1) / 2) + v;
    }



}
