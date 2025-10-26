package org.example.OutputHandler;

public class GraphResult {
    private int graphID;

    private InputStats inputStats;
    private MSTResult prim;
    private MSTResult kruskal;

    public GraphResult(int id, int vertices, int edges, MSTResult prim, MSTResult kruskal){
        this.graphID = id;
        this.inputStats = new InputStats(vertices, edges);
        this.prim = prim;
        this.kruskal = kruskal;
    }
    public int getGraphID(){return graphID;};
    public InputStats getInputStats(){return inputStats;}
    public MSTResult getPrim(){return prim;}
    public MSTResult getKruskal(){return kruskal;}

    public static class InputStats{
        private int vertices;
        private int edges;
        public InputStats(int vertices, int edges){
            this.vertices = vertices;
            this.edges = edges;
        }

        public int getVertices(){return vertices;}
        public int getEdges(){return edges;}
    }
}
