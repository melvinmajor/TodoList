package todolist.commands;

import todolist.Main;
import todolist.Task;

public class AddCommand extends Command {

    @Override
    public void execute(Task task) {
        Main.server.taskManager.addTask(task);
    }

    public String name() {
        return "add";
    }

    public String usage() {
        return "Add a task.";
    }
}
