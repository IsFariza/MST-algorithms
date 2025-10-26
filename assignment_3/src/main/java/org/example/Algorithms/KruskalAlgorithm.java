package org.example.Algorithms;

import org.example.Graph.Edge;
import org.example.Graph.Graph;
import org.example.OutputHandler.MSTResult;

import java.util.*;

public class KruskalAlgorithm {

    public MSTResult kruskalsAlgorithm(Graph graph){
        int operationsCount=0;
        long startTime = System.nanoTime();
        List<String> nodes = new ArrayList<>(graph.getNodes()); //not the nodes array itself but its copy
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        //map nodes names to indexes, because dsu uses integers, not strings
        Map<String, Integer> nodeToIndex = new HashMap<>();
        for(int i=0; i<nodes.size();i++){
            nodeToIndex.put(nodes.get(i), i);
        }

        Collections.sort(edges);
        operationsCount++;
        DSU dsu = new DSU(nodes.size());

        List<Edge> mstEdges = new ArrayList<>();
        int totalWeight =0;


        for(Edge edge : edges){
            int u = nodeToIndex.get(edge.getFrom()); //starting node
            int v = nodeToIndex.get((edge.getTo())); //ending node

            int rootU = dsu.find(u);
            int rootV = dsu.find(v);
            operationsCount+=2;

            if(rootV!=rootU){ //the roots must be distinct, means that the nodes don't belong to one set
                operationsCount++;
                mstEdges.add(edge);
                totalWeight+= edge.getWeight();

                dsu.union(u, v);
                operationsCount++;

                //end the algorithm when number of edges = number of nodes-1
                if(mstEdges.size()==nodes.size()-1) break;
            }

        }

        long endTime = System.nanoTime();
        double executionTime = (endTime-startTime)/1000000.0; //divide like that to convert into miliseconds

        return new MSTResult(mstEdges, totalWeight, operationsCount, executionTime, "Kruskal");
    }


}
