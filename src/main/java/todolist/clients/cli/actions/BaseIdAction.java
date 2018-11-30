package todolist.clients.cli.actions;

import todolist.clients.cli.util.ParseUtil;
import todolist.clients.cli.util.TaskUtil;
import todolist.common.Task;

import java.util.List;

public abstract class BaseIdAction implements Action {

    @Override
    public boolean execute(Data data) {
        if (data.args.isEmpty()) {
            System.err.println("missing argument (id)");
            return false;
        }

        var id = ParseUtil.getIntOrElse(data.args.get(0), -1);
        if (id == -1) {
            System.err.println("NaN: " + data.args.get(0));
            return false;
        }

        var task = TaskUtil.getTaskById(data.tasks, id);
        if (task != null) {
            task = edit(task, data.args);
            if (task == null) return false;
            data.editedTask = task;
            return true;
        } else {
            System.err.println("no task found with id " + id);
            return false;
        }
    }

    protected abstract Task edit(Task task, List<String> args);

}
