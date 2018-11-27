package todolist.server;

import todolist.commands.Command;
import todolist.commands.ExitServerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class Server {
    private int port;
    private ServerSocket socket;

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
                var action = (Command) in.readObject();

                if (action instanceof ExitServerCommand) break;

                handleAction(action);
                server.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAction(Command command) {
        // FIXME debug
        System.out.println(command.getClass().getSimpleName());

        // command.execute();
    }

}
