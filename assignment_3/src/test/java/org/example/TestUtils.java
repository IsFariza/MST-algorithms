package org.example;

import org.example.Algorithms.DSU;
import org.example.Graph.Edge;
import org.example.OutputHandler.MSTResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static boolean containsCycle(List<Edge> edges, List<String> nodes){
        //convert nodes to indexes (string to int), because dsu works with integers
        Map<String, Integer> nodeToIndex = new HashMap<>();
        for(int i=0;i<nodes.size(); i++){
            nodeToIndex.put(nodes.get(i), i);
        }

        DSU dsu = new DSU(nodes.size());
        for(Edge edge : edges){
            int u = nodeToIndex.get(edge.getFrom());
            int v = nodeToIndex.get(edge.getTo());
            int rootU = dsu.find(u);
            int rootV = dsu.find(v);

            if(rootV==rootU) return true;

            dsu.union(u,v);
        }
        return false;
    }

    public static boolean isConnected(List<Edge> edges, List<String> nodes){
        if(nodes.isEmpty()) return true;

        DSU dsu = new DSU(nodes.size());
        Map<String, Integer> nodeToIndex = new HashMap<>();
        for(int i=0;i<nodes.size(); i++){
            nodeToIndex.put(nodes.get(i), i);
        }
        for(Edge edge : edges){
            int u = nodeToIndex.get(edge.getFrom());
            int v = nodeToIndex.get(edge.getTo());
            dsu.union(u, v);
        }

        int root = dsu.find(0);
        for(int i=1;i<nodes.size();i++){
            if(dsu.find(i)!=root) return false;
        }

        return true;
    }



    public static boolean compareMSTCost(MSTResult kruskal, MSTResult prim){
        return kruskal.getTotalWeight()==prim.getTotalWeight();
    }

    public static boolean hasValidEdgeNumber(List<Edge> edges, List<String> nodes){
        return edges.size()== nodes.size()-1;

    }

    public static boolean resultsReproducible(MSTResult res1, MSTResult res2){
        return res1.getTotalWeight()==res2.getTotalWeight() &&
                res1.getOperationsCount()==res2.getOperationsCount();
    }
}
