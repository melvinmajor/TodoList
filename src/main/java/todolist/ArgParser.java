package todolist;

import todolist.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArgParser {
    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("add", new AddCommand());
        commandMap.put("ls", new ListCommand());
        commandMap.put("rm", new RemoveCommand());
        commandMap.put("edit", new EditCommand());
        commandMap.put("done", new DoneCommand());
        commandMap.put("help", new HelpCommand());
    }

    public static Command parse(String[] args) {
        var helpCommand = commandMap.get("help");
        if (args.length == 0) return helpCommand;

        var command = match(args[0]);
        return command.orElse(helpCommand);
    }

    private static Optional<Command> match(String s) {
        var lower = s.toLowerCase();
        List<String> matches = commandMap
                .keySet()
                .stream()
                .filter(e -> e.startsWith(lower))
                .collect(Collectors.toList());

        if (matches.size() != 1) return Optional.empty();

        var onlyMatch = commandMap.get(matches.get(0));
        return Optional.of(onlyMatch);
    }

}
