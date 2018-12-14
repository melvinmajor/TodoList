package todolist.client.gui;

import todolist.common.Importance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class AddTaskScreen extends JFrame {
    private final JFormattedTextField dayField;
    private final Choice monthChoice;
    private final Choice yearChoice;
    private final Choice importanceChoice;
    private final JTextArea descriptionArea;
    private final JButton addButton;

    /**
     * Launch the frame to add tasks in the GUI client.
     */
    public void run() {
        setTitle("TodoList - Add Task");
        setMinimumSize(new Dimension(450, 260));
        setResizable(true);
        setVisible(true);
    }

    /**
     * Create the frame.
     */
    public AddTaskScreen(MainScreen parent) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 260);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // ???
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{94, 4, 66, 151, 93, 0};
        gbl_contentPane.rowHeights = new int[]{22, 22, 20, 83, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        // day format
        var intFormat = NumberFormat.getIntegerInstance();
        var numberFormatter = new NumberFormatter(intFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        numberFormatter.setMaximum(31);

        // day
        dayField = new JFormattedTextField(numberFormatter);
        dayField.setColumns(10);

        // ???
        GridBagConstraints gbc_dayField = new GridBagConstraints();
        gbc_dayField.fill = GridBagConstraints.BOTH;
        gbc_dayField.insets = new Insets(0, 0, 5, 5);
        gbc_dayField.gridwidth = 2;
        gbc_dayField.gridx = 1;
        gbc_dayField.gridy = 0;
        contentPane.add(dayField, gbc_dayField);

        // month
        monthChoice = new Choice();
        List.of(Month.values()).forEach(e -> monthChoice.add(e.name().toLowerCase()));

        // ???
        GridBagConstraints gbc_monthChoice = new GridBagConstraints();
        gbc_monthChoice.fill = GridBagConstraints.BOTH;
        gbc_monthChoice.insets = new Insets(0, 0, 5, 5);
        gbc_monthChoice.gridx = 3;
        gbc_monthChoice.gridy = 0;
        contentPane.add(monthChoice, gbc_monthChoice);


        // year
        yearChoice = new Choice();
        int currentYear = LocalDate.now().getYear();
        for(int i= currentYear;  i<currentYear + 30; i++) {
            yearChoice.add(String.valueOf(i));
        }

        // ???
        GridBagConstraints gbc_yearField = new GridBagConstraints();
        gbc_yearField.fill = GridBagConstraints.BOTH;
        gbc_yearField.insets = new Insets(0, 0, 5, 0);
        gbc_yearField.gridx = 4;
        gbc_yearField.gridy = 0;
        contentPane.add(yearChoice, gbc_yearField);

        var dueDateLabel = new JLabel("Due Date:");

        // ???
        GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
        gbc_dueDateLabel.fill = GridBagConstraints.BOTH;
        gbc_dueDateLabel.insets = new Insets(0, 0, 5, 5);
        gbc_dueDateLabel.gridx = 0;
        gbc_dueDateLabel.gridy = 0;
        contentPane.add(dueDateLabel, gbc_dueDateLabel);

        // importance label
        var importanceLabel = new JLabel("Importance:");

        // importance choice
        importanceChoice = new Choice();
        importanceChoice.add("none");
        List.of(Importance.values())
                .forEach(e -> importanceChoice.add(e.name().toLowerCase()));

        // ???
        GridBagConstraints gbc_textLabel = new GridBagConstraints();
        gbc_textLabel.fill = GridBagConstraints.BOTH;
        gbc_textLabel.insets = new Insets(0, 0, 5, 5);
        gbc_textLabel.gridwidth = 2;
        gbc_textLabel.gridx = 0;
        gbc_textLabel.gridy = 1;
        contentPane.add(importanceLabel, gbc_textLabel);

        // ???
        GridBagConstraints gbc_importanceChoice = new GridBagConstraints();
        gbc_importanceChoice.fill = GridBagConstraints.BOTH;
        gbc_importanceChoice.insets = new Insets(0, 0, 5, 5);
        gbc_importanceChoice.gridwidth = 2;
        gbc_importanceChoice.gridx = 2;
        gbc_importanceChoice.gridy = 1;
        contentPane.add(importanceChoice, gbc_importanceChoice);

        // description
        var descriptionLabel = new JLabel("Description:");

        // ???
        GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
        gbc_descriptionLabel.fill = GridBagConstraints.BOTH;
        gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
        gbc_descriptionLabel.gridwidth = 2;
        gbc_descriptionLabel.gridx = 0;
        gbc_descriptionLabel.gridy = 2;
        contentPane.add(descriptionLabel, gbc_descriptionLabel);

        // description
        descriptionArea = new JTextArea();

        GridBagConstraints gbc_descriptionArea = new GridBagConstraints();
        gbc_descriptionArea.fill = GridBagConstraints.BOTH;
        gbc_descriptionArea.insets = new Insets(0, 0, 5, 0);
        gbc_descriptionArea.gridwidth = 5;
        gbc_descriptionArea.gridx = 0;
        gbc_descriptionArea.gridy = 3;
        contentPane.add(descriptionArea, gbc_descriptionArea);

        // add button
        addButton = new JButton("Add");
        addButton.addActionListener(e -> new AddTask(this, parent).onAddButtonPressed());

        // ???
        GridBagConstraints gbc_addButton = new GridBagConstraints();
        gbc_addButton.fill = GridBagConstraints.BOTH;
        gbc_addButton.gridx = 4;
        gbc_addButton.gridy = 4;
        contentPane.add(addButton, gbc_addButton);

    }

    public JFormattedTextField getDayField() {
        return dayField;
    }

    public Choice getMonthChoice() {
        return monthChoice;
    }

    public Choice getYearChoice() {
        return yearChoice;
    }

    public Choice getImportanceChoice() {
        return importanceChoice;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
