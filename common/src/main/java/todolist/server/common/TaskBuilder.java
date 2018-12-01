package todolist.server.common;

import java.time.LocalDate;

public class TaskBuilder {
    private int id;
    private String description;
    private Importance importance;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private boolean completed;

    public TaskBuilder id(int id) {
        this.id = id;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder importance(Importance importance) {
        this.importance = importance;
        return this;
    }

    public TaskBuilder creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskBuilder completed(boolean completed) {
        this.completed = completed;
        return this;
    }

    public Task build() {
        // TODO null checking maybe
        return new Task(id, description, importance, creationDate, dueDate, completed);
    }

}
