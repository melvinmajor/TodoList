package todolist.clients;

import todolist.common.Command;
import todolist.common.Connection;
import todolist.common.Query;
import todolist.common.Task;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class BaseClient implements Client {
    private int port;
    private Connection connection;
    protected List<Task> tasks;

    @Override
    public void run() {
        connect();
        connection.listen();
        sendQuery(new Query(Command.INIT, null));
    }

    private void connect() {
        try {
            String host = "localhost";
            Socket socket = new Socket(host, port);
            this.connection = new Connection<>(socket, this::onUpdate, this::onExit);
        } catch (IOException e) {
            onConnectionError();
        }
    }

    private <T> void onExit(Connection<T> ignored) {
        onExit();
    }

    protected void disconnect() {
        connection.send(new Query(Command.CLOSE, null));
    }

    protected void sendQuery(Query query) {
        connection.send(query);
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        return false;
    }

    // TODO cache ?
    protected int nextAvailableId() {
        Optional<Integer> max = tasks.stream().map(e -> e.id).max(Integer::compareTo);
        var next = max.orElse(0);
        return ++next;
    }
}
