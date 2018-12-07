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

		AddTask frame = new AddTask();
		frame.setTitle("TodoList - Add Task");
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public AddTask() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel dueDateLabel = new JLabel();
		dueDateLabel.setText("Due Date");
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
		List.of(Month.values()).forEach(e -> monthChoice.add(e.name().toLowerCase()));
		monthChoice.setBounds(171, 11, 93, 20);
		contentPane.add(monthChoice);

		// year
		var yearFormat = NumberFormat.getIntegerInstance();
		var yearFormatter = new NumberFormatter(yearFormat);
		yearFormatter.setValueClass(Integer.class);
		yearFormatter.setAllowsInvalid(false);
		yearFormatter.setMinimum(1);
		var yearField = new JFormattedTextField(yearFormatter);
		yearField.setBounds(271, 12, 75, 20);
		yearField.setColumns(10);
		contentPane.add(yearField);

		var textLabel = new JLabel();
		textLabel.setText("Priority");
		textLabel.setBounds(10, 44, 69, 22);
		contentPane.add(textLabel);

		// importance
		var importanceChoice = new Choice();
		List.of(Importance.values()).forEach(e -> importanceChoice.add(e.name().toLowerCase()));
		importanceChoice.setBounds(89, 44, 151, 20);
		contentPane.add(importanceChoice);

		// description
		var descriptionLabel = new JLabel();
		descriptionLabel.setText("Description");
		descriptionLabel.setBounds(10, 77, 93, 20);
		contentPane.add(descriptionLabel);

		JTextArea txtrTxtarea = new JTextArea();
		txtrTxtarea.setText("");
		txtrTxtarea.setBounds(10, 108, 336, 83);
		contentPane.add(txtrTxtarea);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(256, 215, 89, 23);
		btnAdd.addActionListener(e -> {
			var description = descriptionLabel.getText();
			var importance = Importance.valueOf(importanceChoice.getSelectedItem().toUpperCase());

			var day = Integer.parseInt(dayField.getText());
			var month = Month.valueOf(monthChoice.getSelectedItem().toUpperCase());
			var year = Integer.parseInt(yearField.getText());
			var dueDate = LocalDate.of(year, month, day);

			var creationDate = LocalDate.now();

			var task = new TaskBuilder().setDescription(description).setCompleted(false).setCreationDate(creationDate)
					.setDueDate(dueDate).setImportance(importance).build();

		});
		// ADD BUTTON
		contentPane.add(btnAdd);
	}
}
