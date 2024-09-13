package main.java.com.illumio.flowlogparser;

public class LookupEntry {
    private String dstPort;
    private String protocol;
    private String tag;

    public LookupEntry(String dstPort, String protocol, String tag) {
        this.dstPort = dstPort;
        this.protocol = protocol;
        this.tag = tag;
    }

    public String getDstPort() {
        return dstPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getTag() {
        return tag;
    }
    
}
