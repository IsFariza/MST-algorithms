package org.example.Algorithms;

import org.example.Graph.Edge;
import org.example.Graph.Graph;
import org.example.OutputHandler.MSTResult;

import java.util.*;

public class PrimAlgorithm {
    public MSTResult primAlgorithm(Graph graph){
        long startTime = System.nanoTime();
        List<String> nodes = new ArrayList<>(graph.getNodes());
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Map<String, List<Edge>> adjacencyList = new HashMap<>();//map from node to list of incident edges

        //add edges to adjacency list
        for(Edge edge : edges){
            String from = edge.getFrom();
            String to = edge.getTo();
            int weight = edge.getWeight();

            //add edge from-to
            //if from is not a key in adjacencyList, put it with an empty array
            if(!adjacencyList.containsKey(from)){
                adjacencyList.put(from, new ArrayList<>());
            } adjacencyList.get(from).add(edge); //then add th edge to that array of from's node

            //add reverse edge (to-from), since it's undirected graph
            if(!adjacencyList.containsKey(to)) {
                adjacencyList.put(to, new ArrayList<>());
            }adjacencyList.get(to).add(new Edge(to, from, weight));
        }

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        //priority queue of edges ordered by their weight

        int totalWeight=0;
        int operationsCount=0;

        //start with first node
        String startNode = nodes.get(0);
        visited.add(startNode);
        operationsCount++;

        if(adjacencyList.containsKey(startNode)){
            //if startNode has incident edges, add them all to priority queue
            priorityQueue.addAll(adjacencyList.get(startNode));
            operationsCount+=adjacencyList.get(startNode).size(); //count each add
        }

        //iterate while there are edges and mst is not complete yet (edges must be = nodes-1)
        while(!priorityQueue.isEmpty() && mstEdges.size()<nodes.size()-1){
            Edge minEdge = priorityQueue.poll(); //get smallest edge
            operationsCount++;

            //if both start and end points visited, then this edge creates a cycle,so skip them
            if(visited.contains(minEdge.getFrom()) && visited.contains(minEdge.getTo())){
                operationsCount++;
                continue;
            }
            //add the smallest edge to mst
            mstEdges.add(minEdge);
            totalWeight+=minEdge.getWeight();
            operationsCount++;

            //new node which is not visited yet
             String unvisitedNode;
             //if from is visited, then new vertex is to
            if(visited.contains(minEdge.getFrom())){
                unvisitedNode = minEdge.getTo();
            } else{ //if from is not visited, then it is the new vertex
                unvisitedNode = minEdge.getFrom();
            }

            visited.add(unvisitedNode);
            operationsCount++;

            //if that new node is adjacency list, means if it has neighbours
            if(adjacencyList.containsKey(unvisitedNode)){
                //for each neighbour-edge of that node
                for (Edge edge : adjacencyList.get(unvisitedNode)){
                    //if the edge's to (ending node) isn't visited yet
                    if(!visited.contains(edge.getTo())){
                        priorityQueue.add(edge);
                        operationsCount++;
                    } //(if otherwise it's visited, then cycle will appear)
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTime = (endTime-startTime)/1000000.0; //to convert miliseconds

        return new MSTResult(mstEdges, totalWeight, operationsCount, executionTime, "Prim");

    }


// MY INITIAL IMPLEMENTATION WITH SIMPLE EDGE SCAN, NAIVE APPROACH, WORSE PERFORMANCE

//    public MSTResult primAlgorithm(Graph graph){
//        long startTime = System.nanoTime();
//
//        List<String> nodes = new ArrayList<>(graph.getNodes());
//        List<Edge> edges = new ArrayList<>(graph.getEdges());
//
//        //set quickly checks for the presence of unique element, better than array
//        //because it takes O(1) for contains, while array takes O(n)
//        Set<String> visited = new HashSet<>();
//        List<Edge> mstEdges = new ArrayList<>();
//
//        int totalWeight = 0;
//        int operationsCount = 0;
//
//        //prim's algorithm may start at any node, I chose the first
//        String startingNode = nodes.get(0);
//        visited.add(startingNode);
//
//        while(visited.size()<nodes.size()){
//            int minWeight = Integer.MAX_VALUE;
//            Edge minEdge = null;
//
//            for(Edge edge : edges){
//
//
//                //track visited nodes (u is starting, v is ending node)
//                boolean visitedU = visited.contains(edge.getFrom());
//                boolean visitedV = visited.contains(edge.getTo());
//                operationsCount+=2;
//                //check if one start visited and end is not yet (and vice versa) to avoid the loop
//                if ((visitedU && !visitedV) || (!visitedU && visitedV)){
//                    operationsCount++;
//                    if(edge.getWeight()<minWeight){ //update the minWeight and minEdge if found
//                        operationsCount++;
//                        minWeight = edge.getWeight();
//                        minEdge = edge;
//                    }
//                }
//            }
//            if (minEdge==null) break; //graph disconnected
//            mstEdges.add(minEdge);
//            totalWeight+=minEdge.getWeight();
//
//            visited.add(minEdge.getFrom()); //add both nodes to the edge
//            visited.add(minEdge.getTo());
//            operationsCount+=2;
//
//        }
//        long endTime = System.nanoTime();
//        double executionTime = (endTime-startTime)/1000000.0; //to convert miliseconds
//
//        return new MSTResult(mstEdges, totalWeight, operationsCount, executionTime, "Prim");
//    }
}
