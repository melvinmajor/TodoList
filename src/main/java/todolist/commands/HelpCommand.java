package todolist.commands;

import todolist.Main;
import todolist.Task;

import java.util.Collection;

public class HelpCommand extends Command {

    @Override
    public void execute(Task task) {        Main.commandMap.values().stream()
                .map(e -> e.name() + ": " + e.usage())
                .forEach(System.out::println);
    }

    public String name() {
        return "help";
    }

    public String usage() {
        return "Print the help message corresponding to the command asked.";
    }
}