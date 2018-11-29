package todolist.common;

import todolist.common.Task;
import todolist.common.Command;

import java.io.Serializable;

public class Query implements Serializable {
    public final Command command;
    public final Task task;

    public Query(Command command, Task task) {
        this.command = command;
        this.task = task;
    }
}
