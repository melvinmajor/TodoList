package todolist.cli;

import todolist.commands.Command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CLI {
    private String host;
    private int port;

    public CLI(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendCommand(Command command){
        Socket client = null;
        try {
            client = new Socket(host, port);
            var out = new ObjectOutputStream(client.getOutputStream());


            out.writeObject(command);

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}