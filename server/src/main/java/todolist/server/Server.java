package todolist.server;

import todolist.common.Command;
import todolist.common.Connection;
import todolist.common.Packet;
import todolist.server.logging.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * the server class
 */
public final class Server {
    public static final Server instance = new Server();

    protected final TaskManager taskManager = new TaskManager();
    private final List<Connection> connections = new ArrayList<>();
    public static final Logger logger = new Logger(Server.class);
    private ServerSocket socket;

    private Configuration config = new Configuration(taskManager, "tasks.json");

    public void setAndEnableHttpPort(int port) {
        new Thread(() -> new JsonHttpServer(port).start()).start();
    }

    private Server() {
    }

    public void setPort(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void run() {
        config.restore();
        new Thread(this::waitForConnections, "connection listener").start();
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
                logger.info("A client disconnected");
                return true;
        }

        updateClients();

        if (packet.command != Command.GET) {
            new Thread(() -> config.save(), "config").start();
        }

        return false;
    }

    private void updateClients() {
        connections.removeIf(Connection::isClosed);
        for (var c : connections) c.send(taskManager.getTasks());
    }

}
