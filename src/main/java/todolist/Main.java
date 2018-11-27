package todolist;

import todolist.cli.CLI;
import todolist.commands.*;
import todolist.server.Server;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static Map<String, Command> commandMap = new HashMap<>();
    public static Server server;

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

        server = new Server(port);
        new Thread(server::run).start();

        var command = ArgParser.parse(args);
        CLI client = new CLI("localhost", port);

        // FIXME debug
        HashSet<String> categories = new HashSet<>();
        categories.add("hhhhhh");
        Task task = new Task("test",
                Importance.HIGH,
                LocalDate.now(),
                LocalDate.now(),
                categories,
                categories,
                false
        );

        Query query = new Query(commandMap.get("add"), task);
        client.sendCommand(query);


      //  Query listQuery = new Query(command, null);
      //  client.sendCommand(query);

        client.sendCommand(new Query(new ExitServerCommand(), null));
    }
}
