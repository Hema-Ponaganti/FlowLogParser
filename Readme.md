# FlowLogParser

FlowLogParser is a Java application designed to analyze network flow logs and generate statistical reports. It parses flow log entries, matches them against a lookup table, and produces output files with tag counts and port/protocol combination counts.

## Assumptions

1. The program supports only the default log format, not custom formats.
2. Only version 2 of the flow log format is supported.
3. The input files (flow logs and lookup table) are expected to be in the `input/` directory.
4. The output files will be generated in the `output/` directory.
5. The protocol numbers CSV file is expected to be in the `resources/` directory.


## File Descriptions

1. `Application.java`: The main class and entry point of the application. It orchestrates the entire flow log analysis process.
2. `FlowLogEntry.java`: Represents a single flow log entry.
3. `FlowLogReader.java`: Parses the flow log file into `FlowLogEntry` objects.
4. `LookupEntry.java`: Represents an entry in the lookup table.
5. `LookupTableReader.java`: Parses the lookup table file into `LookupEntry` objects.
6. `OutputWriter.java`: Handles writing the analysis results to output CSV files.
7. `ProtocolLoader.java`: Loads and interprets the protocol numbers .txt file, creating a map of protocol numbers to protocol names.
8. `TagMatcher.java`: Matches flow log entries against the lookup table and counts occurrences. Uses the protocol map for resolving protocol names.

## Compilation and Execution

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Java compiler (javac)

### Compilation

To compile the project, navigate to the root directory of the project and run:

```
javac -d out src/main/java/com/illumio/flowlogparser/*.java
```

This will compile all Java files.

### Execution

To run the program, use the following command from the root directory of the project:

```
java -cp out main.java.com.illumio.flowlogparser.Application <flow_log_file> <lookup_table_file>
```

Replace `<flow_log_file>` with the name of your flow log file and `<lookup_table_file>` with the name of your lookup table file. 
Both files should be located in the `input/` directory.

For example:

```
java -cp out main.java.com.illumio.flowlogparser.Application flow_logs_19.txt lookup_file_11.txt
```

## Input Files

1. `input/`: Contains the lookup table for tag matching and flow log entries to be analyzed.
2. `resources/protocol-numbers.txt`: Contains the mapping of protocol numbers to names.

## Output Files

1. `output/tag_counts.csv`: Contains the count of each tag found in the flow logs.
2. `output/port_protocol_counts.csv`: Contains the count of each port/protocol combination found in the flow logs.


## Dependencies

This project uses only standard Java libraries to minimize external dependencies. No additional libraries or packages are required.
