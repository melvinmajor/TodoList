package todolist.server;

import todolist.common.Command;
import todolist.common.Connection;
import todolist.common.Query;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final TaskManager taskManager = new TaskManager();
    private final List<Connection> connections = new ArrayList<>();
    private ServerSocket socket;

    public Server(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // TODO load from file

        waitForConnections();
    }

    private void waitForConnections() {
        Runnable r = () -> {
            while (true) {
                try {
                    var newSocket = socket.accept();
                    var connection = new Connection<>(newSocket, this::handleAction, this.connections::remove);
                    connections.add(connection);
                    connection.listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();
    }

    private boolean handleAction(Query query) {
        System.out.println("received command " + query.command);
        switch (query.command) {
            case REMOVE:
                taskManager.removeTask(query.task);
                break;
            case EDIT:
            case ADD:
                taskManager.addOrEditTask(query.task);
                break;
            case CLOSE:
                return true;
        }

        if (query.command != Command.INIT) {
            // TODO save to file
        }

        updateClients();

        return false;
    }

    private void updateClients() {
        connections.removeIf(Connection::isClosed);
        for (var c : connections) c.send(taskManager.getTasks());
    }

}
