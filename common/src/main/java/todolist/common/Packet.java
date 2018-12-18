package todolist.common;

import java.io.Serializable;

/**
 * Provides an object sent by a client to the server
 */
public class Packet implements Serializable {
    public final Command command;
    public final Task task;

    public Packet(Command command, Task task) {
        this.command = command;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "command=" + command +
                ", task=" + task +
                '}';
    }
}
