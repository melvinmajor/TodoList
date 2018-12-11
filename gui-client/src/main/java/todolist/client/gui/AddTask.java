package todolist.client.gui;

import todolist.common.Importance;
import todolist.common.TaskBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class AddTask extends JFrame {

    /**
     * Launch the frame to add tasks in the GUI client.
     * @wbp.parser.entryPoint
     */
    public void run() {
        setTitle("TodoList - Add Task");
        setResizable(false);
        setVisible(true);
    }

    /**
     * Create the frame.
     */
    public AddTask() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 388, 260);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(10, 11, 69, 22);
        contentPane.add(dueDateLabel);

        // day
        var intFormat = NumberFormat.getIntegerInstance();
        var numberFormatter = new NumberFormatter(intFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        numberFormatter.setMaximum(31);
        var dayField = new JFormattedTextField(numberFormatter);
        dayField.setBounds(89, 12, 76, 20);
        dayField.setColumns(10);
        contentPane.add(dayField);

        // month
        var monthChoice = new Choice();
        List.of(Month.values())
                .forEach(e -> monthChoice.add(e.name().toLowerCase()));
        monthChoice.setBounds(171, 13, 130, 20);
        contentPane.add(monthChoice);

        // year
        var yearFormat = NumberFormat.getIntegerInstance();
        var yearFormatter = new NumberFormatter(yearFormat);
        yearFormatter.setValueClass(Integer.class);
        yearFormatter.setAllowsInvalid(false);
        yearFormatter.setMinimum(1);
        var yearField = new JFormattedTextField(yearFormatter);
        yearField.setBounds(307, 12, 75, 20);
        yearField.setColumns(10);
        contentPane.add(yearField);

        // importance label
        var textLabel = new JLabel("Importance:");
        textLabel.setBounds(10, 44, 93, 22);
        contentPane.add(textLabel);

        // importance choice
        var importanceChoice = new Choice();
        importanceChoice.add("none");
        List.of(Importance.values())
                .forEach(e -> importanceChoice.add(e.name().toLowerCase()));
        importanceChoice.setBounds(113, 46, 151, 20);
        contentPane.add(importanceChoice);

        // description
        var descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 77, 93, 20);
        contentPane.add(descriptionLabel);

        var descriptionArea = new JTextArea();
        descriptionArea.setBounds(6, 109, 376, 83);
        contentPane.add(descriptionArea);

        // add button
        var addButton = new JButton("Add");
        addButton.setBounds(293, 204, 89, 23);
        addButton.addActionListener(e -> {
            var builder = new TaskBuilder();

            var description = descriptionArea.getText();
            if (description.isEmpty()) {
                // TODO popup
                return;
            } else {
                builder.setDescription(description);
            }

            if (!importanceChoice.getSelectedItem().equals("none")) {
                var importance = Importance.valueOf(importanceChoice.getSelectedItem().toUpperCase());
                builder.setImportance(importance);
            }

            var datePresent = !dayField.getText().isEmpty() && !yearField.getText().isEmpty();
            if (datePresent) {
                var day = Integer.parseInt(dayField.getText());
                var month = Month.valueOf(monthChoice.getSelectedItem().toUpperCase());
                var year = Integer.parseInt(yearField.getText());

                try {
                    builder.setDueDate(LocalDate.of(year, month, day));
                } catch (DateTimeException ex) {
                    // TODO on invalid date popup maybe ??
                    ex.printStackTrace();
                }
            }

            var creationDate = LocalDate.now();

            builder.setCompleted(false)
                    .setCreationDate(creationDate)
                    .build();

            // TODO send query

            // TODO reset fields
        });
        contentPane.add(addButton);
    }
}
