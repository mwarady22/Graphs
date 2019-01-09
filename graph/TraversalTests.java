package graph;

import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

public class TraversalTests {

    /** Order. */
    private ArrayList<Integer> traversalorder = new ArrayList<>();

    /** Vertex count for test graph 1. */
    static final int NV1 = 10;
    /** Edges for test graph 1. */
    static final Integer[][] E1 = {
            { 2, 5 }, { 2, 3 },
            { 2, 6 }, { 3, 7 }, { 3, 8 }, { 8, 1 }, { 8, 9 },
            { 1, 2 }, { 1, 3 }, { 1, 4 },
            { 8, 10 }, { 10, 7 } };

    /** Set up _G with NV vertices and the edges given in EDGES, whose
     *  entries are (V1 index, V2 index). */
    static Graph fillGraph(Graph G, int nv, Integer[][] edges) {
        for (int i = 1; i <= nv; i += 1) {
            int v = G.add();
            assertEquals("Bad vertex number returned by add", i, v);
        }
        for (Integer[] e : edges) {
            G.add(e[0], e[1]);
        }
        return G;
    }

    class Visitor extends DepthFirstTraversal {

        Visitor(Graph g) {
            super(g);
        }

        /** Add v to order of traversal. */
        protected boolean visit(int v) {
            traversalorder.add(v);
            return true;
        }
    }

    @Test
    public void depthfirstdirectedtraversal1() {
        Graph g = new DirectedGraph();
        fillGraph(g, NV1, E1);
        Visitor trav = new Visitor(g);
        trav.traverse(1);
        assertEquals("[1, 4, 3, 8, 10, 7, 9, 2, 6, 5]",
                traversalorder.toString());
    }

}

