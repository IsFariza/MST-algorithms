package org.example.OutputHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Algorithms.KruskalAlgorithm;
import org.example.Algorithms.PrimAlgorithm;
import org.example.Graph.Graph;

import org.example.InputHandler.JsonInputReader;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonOutputWriter {

    public static void jsonWriteOutput(){
        try {
            List<Graph> graphs = JsonInputReader.jsonReadInput();
            List<GraphResult> results = new ArrayList<>();

            PrimAlgorithm prim = new PrimAlgorithm();
            KruskalAlgorithm kruskal = new KruskalAlgorithm();

            for (Graph graph : graphs) {
                System.out.println("Graph " + graph.getId());
                MSTResult kruskalResult = kruskal.kruskalsAlgorithm(graph);
                MSTResult primResult = prim.primAlgorithm(graph);

                System.out.println(kruskalResult);
                System.out.println(primResult);
                GraphResult graphResult = new GraphResult(
                        graph.getId(),
                        graph.getNodes().size(),
                        graph.getEdges().size(),
                        primResult,
                        kruskalResult
                );
                results.add(graphResult);

            }

            //records results into output.json file
            MSTResult.OutputResults output = new MSTResult.OutputResults(results);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter("output.json")) {
                gson.toJson(output, writer);
            }

            //also saves the results into output.csv file
            try(CSVExporter csv = new CSVExporter("output.csv")){
                for(GraphResult gr : results){
                    csv.writeRow(gr.getGraphID(), gr.getInputStats().getVertices(), gr.getInputStats().getEdges(),
                            gr.getKruskal().getTotalWeight(), gr.getKruskal().getExecutionTime(),
                            gr.getKruskal().getOperationsCount(), gr.getKruskal().getAlgorithmName());
                    csv.writeRow(gr.getGraphID(), gr.getInputStats().getVertices(), gr.getInputStats().getEdges(),
                            gr.getPrim().getTotalWeight(), gr.getPrim().getExecutionTime(),
                            gr.getPrim().getOperationsCount(), gr.getPrim().getAlgorithmName());



                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}
