package todolist.client.gui;

import todolist.client.base.BaseClient;
import todolist.client.gui.util.SwingUtils;
import todolist.common.Query;
import todolist.common.Task;

import java.util.Collection;
import java.util.List;

public class GuiClient extends BaseClient {
    public static final GuiClient instance = new GuiClient();

    public final Screen screen = new Screen();

    private GuiClient() {
    }

    /**
     * Launch the main frame of the GUI client.
     */
    @Override
    public void run() {
        screen.display();
        super.run();
    }


    /**
     * If a connection error occurs while trying to connect to the server, an error
     * message appears.
     */
    @Override
    public void onConnectionError() {
        SwingUtils.errorPopup("Connection with the server failed");
        onExit();
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    /**
     * Update content from the server in the table initialized in the content of the
     * frame.
     */
    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        var temp = super.onUpdate(tasks);
        screen.defaultView.updateTaskTable();
        return temp;
    }

    @Override
    public int nextAvailableId() {
        return super.nextAvailableId();
    }

    @Override
    public void sendQuery(Query query) {
        super.sendQuery(query);
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
