package todolist.commands;

import todolist.Main;
import todolist.Task;

public class ListCommand extends Command {

    @Override
    public void execute(Task task) {

    }

    public String name() {
        return "ls";
    }

    public String usage() {
        return "List all tasks.";
    }
}
