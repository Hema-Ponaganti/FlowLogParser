package main.java.com.illumio.flowlogparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomFileReader {

    private static final Logger LOGGER = Logger.getLogger(ProtocolLoader.class.getName());
    private static final String PROTOCOL_FILE = "resources/protocol-numbers.txt";

    public List<FlowLogEntry> readFlowLogs(String filename) throws IOException {
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

    public List<LookupEntry> readLookupTable(String filename) throws IOException {
        List<LookupEntry> lookupEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; 
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String dstPort = parts[0].trim();
                    String protocol = parts[1].toLowerCase();
                    String tag = parts[2];
                    lookupEntries.add(new LookupEntry(dstPort, protocol, tag));
                }
            }
        }

        return lookupEntries;
    }

    public Map<String, String> readProtocolMap() {
        Map<String, String> protocolMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PROTOCOL_FILE))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 2) {
                    try {
                        String protocolNumber = parts[0].trim();
                        String protocolName = parts[1].trim().toLowerCase();
                        protocolMap.put(protocolNumber, protocolName);
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Invalid protocol number: " + parts[0], e);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading protocol map", e);
        }

        return protocolMap;
    }
}