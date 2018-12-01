package todolist.cli.actions;

import todolist.cli.util.ArgParser;
import todolist.server.common.Command;

import java.time.LocalDate;

public class AddAction implements Action {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean execute(Data data) {
        var task = ArgParser.parseTask(data.args)
                .creationDate(LocalDate.now())
                .id(data.nextAvailableId)
                .build();

        if (task.description != null && !task.description.trim().isEmpty()) {
            data.editedTask = task;
            return true;
        }

        return false;
    }

    @Override
    public Command command() {
        return Command.ADD;
    }
}
