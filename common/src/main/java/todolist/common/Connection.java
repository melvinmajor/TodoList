package todolist.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a connection between the server and a client
 */
public class Connection<T> {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final Socket socket;
    private final Function<T, Boolean> listenCallback;
    private final Consumer<Connection<T>> onCloseCallback;

    public Connection(Socket socket, Function<T, Boolean> listenCallback, Consumer<Connection<T>> onCloseCallback) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.listenCallback = listenCallback;
        this.onCloseCallback = onCloseCallback;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void close() {
        try {
            socket.close();
            onCloseCallback.accept(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        Runnable runnable = () -> {
            while (!socket.isClosed()) {
                try {
                    T obj = (T) in.readObject();

                    boolean exit = listenCallback.apply(obj);
                    if (exit) close();
                } catch (IOException e) {
                    close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
    }

    public void send(Object obj) {
        if (socket.isClosed()) {
            System.err.println("Socket is closed");
            return;
        }
        try {
            out.writeObject(obj);
            out.flush();
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
