package main.java.com.illumio.flowlogparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LookupTableReader {
    public List<LookupEntry> parseLookupTable(String filename) throws IOException {
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
    
}
