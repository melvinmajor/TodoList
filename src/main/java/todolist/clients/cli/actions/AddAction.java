package todolist.clients.cli.actions;

import todolist.clients.cli.util.ParseUtil;
import todolist.common.Command;

import java.time.LocalDate;

public class AddAction implements Action {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean execute(Data data) {
        var task = ParseUtil.parseTask(data.args)
                .setCreationDate(LocalDate.now())
                .setId(data.nextAvailableId)
                .build();

        if (task.description != null && !task.description.trim().isEmpty()) {
            data.editedTask = task;
            return true;
        }

        System.err.println("A description is needed");
        return false;
    }

    @Override
    public Command command() {
        return Command.ADD;
    }
}
