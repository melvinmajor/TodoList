package todolist.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The task class
 */
public class Task implements Serializable {
    public final int id;
    public final String description;
    public final Importance importance;
    public final LocalDate dueDate;
    public final boolean completed;

    public Task(int id, String description, Importance importance, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.description = description;
        this.importance = importance;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", importance=" + importance +
                ", due=" + dueDate +
                ", completed=" + completed +
                '}';
    }
}
