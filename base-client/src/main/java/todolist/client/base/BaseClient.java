package todolist.client.base;

import todolist.common.Command;
import todolist.common.Connection;
import todolist.common.Packet;
import todolist.common.Task;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.*;

/**
 * a basic implementation of the Client interface
 */
public abstract class BaseClient implements Client {
    private int port;
    private Connection<Collection<Task>> connection;
    protected List<Task> tasks;

    private final Comparator<Task> comparator = Comparator
            .<Task, LocalDate>comparing(t -> t.dueDate, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(t -> t.importance, Comparator.nullsLast(Comparator.naturalOrder()));

    @Override
    public void run() {
        connect();
        connection.listen();
        sendPacket(new Packet(Command.INIT, null));
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
        connection.send(new Packet(Command.CLOSE, null));
    }

    protected void sendPacket(Packet packet) {
        connection.send(packet);
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.tasks.sort(comparator);
        return false;
    }

    // TODO cache ?
    protected int nextAvailableId() {
        Optional<Integer> max = tasks.stream().map(e -> e.id).max(Integer::compareTo);
        var next = max.orElse(0);
        return ++next;
    }
}
