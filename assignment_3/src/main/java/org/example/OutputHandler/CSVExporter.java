package org.example.OutputHandler;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter implements AutoCloseable {
    private FileWriter writer;

    public CSVExporter(String path) throws IOException {
        writer = new FileWriter(path);
        writer.write("GraphID,Vertices,Edges,TotalCost,ExecutionTime_ms,Operations,Algorithm \n");
    }

    public void writeRow(int id, int vertices, int edges,
                         int totalCost, double time, int ops, String algorithm) throws IOException {
        writer.write(id+","+ vertices + "," + edges+  "," +totalCost+
                "," +time+ "," + ops + ","+algorithm+"\n");

    }
    public void close() throws IOException {
        writer.close();
    }
}