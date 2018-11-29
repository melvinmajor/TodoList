package todolist.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    public final ObjectOutputStream out;
    public final ObjectInputStream in;
    public final Socket socket;

    public Connection(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        this.socket = socket;
    }
}
