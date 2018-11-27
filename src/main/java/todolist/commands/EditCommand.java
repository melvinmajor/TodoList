package todolist.commands;

import todolist.Task;

import java.util.Collection;

public class EditCommand extends Command {

    @Override
    public void execute(Task task) {        // TODO Auto-generated method stub
    }

    public String name() {
        return "edit";
    }

    public String usage() {
        return "Edit a task.";
    }
}
