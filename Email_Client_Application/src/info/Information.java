package info;
// Elif Su Tuncel
public class Information {
    
    private int receivePort; 
    private String toReceive; 
    private int sendPort; 
    private String toSend; 
    private String hostAddress;
    private String password; 
    private String name;
    
    private final static Information INSTANCE = new Information();
    
    public Information(){
        
    }
    
    public static Information getInstance(){
        return INSTANCE;
    }

    public int getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(int receivePort) {
        this.receivePort = receivePort;
    }

    public String getToReceive() {
        return toReceive;
    }

    public void setToReceive(String toReceive) {
        this.toReceive = toReceive;
    }
    public int getSendPort() {
        return sendPort;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    } 
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
}
