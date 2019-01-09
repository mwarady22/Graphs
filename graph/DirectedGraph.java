package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Michaela Warady
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {

        if (contains(v)) {
            int deg = 0;
            for (int x = 1; x <= getyams().size(); x += 1) {
                if (getyams().get(x - 1).contains(v)) {
                    deg += 1;
                }
            }
            return deg;
        } else {
            return 0;
        }

    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        if (contains(v)) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int x = 1; x <= getyams().size(); x += 1) {
                if (getyams().get(x - 1).contains(v)) {
                    list.add(x);
                }
            }
            return Iteration.iteration(list);
        } else {
            return Iteration.iteration(new ArrayList<>());
        }
    }

}
