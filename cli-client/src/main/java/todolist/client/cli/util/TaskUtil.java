package todolist.client.cli.util;

import todolist.client.cli.CLIClient;
import todolist.common.Task;

public class TaskUtil {
    public static Task getTaskById(int id) {
        return CLIClient.instance
                .getTasks()
                .stream()
                .filter(e -> e.id == id)
                .findAny()
                .orElse(null);
    }
}
