package todolist.clients;

import todolist.common.Command;
import todolist.common.Query;
import todolist.common.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class BaseClient implements Client {
    private int port;
    private final String host = "localhost";
    protected List<Task> tasks;

    @Override
    public void run() {
        sendQuery(new Query(Command.INIT, null));
        //new Thread(this::listen).start();
    }

    private void listen() {
        try {
            Socket client = new Socket(host, port);
            var in = new ObjectInputStream(client.getInputStream());

            while (true) {
                Object obj = in.readObject();
                if (obj instanceof Collection<?>) {
                    var tasks = (Collection<Task>) obj;
                    System.out.println("GOT UPDATE");
                    // onUpdate(tasks);
                }
            }

            // client.close();

        } catch (ConnectException e) {
            onConnectionError();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void sendQuery(Query query) {
        Socket client;
        try {
            client = new Socket(host, port);
            var out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(query);

            var in = new ObjectInputStream(client.getInputStream());

            Object obj = in.readObject();
            if (obj instanceof Collection<?>) {
                var tasks = (Collection<Task>) obj;

                onUpdate(tasks);
            }

            client.close();

        } catch (ConnectException e) {
            onConnectionError();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void onUpdate(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    // TODO cache ?
    protected int nextAvailableId() {
        Optional<Integer> max = tasks.stream().map(e -> e.id).max(Integer::compareTo);
        var next = max.orElse(0);
        return ++next;
    }
}
