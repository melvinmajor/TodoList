package todolist.server;

import todolist.Query;
import todolist.Task;
import todolist.TaskManager;
import todolist.commands.ExitServerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Collection;

public class Server {
    public final TaskManager taskManager = new TaskManager();
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
        while (true) {
            try {
                var server = socket.accept();
                var in = new ObjectInputStream(server.getInputStream());
                var action = (Query) in.readObject();

                if (action.command instanceof ExitServerCommand) {
                    server.close();
                    break;
                }

                handleAction(action, new ObjectOutputStream(server.getOutputStream()));

                server.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAction(Query query, ObjectOutputStream out) {
        query.command.execute(query.task);
        Collection<Task> tasks = taskManager.getTasks();
        try {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
