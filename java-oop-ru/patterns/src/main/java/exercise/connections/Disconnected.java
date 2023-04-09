package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        this.connection.changeState(new Connected(this.connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Try to disconnect when there is no connection.");
    }

    @Override
    public void write(String data) {
        System.out.println("Error! Try to write when there is no connection.");
    }
}
// END
