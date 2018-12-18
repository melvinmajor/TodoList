package todolist.server;

import todolist.common.Task;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides a storage for the tasks
 */
public class TaskManager {
    private final Set<Task> tasks = new HashSet<>();

    /**
     * Add a task or edit it if present
     * @param task the task
     */
    public synchronized void addOrEditTask(Task task) {
        tasks.remove(task);
        tasks.add(task);
    }

    /**
     * removes a task
     * @param task the removed task
     * @return the success
     */
    public synchronized boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     *
     * @return the tasks
     */
    public Collection<Task> getTasks() {
        return tasks;
    }

}
