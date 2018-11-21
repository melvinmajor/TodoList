package todolist;

import todolist.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArgParser {

    public static Command parse(String[] args) {
        var helpCommand = Main.commandMap.get("help");
        if (args.length == 0) return helpCommand;

        var command = match(args[0]);
        return command.orElse(helpCommand);
    }

    private static Optional<Command> match(String s) {
        var lower = s.toLowerCase();
        List<String> matches = Main.commandMap
                .keySet()
                .stream()
                .filter(e -> e.startsWith(lower))
                .collect(Collectors.toList());

        if (matches.size() != 1) return Optional.empty();

        var onlyMatch = Main.commandMap.get(matches.get(0));
        return Optional.of(onlyMatch);
    }

}
