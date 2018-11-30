package todolist.clients.cli.actions;

import todolist.clients.cli.util.CliUtil;
import todolist.common.Task;

import java.util.List;

// TODO rename
public class Data {
    public final List<Task> tasks;
    public final int nextAvailableId;
    public final CliUtil cliUtil;

    public boolean closeClient;
    public Task editedTask;

    public Data(List<Task> tasks, int nextAvailableId, CliUtil cliUtil) {
        this.tasks = tasks;
        this.nextAvailableId = nextAvailableId;
        this.cliUtil = cliUtil;
    }
}
