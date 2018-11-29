package todolist.server;

import todolist.common.Task;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaskManager {
    private Set<Task> tasks = new HashSet<>();

    public void addOrEditTask(Task task) {
        tasks.remove(task);
        tasks.add(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

}
