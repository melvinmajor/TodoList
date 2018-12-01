package todolist.cli.actions;

import todolist.server.common.Task;

import java.util.List;

// TODO rename
public class Data {
    public final List<Task> tasks;
    public final List<String> args;
    public boolean closeClient;
    public final int nextAvailableId;
    public Task editedTask;

    public Data(List<Task> tasks, List<String> args, int nextAvailableId) {
        this.tasks = tasks;
        this.args = args;
        this.nextAvailableId = nextAvailableId;
    }
}
