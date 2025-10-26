package org.example.Graph;

public class Edge implements Comparable<Edge>{
    private String from;
    private String to;
    private int weight;

    public Edge(String start, String end, int weight){
        this.from = start;
        this.to = end;
        this.weight = weight;
    }

    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public int getWeight(){
        return weight;
    }


    @Override
    public int compareTo(Edge other){
        return Integer.compare(this.weight, other.weight);
    }
    @Override
    public String toString(){
        return from + " - " + to + " (" +weight + ")";
    }
}
