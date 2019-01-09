package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Michaela Warady
 */
public class GraphTest {


    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void testvertexsize() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.vertexSize());
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(4, g.vertexSize());
        g.remove(2);
        g.remove(1);
        assertEquals(2, g.vertexSize());
        g.remove(3);
        g.remove(4);
        assertEquals(0, g.vertexSize());

        UndirectedGraph h = new UndirectedGraph();
        assertEquals(0, h.vertexSize());
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        assertEquals(8, h.vertexSize());
        h.remove(1);
        h.remove(2);
        h.remove(3);
        h.remove(4);
        assertEquals(4, h.vertexSize());
        h.add();
        assertEquals(5, h.vertexSize());
        assertEquals(8, h.maxVertex());
        h.add();
        h.add();
        h.add();
        assertEquals(8, h.vertexSize());
        h.remove(1);
        h.remove(2);
        h.remove(3);
        h.remove(4);
        h.remove(5);
        h.remove(6);
        h.remove(7);
        h.remove(8);
        assertEquals(0, h.vertexSize());
    }

    @Test
    public void testmaxvertex() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.maxVertex());
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(4, g.maxVertex());
        g.remove(2);
        g.remove(1);
        assertEquals(4, g.maxVertex());
        g.add();
        assertEquals(4, g.maxVertex());
        g.remove(1);
        g.remove(4);
        assertEquals(3, g.maxVertex());
        g.remove(3);
        assertEquals(0, g.maxVertex());

        UndirectedGraph h = new UndirectedGraph();
        assertEquals(0, h.maxVertex());
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        h.add();
        assertEquals(8, h.maxVertex());
        h.remove(1);
        h.remove(2);
        h.remove(3);
        h.remove(4);
        assertEquals(8, h.maxVertex());
        h.remove(5);
        h.remove(6);
        h.remove(7);
        h.remove(8);
        assertEquals(0, h.vertexSize());
    }

    @Test
    public void testedgesize() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.edgeSize());
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 4);
        g.add(1, 3);
        g.add(4, 2);
        g.add(4, 1);
        assertEquals(4, g.edgeSize());
        g.add(4, 1);
        g.add(1, 4);
        assertEquals(5, g.edgeSize());
        g.add();
        assertEquals(5, g.edgeSize());
        g.add(4, 5);
        assertEquals(6, g.edgeSize());
        g.remove(1, 3);
        g.remove(4, 1);
        assertEquals(4, g.edgeSize());
        g.remove(2);
        assertEquals(2, g.edgeSize());
        g.remove(4, 5);
        assertEquals(1, g.edgeSize());
        g.add(4, 4);
        assertEquals(2, g.edgeSize());
        g.add(4, 4);
        assertEquals(2, g.edgeSize());
        UndirectedGraph h = new UndirectedGraph();
        assertEquals(0, h.edgeSize());
        h.add();
        h.add();
        h.add();
        h.add();
        h.add(2, 4);
        h.add(1, 3);
        h.add(4, 1);
        assertEquals(3, h.edgeSize());
        h.add(4, 1);
        assertEquals(3, h.edgeSize());
        h.add(4, 2);
        assertEquals(3, h.edgeSize());
        h.add();
        assertEquals(3, h.edgeSize());
        h.add(4, 5);
        assertEquals(4, h.edgeSize());
        h.remove(3, 1);
        h.remove(4, 1);
        assertEquals(2, h.edgeSize());
        h.remove(2);
        assertEquals(1, h.edgeSize());
        h.remove(4, 5);
        assertEquals(0, h.edgeSize());
        h.add(4, 4);
        assertEquals(1, h.edgeSize());
        h.add(4, 4);
        assertEquals(1, h.edgeSize());
    }

    @Test
    public void testoutdegree() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        assertEquals(0, g.outDegree(1));
        g.add();
        g.add(1, 2);
        assertEquals(1, g.outDegree(1));
        assertEquals(0, g.outDegree(2));
        g.add();
        g.add(1, 3);
        assertEquals(2, g.outDegree(1));
        assertEquals(0, g.outDegree(2));
        assertEquals(0, g.outDegree(3));
        g.remove(2);
        assertEquals(1, g.outDegree(1));
        assertEquals(0, g.outDegree(3));
        g.remove(3);
        assertEquals(0, g.outDegree(1));
        g.add(1, 1);
        assertEquals(1, g.outDegree(1));

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        assertEquals(0, h.outDegree(1));
        h.add();
        h.add(1, 2);
        assertEquals(1, h.outDegree(1));
        assertEquals(1, h.outDegree(2));
        h.add();
        h.add(1, 3);
        assertEquals(2, h.outDegree(1));
        assertEquals(1, h.outDegree(2));
        assertEquals(1, h.outDegree(3));
        h.remove(2);
        h.add(3, 1);
        assertEquals(1, h.outDegree(1));
        assertEquals(1, h.outDegree(3));
        h.remove(3);
        assertEquals(0, h.outDegree(1));
        h.add(1, 1);
        assertEquals(1, h.outDegree(1));

    }

    @Test
    public void testindegree() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        assertEquals(0, g.inDegree(1));
        g.add();
        g.add(1, 2);
        assertEquals(0, g.inDegree(1));
        assertEquals(1, g.inDegree(2));
        g.add();
        g.add(1, 3);
        assertEquals(0, g.inDegree(1));
        assertEquals(1, g.inDegree(2));
        assertEquals(1, g.inDegree(3));
        g.remove(2);
        assertEquals(0, g.inDegree(1));
        assertEquals(1, g.inDegree(3));
        g.remove(3);
        assertEquals(0, g.inDegree(1));
        g.add(1, 1);
        assertEquals(1, g.inDegree(1));

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        assertEquals(0, h.inDegree(1));
        h.add();
        h.add(1, 2);
        assertEquals(1, h.inDegree(1));
        assertEquals(1, h.inDegree(2));
        h.add();
        h.add(1, 3);
        assertEquals(2, h.inDegree(1));
        assertEquals(1, h.inDegree(2));
        assertEquals(1, h.inDegree(3));
        h.remove(2);
        h.add(3, 1);
        assertEquals(1, h.inDegree(1));
        assertEquals(1, h.inDegree(3));
        h.remove(3);
        assertEquals(0, h.inDegree(1));
        h.add(1, 1);
        assertEquals(1, h.inDegree(1));
    }

    @Test
    public void testcontainsvertex() {
        DirectedGraph g = new DirectedGraph();
        assertFalse(g.contains(1));
        g.add();
        assertTrue(g.contains(1));
        g.add();
        assertTrue(g.contains(2));
        g.add();
        assertTrue(g.contains(3));
        g.add();
        assertTrue(g.contains(4));
        g.remove(2);
        assertTrue(g.contains(1));
        assertFalse(g.contains(2));
        assertTrue(g.contains(3));
        assertTrue(g.contains(4));
        g.remove(3);
        assertTrue(g.contains(1));
        assertFalse(g.contains(2));
        assertFalse(g.contains(3));
        assertTrue(g.contains(4));
        g.add();
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertFalse(g.contains(3));
        assertTrue(g.contains(4));
        assertFalse(g.contains(0));
        assertFalse(g.contains(-1));
        assertFalse(g.contains(5));

        UndirectedGraph h = new UndirectedGraph();
        assertFalse(h.contains(1));
        h.add();
        assertTrue(h.contains(1));
        h.add();
        assertTrue(h.contains(2));
        h.add();
        assertTrue(h.contains(3));
        h.add();
        assertTrue(h.contains(4));
        h.remove(2);
        assertTrue(h.contains(1));
        assertFalse(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.remove(3);
        assertTrue(h.contains(1));
        assertFalse(h.contains(2));
        assertFalse(h.contains(3));
        assertTrue(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertTrue(h.contains(2));
        assertFalse(h.contains(3));
        assertTrue(h.contains(4));
        assertFalse(h.contains(0));
        assertFalse(h.contains(-1));
        assertFalse(h.contains(5));
    }

    @Test
    public void testcontainsedge() {
        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        assertFalse(h.contains(1, 1));
        assertFalse(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertFalse(h.contains(1, 4));
        assertFalse(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertFalse(h.contains(4, 1));
        h.add(1, 4);
        assertFalse(h.contains(1, 1));
        assertFalse(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertTrue(h.contains(1, 4));
        assertFalse(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertTrue(h.contains(4, 1));
        h.add(1, 2);
        assertFalse(h.contains(1, 1));
        assertTrue(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertTrue(h.contains(1, 4));
        assertTrue(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertTrue(h.contains(4, 1));
        h.add(1, 1);
        assertTrue(h.contains(1, 1));
        assertTrue(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertTrue(h.contains(1, 4));
        assertTrue(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertTrue(h.contains(4, 1));
        h.remove(1, 4);
        assertTrue(h.contains(1, 1));
        assertTrue(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertFalse(h.contains(1, 4));
        assertTrue(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertFalse(h.contains(4, 1));
        h.remove(1);
        assertFalse(h.contains(1, 1));
        assertFalse(h.contains(1, 2));
        assertFalse(h.contains(1, 3));
        assertFalse(h.contains(1, 4));
        assertFalse(h.contains(2, 1));
        assertFalse(h.contains(3, 1));
        assertFalse(h.contains(4, 1));
        h.add();
        h.add(1, 1);
        h.add(1, 2);
        h.add(3, 1);
        h.add(1, 4);
        h.remove(1);
    }

    @Test
    public void testaddandremovevertex() {
        UndirectedGraph h = new UndirectedGraph();
        assertFalse(h.contains(1));
        assertFalse(h.contains(2));
        assertFalse(h.contains(3));
        assertFalse(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertFalse(h.contains(2));
        assertFalse(h.contains(3));
        assertFalse(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertTrue(h.contains(2));
        assertFalse(h.contains(3));
        assertFalse(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertTrue(h.contains(2));
        assertTrue(h.contains(3));
        assertFalse(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertTrue(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.remove(2);
        assertTrue(h.contains(1));
        assertFalse(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertTrue(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.remove(1);
        assertFalse(h.contains(1));
        assertTrue(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.remove(2);
        assertFalse(h.contains(1));
        assertFalse(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
        h.add();
        assertTrue(h.contains(1));
        assertFalse(h.contains(2));
        assertTrue(h.contains(3));
        assertTrue(h.contains(4));
    }

    @Test
    public void testaddremoveedge() {
        DirectedGraph g = new DirectedGraph();
        assertFalse(g.contains(1));
        assertFalse(g.contains(2));
        assertFalse(g.contains(3));
        assertFalse(g.contains(4));
        assertFalse(g.contains(2, 1));
        assertFalse(g.contains(2, 2));
        assertFalse(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(1, 2));
        assertFalse(g.contains(3, 2));
        assertFalse(g.contains(4, 2));
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 2);
        g.add(2, 3);
        g.add(4, 2);
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(g.contains(3));
        assertTrue(g.contains(4));
        assertFalse(g.contains(2, 1));
        assertTrue(g.contains(2, 2));
        assertTrue(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(1, 2));
        assertFalse(g.contains(3, 2));
        assertTrue(g.contains(4, 2));
        g.add(3, 2);
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(g.contains(3));
        assertTrue(g.contains(4));
        assertFalse(g.contains(2, 1));
        assertTrue(g.contains(2, 2));
        assertTrue(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(1, 2));
        assertTrue(g.contains(3, 2));
        assertTrue(g.contains(4, 2));
        g.add(3, 2);
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(g.contains(3));
        assertTrue(g.contains(4));
        assertFalse(g.contains(2, 1));
        assertTrue(g.contains(2, 2));
        assertTrue(g.contains(2, 3));
        assertFalse(g.contains(2, 4));
        assertFalse(g.contains(1, 2));
        assertTrue(g.contains(3, 2));
        assertTrue(g.contains(4, 2));
        g.remove(3, 2);
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
    }

    @Test
    public void testvertices() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.remove(2);
        Iteration it = g.vertices();
        assertEquals(1, it.next());
        assertEquals(3, it.next());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
        boolean err = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            err = true;
        }
        assertTrue(err);

        DirectedGraph g0 = new DirectedGraph();
        g0.add();
        g0.add();
        g0.add();
        g0.add();
        g0.remove(1);
        g0.remove(2);
        g0.remove(3);
        g0.remove(4);
        Iteration it0 = g0.vertices();
        assertFalse(it0.hasNext());
        boolean err0 = false;
        try {
            it0.next();
        } catch (NoSuchElementException e) {
            err0 = true;
        }
        assertTrue(err0);

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.remove(2);
        Iteration ti = h.vertices();
        assertEquals(1, ti.next());
        assertEquals(3, ti.next());
        assertEquals(4, ti.next());
        assertFalse(ti.hasNext());
        boolean rre = false;
        try {
            ti.next();
        } catch (NoSuchElementException e) {
            rre = true;
        }
        assertTrue(rre);

    }

    @Test
    public void testsuccessors() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.remove(2);
        g.add(4, 1);
        g.add(4, 3);
        g.add(4, 4);
        g.add(1, 4);
        g.add(1, 3);
        Iteration it = g.successors(4);
        ArrayList<Integer> list = new ArrayList<>();
        list.add((Integer) it.next());
        list.add((Integer) it.next());
        list.add((Integer) it.next());
        assertFalse(it.hasNext());
        boolean err = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            err = true;
        }
        assertTrue(err);
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.remove(2);
        h.add(4, 3);
        h.add(4, 4);
        h.add(1, 4);
        h.add(1, 3);
        Iteration ti = h.successors(4);
        ArrayList<Integer> tsil = new ArrayList<>();
        tsil.add((Integer) ti.next());
        tsil.add((Integer) ti.next());
        tsil.add((Integer) ti.next());
        assertFalse(ti.hasNext());
        boolean rre = false;
        try {
            ti.next();
        } catch (NoSuchElementException e) {
            rre = true;
        }
        assertTrue(rre);
        assertEquals(3, tsil.size());
        assertTrue(tsil.contains(1));
        assertTrue(tsil.contains(3));
        assertTrue(tsil.contains(4));

    }

    @Test
    public void testpredecessors() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.remove(2);
        g.add(1, 4);
        g.add(3, 4);
        g.add(4, 3);
        g.add(4, 4);

        Iteration it = g.predecessors(4);
        ArrayList<Integer> list = new ArrayList<>();
        list.add((Integer) it.next());
        list.add((Integer) it.next());
        list.add((Integer) it.next());
        assertFalse(it.hasNext());
        boolean err = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            err = true;
        }
        assertTrue(err);
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));


        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.remove(2);
        h.add(1, 4);
        h.add(3, 4);
        h.add(4, 3);
        h.add(4, 4);
        Iteration ti = h.predecessors(4);
        ArrayList<Integer> tsil = new ArrayList<>();
        tsil.add((Integer) ti.next());
        tsil.add((Integer) ti.next());
        tsil.add((Integer) ti.next());
        assertFalse(ti.hasNext());
        boolean rre = false;
        try {
            ti.next();
        } catch (NoSuchElementException e) {
            rre = true;
        }
        assertTrue(rre);
        assertEquals(3, tsil.size());
        assertTrue(tsil.contains(1));
        assertTrue(tsil.contains(3));
        assertTrue(tsil.contains(4));
    }

    @Test
    public void testedges() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.remove(2);
        g.add(1, 4);
        g.add(3, 4);
        g.add(4, 3);
        g.add(4, 4);

        Iteration it = g.edges();
        int[] n = (int[]) it.next();
        assertEquals(1, n[0]);
        assertEquals(4, n[1]);
        n = (int[]) it.next();
        assertEquals(3, n[0]);
        assertEquals(4, n[1]);
        n = (int[]) it.next();
        assertEquals(4, n[0]);
        assertEquals(3, n[1]);
        n = (int[]) it.next();
        assertEquals(4, n[0]);
        assertEquals(4, n[1]);
        assertFalse(it.hasNext());
        boolean err = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            err = true;
        }
        assertTrue(err);

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add();
        h.add();
        h.remove(2);
        h.add(1, 4);
        h.add(3, 4);
        h.add(4, 3);
        h.add(4, 4);

        Iteration ti = h.edges();
        int[] m = (int[]) ti.next();
        assertEquals(1, m[0]);
        assertEquals(4, m[1]);
        m = (int[]) ti.next();
        assertEquals(3, m[0]);
        assertEquals(4, m[1]);
        m = (int[]) ti.next();
        assertEquals(4, m[0]);
        assertEquals(4, m[1]);



    }

    @Test
    public void testcheckmyvertex() {
        DirectedGraph g = new DirectedGraph();
        boolean errorthrowndirected = false;
        try {
            g.checkMyVertex(1);
        } catch (IllegalArgumentException e) {
            errorthrowndirected = true;
        }
        assertTrue(errorthrowndirected);

        UndirectedGraph h = new UndirectedGraph();
        boolean errorthrownundirected = false;
        try {
            g.checkMyVertex(1);
        } catch (IllegalArgumentException e) {
            errorthrownundirected = true;
        }
        assertTrue(errorthrownundirected);

    }

    @Test
    public void testedgeid() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(2, 1);
        assertNotEquals(g.edgeId(1, 2), g.edgeId(2, 1));

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 1);
        assertEquals(h.edgeId(1, 2), h.edgeId(2, 1));
    }

}
