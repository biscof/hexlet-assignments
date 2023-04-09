package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private String ip;
    private int port;
    private Connection state;
    List<String> buffer;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Connected(this);
        this.buffer = new ArrayList<>();
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public List<String> getBuffer() {
        return new ArrayList(buffer);
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = new ArrayList(buffer);
    }

    public void changeState(Connection state) {
        this.state = state;
    }

    public String getCurrentState() {
        return state.getCurrentState();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String data) {
        state.write(data);
    }
}
// END
