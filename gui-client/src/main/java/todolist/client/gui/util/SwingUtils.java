package todolist.client.gui.util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * A collection of methods in order to reduce boilerplate with swing.
 */
public class SwingUtils {

    /**
     * An instance of {@code ChoiceCollector} so we don't need to create a new one each time.
     */
    public static final ChoiceCollector choiceCollector = new ChoiceCollector();

    /**
     * Display a popup dialog
     *
     * @param msg the error message to display
     */
    public static void errorPopup(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
    }

    /**
     * Returns a new JButton with an actionListener
     *
     * @param text    the text of the button
     * @param onClick the listener called when the button is clicked
     * @return a new {@code JButton}
     */
    public static JButton newButton(String text, Runnable onClick) {
        var button = new JButton(text);
        button.addActionListener(e -> onClick.run());
        return button;
    }

    /**
     * Create a {@Code FlowLayout} composed of components
     *
     * @param components the components
     * @return a new FlowLayout
     */
    public static JPanel lineOf(Component... components) {
        var layout = new FlowLayout();
        var panel = new JPanel();
        panel.setLayout(layout);
        for (var c : components) panel.add(c);
        return panel;
    }

    /**
     * Create a new {@code JTextField} with a callback when its value is changed
     *
     * @param columns the number of columns
     * @param onChange a callback called when the text inside the JTextField is updated
     * @return a JTextField
     */
    public static JTextField newTextField(int columns, Consumer<String> onChange) {
        var field = new JTextField(columns);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange.accept(field.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange.accept(field.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange.accept(field.getText());
            }
        });

        return field;
    }

}
