package todolist.client.cli.actions;

import todolist.common.Task;

// TODO rename
public class Data {
    public final int nextAvailableId;

    public boolean closeClient;
    public Task editedTask;

    public Data(int nextAvailableId) {
        this.nextAvailableId = nextAvailableId;
    }
}
