package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Connected implements Connection {
    TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    public void setConnection(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Try to connect when the connection already established.");
    }

    @Override
    public void disconnect() {
        this.connection.changeState(new Disconnected(this.connection));
    }

    @Override
    public void write(String data) {
        List<String> buffer = this.connection.getBuffer();
        buffer.add(data);
        this.connection.setBuffer(buffer);
    }
}
// END
