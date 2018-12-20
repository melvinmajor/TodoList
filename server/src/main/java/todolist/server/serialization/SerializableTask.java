package todolist.server.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import todolist.common.Importance;
import todolist.common.Task;
import todolist.common.TaskBuilder;

import java.time.LocalDate;

public class SerializableTask {
    private final String description;
    private final Importance importance;
    // ISO 8601 representation
    private final String dueDate;
    private final boolean completed;

    @JsonCreator
    public SerializableTask(
            @JsonProperty("description") String description,
            @JsonProperty("importance") Importance importance,
            @JsonProperty("dueDate") String dueDate,
            @JsonProperty("completed") boolean completed
    ) {
        this.description = description;
        this.importance = importance;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public SerializableTask(Task task) {
        this.description = task.description;
        this.importance = task.importance;

        this.dueDate = task.dueDate != null ? task.dueDate.toString() : null;

        this.completed = task.completed;
    }

    public TaskBuilder toTaskBuilder() {
        var builder = new TaskBuilder()
                .setDescription(description)
                .setImportance(importance)
                .setCompleted(completed);

        if (dueDate != null && !dueDate.isEmpty()) {
            builder.setDueDate(LocalDate.parse(dueDate));
        }

        return builder;
    }

}
