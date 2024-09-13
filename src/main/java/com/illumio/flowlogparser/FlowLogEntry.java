package main.java.com.illumio.flowlogparser;

public class FlowLogEntry {
    private String dstPort;
    private String protocol;

    public FlowLogEntry(String dstPort, String protocol) {
        this.dstPort = dstPort;
        this.protocol = protocol;
    }

    public String getDstPort() {
        return dstPort;
    }

    public String getProtocol() {
        return protocol;
    }
}
