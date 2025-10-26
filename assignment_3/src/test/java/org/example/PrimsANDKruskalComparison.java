package org.example;

import org.example.Algorithms.KruskalAlgorithm;
import org.example.Algorithms.PrimAlgorithm;
import org.example.Graph.Edge;
import org.example.Graph.Graph;
import org.example.OutputHandler.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimsANDKruskalComparison {
    @Test
    void testMSTCostEquality(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        nodes.add("E");
        nodes.add("F");
        nodes.add("G");
        nodes.add("H");
        nodes.add("I");
        nodes.add("J");
        nodes.add("K");
        nodes.add("L");
        nodes.add("M");
        nodes.add("N");
        nodes.add("O");
        nodes.add("P");
        nodes.add("Q");
        nodes.add("R");
        nodes.add("S");
        nodes.add("T");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 5),
                new Edge("B", "C", 7),
                new Edge("C", "D", 8),
                new Edge("D", "E", 9),
                new Edge("E", "F", 10),
                new Edge("F", "G", 4),
                new Edge("G", "H", 6),
                new Edge("H", "I", 7),
                new Edge("I", "J", 5),
                new Edge("J", "K", 12),
                new Edge("K", "L", 11),
                new Edge("L", "M", 8),
                new Edge("M", "N", 9),
                new Edge("N", "O", 13),
                new Edge("O", "P", 14),
                new Edge("P", "Q", 7),
                new Edge("Q", "R", 10),
                new Edge("R", "S", 12),
                new Edge("S", "T", 9),
                new Edge("A", "F", 15),
                new Edge("C", "H", 6),
                new Edge("D", "K", 10),
                new Edge("B", "L", 14),
                new Edge("E", "J", 11),
                new Edge("G", "M", 7),
                new Edge("I", "N", 12),
                new Edge("O", "R", 8),
                new Edge("P", "S", 9),
                new Edge("Q", "T", 5)
        );
        Graph graph = new Graph(1, nodes, edges);

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult kruskalRes = kruskal.kruskalsAlgorithm(graph);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult primRes = prim.primAlgorithm(graph);

        assertTrue(TestUtils.compareMSTCost(kruskalRes, primRes));

    }
}
