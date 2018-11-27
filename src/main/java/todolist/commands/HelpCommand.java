package todolist.commands;

import todolist.Main;

public class HelpCommand extends Command {

    @Override
    public void execute() {
        Main.commandMap.values().stream()
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