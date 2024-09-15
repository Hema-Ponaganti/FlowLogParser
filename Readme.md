## FlowLogParser

FlowLogParser is a Java application designed to analyze network flow logs and generate statistical reports. It parses flow log entries, matches them against a lookup table, and produces output files with tag counts and port/protocol combination counts.

### Project structure and description
- src folder contains all the java files for the logic.

FlowLogParser/
├── src/main/java/com/illumio/flowlogparser/
│   ├── Application.java         # Main entry point. It orchestrates the entire flow log analysis process.
│   ├── FlowLogEntry.java        # Flow log entry representation
│   ├── LookupEntry.java         # Lookup table entry representation
│   ├── CustomFileReader.java    # Handles all file reading operations
│   ├── OutputWriter.java        # Results writer
│   └── TagMatcher.java          # Tag matching and counting logic. Uses the protocol map for resolving protocol names.
├── input/                       # Input files directory
├── output/                      # Output files directory
└── resources/
    └── protocol-numbers.txt     # Protocol numbers to names mapping


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

