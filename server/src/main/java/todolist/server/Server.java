package todolist.server;

import todolist.common.Connection;
import todolist.common.Packet;
import todolist.server.Logging.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * the server class
 */
public class Server {
    private final TaskManager taskManager = new TaskManager();
    private final List<Connection> connections = new ArrayList<>();
    public static final Logger logger = new Logger(Server.class);
    private ServerSocket socket;

    public Server(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void run() {
        // TODO load from file

        new Thread(this::waitForConnections).start();
    }

    private void waitForConnections() {
        while (true) {
            try {
                var newSocket = socket.accept();
                var connection = new Connection<>(newSocket, this::handleAction, this.connections::remove);
                connections.add(connection);
                connection.listen();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private boolean handleAction(Packet packet) {

        var msg = "Received " + packet.command + " command";
        if (packet.task != null) msg += " with " + packet.task;
        logger.info(msg);
        switch (packet.command) {
            case REMOVE:
                taskManager.removeTask(packet.task);
                break;
            case EDIT:
            case ADD:
                taskManager.addOrEditTask(packet.task);
                break;
            case CLOSE:
                logger.info("Exiting the server");
                return true;
        }

        updateClients();

        return false;
    }

    private void updateClients() {
        connections.removeIf(Connection::isClosed);
        for (var c : connections) c.send(taskManager.getTasks());
    }

}
