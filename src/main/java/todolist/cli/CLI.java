package todolist.cli;

import todolist.Query;
import todolist.Task;
import todolist.commands.ExitServerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class CLI {
    private String host;
    private int port;

    public CLI(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendCommand(Query query){
        Socket client = null;
        try {
            client = new Socket(host, port);
            var out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(query);

            if(query.command instanceof ExitServerCommand){
                client.close();
                return;
            }

            var in = new ObjectInputStream(client.getInputStream());

            Object obj = in.readObject();
            if (obj instanceof Collection<?>){
                var tasks = (Collection<Task>) obj;

                // TODO debug
                System.out.println("size: " + tasks.size());
                System.out.println(tasks);
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}