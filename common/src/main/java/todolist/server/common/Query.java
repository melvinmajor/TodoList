package todolist.server.common;

import java.io.Serializable;

public class Query implements Serializable {
    public final Command command;
    public final Task task;

    public Query(Command command, Task task) {
        this.command = command;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Query{" +
                "command=" + command +
                ", task=" + task +
                '}';
    }
}
