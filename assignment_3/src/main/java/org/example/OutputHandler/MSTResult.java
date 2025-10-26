package org.example.OutputHandler;


import org.example.Graph.Edge;

import java.util.List;

public class MSTResult {
    private List<Edge> mstEdges;
    private int totalWeight;
    private int operationsCount;
    private double executionTime;
    private String algorithm;

    public MSTResult(List<Edge> edges, int totalWeight, int ops, double time, String name){
        this.mstEdges = edges;
        this.totalWeight = totalWeight;
        this.operationsCount = ops;
        this.executionTime = time;
        this.algorithm = name;
    }
    public int getTotalWeight(){
        return totalWeight;
    }
    public List<Edge> getEdges(){
        return mstEdges;
    }
    public double getExecutionTime(){
        return executionTime;
    }
    public int getOperationsCount(){
        return operationsCount;
    }
    public String getAlgorithmName(){
        return algorithm;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Algorithm: ").append(algorithm).append("\n");
        sb.append("Total cost: ").append(totalWeight).append("\n");
        sb.append("Operations cost: ").append(operationsCount).append("\n");
        sb.append("Execution time: ").append(executionTime).append(" ms\n");
        sb.append("Edges in MST:\n");

        if (mstEdges == null || mstEdges.isEmpty()) {
            sb.append("none\n");
        } else {
            for (Edge e : mstEdges) {
                sb.append(e + "\n");
            }
        }
        return sb.toString();
    }

    public static class OutputResults{
        private List<GraphResult> results;

        public OutputResults(List<GraphResult> res){
            this.results = res;
        }
    }
}
