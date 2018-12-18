package todolist.client.gui;

import todolist.client.gui.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Screen extends JFrame {

    private final Container mainContainer;

    public final MainView defaultView = new MainView();
    public final View addView = new AddView();
    public final View deleteView = new DeleteView();
    public final View completeView = new CompleteView();

    public Screen() {
        setTitle("TodoList");

        var icons = Stream.of("TodoList-icon-16.png", "TodoList-icon-32.png", "TodoList-icon.png")
                .map(e -> Toolkit.getDefaultToolkit().getImage(e))
                .collect(Collectors.toList());

        setIconImages(icons);

        mainContainer = getContentPane();
        mainContainer.setBackground(new Color(238, 238, 238));

        setBounds(100, 100, 600, 400);
        setMinimumSize(new Dimension(600, 400));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        swapView(defaultView);
    }

    public void display() {
        setVisible(true);
    }

    private void clear() {
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public void swapView(View newView) {
        clear();
        newView.fill(mainContainer);
    }

}
