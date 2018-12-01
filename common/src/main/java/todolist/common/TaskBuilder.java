package todolist.common;

import java.time.LocalDate;
import java.util.Objects;

public class TaskBuilder {
    private int id;
    private String description;
    private Importance importance;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private boolean completed;

    public TaskBuilder() {
    }

    public TaskBuilder(Task task) {
        this.id = task.id;
        this.description = task.description;
        this.importance = task.importance;
        this.creationDate = task.creationDate;
        this.dueDate = task.dueDate;
        this.completed = task.completed;
    }

    public TaskBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setImportance(Importance importance) {
        this.importance = importance;
        return this;
    }

    public TaskBuilder setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public Task build() {
        // TODO null checking maybe
        Objects.requireNonNull(description);

        return new Task(id, description, importance, creationDate, dueDate, completed);
    }

}
