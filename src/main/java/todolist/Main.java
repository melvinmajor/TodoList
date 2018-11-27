package todolist;

import todolist.cli.CLI;
import todolist.commands.*;
import todolist.server.Server;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, Command> commandMap = new HashMap<>();
    public static TaskManager taskManager = new TaskManager();

    static {
        commandMap.put("add", new AddCommand());
        commandMap.put("ls", new ListCommand());
        commandMap.put("rm", new RemoveCommand());
        commandMap.put("edit", new EditCommand());
        commandMap.put("done", new DoneCommand());
        commandMap.put("help", new HelpCommand());
    }

    public static void main(String[] args) {
        // Start server

        int port = 8002;


        Server server = new Server(port);
        new Thread(server::run).start();

        var command = ArgParser.parse(args);
        CLI client = new CLI("localhost", port);
        client.sendCommand(command);

        client.sendCommand(new ExitServerCommand());
    }
}
