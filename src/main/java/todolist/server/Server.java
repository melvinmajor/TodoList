package todolist.server;

import todolist.common.Connection;
import todolist.common.Query;
import todolist.common.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public final TaskManager taskManager = new TaskManager();
    private final List<Connection> connections = new ArrayList<>();
    private ServerSocket socket;
    private int port;

    public Server(int port) {
        this.port = port;

        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        // TODO load from file

        while (true) {
            try {
                var server = socket.accept();


                var in = new ObjectInputStream(server.getInputStream());
                var action = (Query) in.readObject();

                handleAction(action, new ObjectOutputStream(server.getOutputStream()));

                // server.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAction(Query query, ObjectOutputStream out) {
        System.out.println("received command " + query.command);
        switch (query.command) {
            case REMOVE:
                taskManager.removeTask(query.task);
                break;
            case EDIT:
            case ADD:
                taskManager.addOrEditTask(query.task);
                break;
        }

        if (query.command != Command.INIT) {
            // TODO save to file
        }

        // return updated tasks
        var tasks = taskManager.getTasks();
        try {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
