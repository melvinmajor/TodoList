package todolist.client.cli;

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

        var client = CLIClient.instance;
        client.setPort(port);
        client.run();
    }
}
