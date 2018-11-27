package todolist.commands;

import todolist.Task;

public class RemoveCommand extends Command {

    @Override
    public void execute(Task task) {
        // TODO Auto-generated method stub
    }

    public String name() {
        return "rm";
    }

    public String usage() {
        return "Remove a task.";
    }
}



