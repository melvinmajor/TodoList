package todolist.client.base;

import todolist.common.Task;

import java.util.Collection;

/**
 * Represents a client
 */
public interface Client {
    void run();

    void setPort(int port);

    boolean onUpdate(Collection<Task> tasks);

    void onConnectionError();

    void onExit();
}
