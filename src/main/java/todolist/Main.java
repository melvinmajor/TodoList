package todolist;

import todolist.clients.Client;
import todolist.clients.cli.CLIClient;
import todolist.server.Server;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> argsList = List.of(args);

        int port = 8002;
        int i = argsList.indexOf("--port");
        if (i != -1 && i + 1 < argsList.size()) {
            String maybePort = argsList.get(i + 1);
            try {
                port = Integer.parseInt(maybePort);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port");
            }
        }

        if (argsList.contains("--server")) {
            Server server = new Server(port);
            Thread thread = new Thread(server::run);
            thread.setDaemon(false);
            thread.start();
        } else if (argsList.contains("--gui")) {
            // TODO
        } else {
            Client client = new CLIClient();
            client.setPort(port);
            client.run();
        }

    }
}
