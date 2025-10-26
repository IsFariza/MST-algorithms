package org.example.InputHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Graph.Graph;
import org.example.Graph.GraphsList;

import java.io.FileReader;
import java.util.List;

public class JsonInputReader {
    public static List<Graph> jsonReadInput(){
        try (FileReader reader = new FileReader("assignment_3/input.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            GraphsList graphs = gson.fromJson(reader, GraphsList.class);
            reader.close();
            return graphs.getGraphs();

        } catch(Exception e){
            System.out.println("Parsing error: " + e.getMessage());
        }
        return null;
    }
}

