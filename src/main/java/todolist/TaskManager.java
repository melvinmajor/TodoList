package todolist;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaskManager {
    private Set<Task> tasks = new HashSet<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(Task task) {
        tasks.remove(task);
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

}
