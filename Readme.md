## FlowLogParser

FlowLogParser is a Java application designed to analyze network flow logs and generate statistical reports. It parses flow log entries, matches them against a lookup table, and produces output files with tag counts and port/protocol combination counts.

### Java files description
- src folder contains all the java files for the logic.

    Application.java is main class and entry point of the application. It orchestrates the entire flow log analysis process.
    FlowLogEntry.java represents a single flow log entry.
    FlowLogReader.java parses the flow log file into `FlowLogEntry` objects.
    LookupEntry.java represents an entry in the lookup table.
    LookupTableReader.java parses the lookup table file into `LookupEntry` objects.
    OutputWriter.java handles writing the analysis results to output CSV files.
    ProtocolLoader.java loads and interprets the protocol numbers .txt file, creating a map of protocol numbers to protocol names.
    TagMatcher.java matches flow log entries against the lookup table and counts occurrences. Uses the protocol map for resolving protocol names.


### Run application using below commands

```
javac -d out src/main/java/com/illumio/flowlogparser/*.java
java -cp out main.java.com.illumio.flowlogparser.Application <flow_log_file> <lookup_table_file>
``` 
Example
```
java -cp out main.java.com.illumio.flowlogparser.Application flow_logs_19.txt lookup_file_11.txt
```
Both files should be located in the `input/` directory. 
Outputs files are generated to `output/` directory. `tag_counts.csv` conatins count of each tag found in the flow logs and `port_protocol_counts.csv` contains the count of each port/protocol combination found in the flow logs
The protocol numbers .txt file is expected to be in the `resources/` directory.

