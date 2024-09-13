package main.java.com.illumio.flowlogparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class OutputWriter {
    private static final String CSV_HEADER_TAG = "Tag,Count";
    private static final String CSV_HEADER_PORT_PROTOCOL = "Port,Protocol,Count";
    private static final String TAG_COUNTS_FILENAME = "tag_counts.csv";
    private static final String PORT_PROTOCOL_COUNTS_FILENAME = "port_protocol_counts.csv";

    private final String outputDir;

    public OutputWriter(String outputDir) {
        this.outputDir = outputDir;
    }

    public void writeResults(List<Map.Entry<String, Integer>> tagCounts, List<Map.Entry<String, Integer>> portProtocolCounts) throws IOException {
        writeToFile(outputDir + "/" + TAG_COUNTS_FILENAME, CSV_HEADER_TAG, tagCounts);
        writeToFile(outputDir + "/" + PORT_PROTOCOL_COUNTS_FILENAME, CSV_HEADER_PORT_PROTOCOL, portProtocolCounts);
    }

    private void writeToFile(String filename, String header, List<Map.Entry<String, Integer>> entries) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(header + "\n");
            for (Map.Entry<String, Integer> entry : entries) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }
}