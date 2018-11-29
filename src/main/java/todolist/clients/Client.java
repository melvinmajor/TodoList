package todolist.clients;

import todolist.common.Task;

import java.util.Collection;

public interface Client {
    void run();

    void setPort(int port);

    void onUpdate(Collection<Task> tasks);

    void onConnectionError();
}
