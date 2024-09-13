package main.java.com.illumio.flowlogparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProtocolLoader {
    private static final Logger LOGGER = Logger.getLogger(ProtocolLoader.class.getName());
    private static final String PROTOCOL_FILE = "resources/protocol-numbers.txt";


    public Map<String, String> loadProtocolMap() {
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

