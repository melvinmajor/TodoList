package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

public class ListAction implements Action {
    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public boolean execute(Data data) {
        if (data.tasks.isEmpty()) {
            System.out.println("There is no tasks!");
            return true;
        }

        // TODO header and table
        System.out.println("tasks: ");
        for (Task t : data.tasks) {
            System.out.println(t);
        }
        return true;
    }

    @Override
    public Command command() {
        return null;
    }

}