package todolist.common;

import java.time.LocalDate;

public class TaskBuilder {
    private int id;
    private String description;
    private Importance importance;
    private LocalDate dueDate;
    private boolean completed;

    public TaskBuilder() {
    }

    public TaskBuilder(Task task) {
        this.id = task.id;
        this.description = task.description;
        this.importance = task.importance;
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

    public TaskBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public boolean validate() {
        return description != null && !description.isBlank();
    }

    public Task build() {
        if (!validate()) throw new IllegalStateException();

        return new Task(id, description, importance, dueDate, completed);
    }

}
