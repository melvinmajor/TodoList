package todolist.clients.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

import java.util.Optional;

public class RemoveAction implements Action {
    @Override
    public String getName() {
        return "rm";
    }

    // TODO remove by id for now
    @Override
    public boolean execute(Data data) {
        if (!data.args.isEmpty()) {
            int id;
            try {
                id = Integer.parseInt(data.args.get(0));
            } catch (NumberFormatException e) {
                return false;
            }

            Optional<Task> task = data.tasks.stream()
                    .filter(e -> e.id == id)
                    .findAny();

            if (task.isPresent()) {
                data.editedTask = task.get();
                return true;
            }
        }
        return false;
    }

    @Override
    public Command command() {
        return Command.REMOVE;
    }
}
