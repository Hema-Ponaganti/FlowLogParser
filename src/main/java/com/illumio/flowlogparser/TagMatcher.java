package main.java.com.illumio.flowlogparser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TagMatcher {
    private final List<LookupEntry> lookupEntries;
    private final Map<String, String> protocolMap;

    public TagMatcher(List<LookupEntry> lookupEntries, Map<String, String> protocolMap) {
        this.lookupEntries = lookupEntries;
        this.protocolMap = protocolMap;
    }

    public List<Map.Entry<String, Integer>> matchTags(List<FlowLogEntry> flowLogEntries) {
        Map<String, Integer> tagCounts = new HashMap<>();
        tagCounts.put("Untagged", 0);

        for (FlowLogEntry entry : flowLogEntries) {
            String tag = findMatchingTag(entry);
            tagCounts.merge(tag, 1, Integer::sum);
        }

        return tagCounts.entrySet().stream()
        .sorted(
            Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .thenComparing(Map.Entry::getKey)
        )
        .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Integer>> countPortProtocolCombinations(List<FlowLogEntry> flowLogEntries) {
        Map<String, Integer> portProtocolCounts = new HashMap<>();
    
        for (FlowLogEntry entry : flowLogEntries) {
            String key = entry.getDstPort() + "," + getProtocolName(entry.getProtocol());
            portProtocolCounts.merge(key, 1, Integer::sum);
        }
    
        return portProtocolCounts.entrySet().stream()
        .sorted(
            Comparator.<Map.Entry<String, Integer>>comparingInt(entry -> {
                String[] parts = entry.getKey().split(",");
                return Integer.parseInt(parts[0]); 
            })
            .thenComparing(entry -> entry.getKey().split(",")[1]) 
            .thenComparing(Map.Entry.<String, Integer>comparingByValue().reversed()) 
        )
        .collect(Collectors.toList());
    }

    private String findMatchingTag(FlowLogEntry flowLogEntry) {
        for (LookupEntry lookupEntry : lookupEntries) {
            if (lookupEntry.getDstPort().equalsIgnoreCase(flowLogEntry.getDstPort()) &&
                lookupEntry.getProtocol().equalsIgnoreCase(getProtocolName(flowLogEntry.getProtocol()))) {
                return lookupEntry.getTag();
            }
        }
        return "Untagged";
    }

    private String getProtocolName(String protocolNumber) {
        return protocolMap.getOrDefault(protocolNumber, "unknown");
    }
}