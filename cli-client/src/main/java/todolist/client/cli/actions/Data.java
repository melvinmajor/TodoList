package todolist.client.cli.actions;

import todolist.client.cli.util.CliUtil;
import todolist.common.Task;

// TODO rename
public class Data {
    public final int nextAvailableId;
    public final CliUtil cliUtil;

    public boolean closeClient;
    public Task editedTask;

    public Data(int nextAvailableId, CliUtil cliUtil) {
        this.nextAvailableId = nextAvailableId;
        this.cliUtil = cliUtil;
    }
}
