package todolist.server;

import todolist.server.common.Task;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaskManager {
    private final Set<Task> tasks = new HashSet<>();

    public synchronized void addOrEditTask(Task task) {
        tasks.remove(task);
        tasks.add(task);
    }

    public synchronized boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

}
