package todolist.client.gui;

import todolist.client.base.BaseClient;

public class Main {

    /**
     * Initialize the GUI Client.
     * @param args
     */
    public static void main(String[] args) {
        BaseClient client = new MainScreen();
        client.setPort(8002);
        client.run();
    }

}
