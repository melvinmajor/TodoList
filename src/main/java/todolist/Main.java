package todolist;

import todolist.commands.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, Command> commandMap = new HashMap<>();
    public static TaskManager taskManager = new TaskManager();
    
    static {
        commandMap.put("add",  new AddCommand());
        commandMap.put("ls",   new ListCommand());
        commandMap.put("rm",   new RemoveCommand());
        commandMap.put("edit", new EditCommand());
        commandMap.put("done", new DoneCommand());
        commandMap.put("help", new HelpCommand());
    }

    public static void main(String[] args) {
        var command = ArgParser.parse(args);
        command.execute();
    }
}
