package todolist.client.gui;

import todolist.common.Importance;
import todolist.common.TaskBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class AddTask extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddTask frame = new AddTask();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public AddTask() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel dueDateLabel = new JLabel();
        dueDateLabel.setText("Due Date");
        dueDateLabel.setBounds(10, 41, 69, 22);
        contentPane.add(dueDateLabel);

        // day
        var intFormat = NumberFormat.getIntegerInstance();
        var numberFormatter = new NumberFormatter(intFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        numberFormatter.setMaximum(31);
        var dayField = new JFormattedTextField(numberFormatter);
        dayField.setBounds(86, 44, 151, 20);
        dayField.setColumns(10);
        contentPane.add(dayField);

        // month
        var monthChoice = new Choice();
        List.of(Month.values()).forEach(e -> monthChoice.add(e.name().toLowerCase()));
        monthChoice.setBounds(237, 44, 151, 20);
        contentPane.add(monthChoice);

        // year
        var yearFormat = NumberFormat.getIntegerInstance();
        var yearFormatter = new NumberFormatter(yearFormat);
        yearFormatter.setValueClass(Integer.class);
        yearFormatter.setAllowsInvalid(false);
        yearFormatter.setMinimum(1);
        var yearField = new JFormattedTextField(yearFormatter);
        yearField.setBounds(388, 44, 151, 20);
        yearField.setColumns(10);
        contentPane.add(yearField);

        var textLabel = new JLabel();
        textLabel.setText("Priority");
        textLabel.setBounds(10, 74, 69, 22);
        contentPane.add(textLabel);

        // importance
        var importanceChoice = new Choice();
        List.of(Importance.values())
                .forEach(e -> importanceChoice.add(e.name().toLowerCase()));
        importanceChoice.setBounds(86, 76, 151, 20);
        contentPane.add(importanceChoice);

        // description
        var descriptionTextArea = new JTextArea();
        descriptionTextArea.setText("Description");
        descriptionTextArea.setBounds(10, 107, 93, 20);
        descriptionTextArea.setEditable(false);
        contentPane.add(descriptionTextArea);

        JTextArea txtrTxtarea = new JTextArea();
        txtrTxtarea.setText("");
        txtrTxtarea.setBounds(10, 138, 414, 61);
        contentPane.add(txtrTxtarea);

        var completedCheckbox = new JCheckBox("Completed");
        completedCheckbox.setBounds(6, 231, 97, 23);
        contentPane.add(completedCheckbox);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(321, 231, 89, 23);
        btnAdd.addActionListener(e -> {
            var description = descriptionTextArea.getText();
            var importance = Importance.valueOf(importanceChoice.getSelectedItem().toUpperCase());

            var day = Integer.parseInt(dayField.getText());
            var month = Month.valueOf(monthChoice.getSelectedItem());
            var year = Integer.parseInt(yearField.getText());
            var dueDate = LocalDate.of(year, month, day);

            var creationDate = LocalDate.now();

            var task = new TaskBuilder()
                    .setDescription(description)
                    .setCompleted(false)
                    .setCreationDate(creationDate)
                    .setDueDate(dueDate)
                    .setImportance(importance)
                    .build();


        });

        contentPane.add(btnAdd);
    }
}
