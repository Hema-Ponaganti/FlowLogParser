package main.java.com.illumio.flowlogparser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Application {
    private static final String INPUT_DIR = "input";
    private static final String OUTPUT_DIR = "output";

    public static void main(String[] args) {

        String flowLogFile = args[0];
        String lookupFile = args[1];
        try {
            // Parse lookup table
            LookupTableReader lookupTableParser = new LookupTableReader();
            List<LookupEntry> lookupEntries = lookupTableParser.parseLookupTable(INPUT_DIR + "/" + lookupFile);

            // Parse flow logs
            FlowLogReader flowLogParser = new FlowLogReader();
            List<FlowLogEntry> flowLogEntries = flowLogParser.parseFlowLogs(INPUT_DIR + "/" + flowLogFile);

            //Load protocol-numbers.txt
            ProtocolLoader protocolLoader = new ProtocolLoader();
            Map<String, String> protocolMap = protocolLoader.loadProtocolMap();

            // Match tags and count port/protocol combinations
            TagMatcher tagMatcher = new TagMatcher(lookupEntries, protocolMap);
            List<Map.Entry<String, Integer>> tagCounts = tagMatcher.matchTags(flowLogEntries);
            List<Map.Entry<String, Integer>> portProtocolCounts = tagMatcher.countPortProtocolCombinations(flowLogEntries);

            // Write output files
            OutputWriter outputWriter = new OutputWriter(OUTPUT_DIR);
            outputWriter.writeResults(tagCounts, portProtocolCounts);

            System.out.println("Analysis complete. Output files generated in the '" + OUTPUT_DIR + "' folder.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}