package todolist;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Task {
    private static int currentID;

    private int id;
    private String description;
    private Importance importance;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private Set<String> categories;
    private Set<String> users;
    private boolean completed;

    public Task(String description, Importance importance, LocalDate creationDate, LocalDate dueDate, Set<String> categories, Set<String> users, boolean completed) {
        this.id = currentID++;
        this.description = description;
        this.importance = importance;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.categories = categories;
        this.users = users;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
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
}
