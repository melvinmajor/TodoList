package todolist.commands;

import todolist.Task;

public class DoneCommand extends Command {

    @Override
    public void execute(Task task) {
        // TODO Auto-generated method stub
    }

    public String name() {
        return "done";
    }

    public String usage() {
        return "Set a task as done.";
    }
}
