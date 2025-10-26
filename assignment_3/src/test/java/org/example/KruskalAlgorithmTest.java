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

import static org.junit.jupiter.api.Assertions.*;

public class KruskalAlgorithmTest {
    @Test
    void testSmallGraph(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("A", "C", 3),
                new Edge("C", "D", 4)
        );

        Graph graph = new Graph(1, nodes, edges);

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result = kruskal.kruskalsAlgorithm(graph);

        assertNotNull(result);
        assertEquals(7, result.getTotalWeight());
        assertEquals(3, result.getEdges().size());
        assertFalse(TestUtils.containsCycle(result.getEdges(), nodes));
        assertTrue(TestUtils.isConnected(result.getEdges(), nodes));
        assertTrue(TestUtils.hasValidEdgeNumber(result.getEdges(), nodes));
        assertTrue(result.getExecutionTime()>=0 && result.getOperationsCount()>=0);
    }
    @Test
    void testMediumGraph(){
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
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 3),
                new Edge("A", "C", 6),
                new Edge("B", "D", 4),
                new Edge("C", "E", 5),
                new Edge("D", "F", 7),
                new Edge("E", "G", 9),
                new Edge("F", "H", 8),
                new Edge("G", "I", 10),
                new Edge("H", "J", 6),
                new Edge("I", "K", 12),
                new Edge("J", "L", 11),
                new Edge("K", "M", 14),
                new Edge("L", "N", 4),
                new Edge("M", "O", 7),
                new Edge("A", "F", 5),
                new Edge("B", "G", 8),
                new Edge("C", "H", 7)
        );

        Graph graph = new Graph(2, nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.primAlgorithm(graph);

        assertNotNull(result);
        assertEquals(102, result.getTotalWeight());
        assertEquals(14, result.getEdges().size());
        assertFalse(TestUtils.containsCycle(result.getEdges(), nodes));
        assertTrue(TestUtils.isConnected(result.getEdges(), nodes));
        assertTrue(TestUtils.hasValidEdgeNumber(result.getEdges(), nodes));
        assertTrue(result.getExecutionTime()>=0 && result.getOperationsCount()>=0);
    }

    @Test
    void testLargeGraph(){
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
                new Edge("A", "F", 6),
                new Edge("B", "G", 5),
                new Edge("C", "H", 8)

        );

        Graph graph = new Graph(3, nodes, edges);

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.primAlgorithm(graph);

        assertNotNull(result);
        assertEquals(161, result.getTotalWeight());
        assertEquals(19, result.getEdges().size());
        assertFalse(TestUtils.containsCycle(result.getEdges(), nodes));
        assertTrue(TestUtils.isConnected(result.getEdges(), nodes));
        assertTrue(TestUtils.hasValidEdgeNumber(result.getEdges(), nodes));
        assertTrue(result.getExecutionTime()>=0 && result.getOperationsCount()>=0);
    }
    @Test
    void testConnectivity(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("C", "D", 4)
        );

        Graph graph = new Graph(4, nodes, edges);
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result = kruskal.kruskalsAlgorithm(graph);

        //must be disconnected (mst edges must be empty)
        assertTrue(result.getEdges().isEmpty() || !TestUtils.isConnected(result.getEdges(), nodes));
    }



    @Test
    void testPerformanceNegativity(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 10),
                new Edge("B", "C", 15),
                new Edge("A", "C", 20)
        );
        Graph graph = new Graph(5, nodes, edges);
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result = kruskal.kruskalsAlgorithm(graph);

        assertTrue(result.getExecutionTime()>=0);
        assertTrue(result.getOperationsCount()>=0);
    }

    @Test
    void testReproducibility(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 4)
        );
        Graph graph = new Graph(6, nodes, edges);
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result1 = kruskal.kruskalsAlgorithm(graph);
        MSTResult result2 = kruskal.kruskalsAlgorithm(graph);

        assertTrue(TestUtils.resultsReproducible(result1, result2));;
    }
    @Test
    void testContainsCycle(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 4)
        );

        Graph graph = new Graph(7, nodes, edges);
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result = kruskal.kruskalsAlgorithm(graph);

        assertFalse(TestUtils.containsCycle(result.getEdges(), nodes));
    }
    @Test
    void testValidEdgeNumber(){
        List<String> nodes = new ArrayList<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("A", "D", 4)
        );

        Graph graph = new Graph(8, nodes, edges);
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        MSTResult result = kruskal.kruskalsAlgorithm(graph);

        assertTrue(TestUtils.hasValidEdgeNumber(result.getEdges(), nodes));
    }


}