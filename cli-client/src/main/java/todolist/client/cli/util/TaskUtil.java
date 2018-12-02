package todolist.client.cli.util;

import todolist.common.Task;

import java.util.Collection;

public class TaskUtil {
    public static Task getTaskById(Collection<Task> tasks, int id) {
        return tasks.stream()
                .filter(e -> e.id == id)
                .findAny()
                .orElse(null);
    }
}
