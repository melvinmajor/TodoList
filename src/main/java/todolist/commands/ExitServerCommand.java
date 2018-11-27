package todolist.commands;

import todolist.Task;

import java.util.Collection;

public class ExitServerCommand extends Command {
    @Override
    public void execute(Task task) {    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String usage() {
        return "exit the server";
    }
}
