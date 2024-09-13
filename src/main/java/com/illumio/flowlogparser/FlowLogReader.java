package main.java.com.illumio.flowlogparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowLogReader {
    public List<FlowLogEntry> parseFlowLogs(String filename) throws IOException {
        List<FlowLogEntry> flowLogEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 14) {
                    String dstPort = parts[5].trim();
                    String protocol = parts[7].trim();
                    flowLogEntries.add(new FlowLogEntry(dstPort, protocol));
                }
            }
        }

        return flowLogEntries;
    }
}