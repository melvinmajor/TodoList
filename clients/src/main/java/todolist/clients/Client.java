package todolist.clients;

import todolist.server.common.Task;

import java.util.Collection;

public interface Client {
    void run();

    void setPort(int port);

    boolean onUpdate(Collection<Task> tasks);

    void onConnectionError();

    void onExit();
}
