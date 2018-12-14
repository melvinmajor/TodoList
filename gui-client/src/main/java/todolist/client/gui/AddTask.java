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
	private final MainScreen parent;
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
	public AddTask(MainScreen parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 260);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 94, 4, 66, 151, 93, 0 };
		gbl_contentPane.rowHeights = new int[] { 22, 22, 20, 83, 23, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// day
		var intFormat = NumberFormat.getIntegerInstance();
		var numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(1);
		numberFormatter.setMaximum(31);

		// month
		var monthChoice = new Choice();
		GridBagConstraints gbc_monthChoice = new GridBagConstraints();
		gbc_monthChoice.fill = GridBagConstraints.BOTH;
		gbc_monthChoice.insets = new Insets(0, 0, 5, 5);
		gbc_monthChoice.gridx = 3;
		gbc_monthChoice.gridy = 0;
		contentPane.add(monthChoice, gbc_monthChoice);

		// year
		var yearFormat = NumberFormat.getIntegerInstance();
		var yearFormatter = new NumberFormatter(yearFormat);
		yearFormatter.setValueClass(Integer.class);
		yearFormatter.setAllowsInvalid(false);
		yearFormatter.setMinimum(1);

		JLabel dueDateLabel = new JLabel("Due Date:");
		GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
		gbc_dueDateLabel.fill = GridBagConstraints.BOTH;
		gbc_dueDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dueDateLabel.gridx = 0;
		gbc_dueDateLabel.gridy = 0;
		contentPane.add(dueDateLabel, gbc_dueDateLabel);
		var dayField = new JFormattedTextField(numberFormatter);
		dayField.setColumns(10);
		GridBagConstraints gbc_dayField = new GridBagConstraints();
		gbc_dayField.fill = GridBagConstraints.BOTH;
		gbc_dayField.insets = new Insets(0, 0, 5, 5);
		gbc_dayField.gridwidth = 2;
		gbc_dayField.gridx = 1;
		gbc_dayField.gridy = 0;
		contentPane.add(dayField, gbc_dayField);

		// importance choice
		var importanceChoice = new Choice();
		importanceChoice.add("none");
		var yearField = new JFormattedTextField(yearFormatter);
		yearField.setColumns(10);
		GridBagConstraints gbc_yearField = new GridBagConstraints();
		gbc_yearField.fill = GridBagConstraints.BOTH;
		gbc_yearField.insets = new Insets(0, 0, 5, 0);
		gbc_yearField.gridx = 4;
		gbc_yearField.gridy = 0;
		contentPane.add(yearField, gbc_yearField);

		// importance label
		var textLabel = new JLabel("Importance:");
		GridBagConstraints gbc_textLabel = new GridBagConstraints();
		gbc_textLabel.fill = GridBagConstraints.BOTH;
		gbc_textLabel.insets = new Insets(0, 0, 5, 5);
		gbc_textLabel.gridwidth = 2;
		gbc_textLabel.gridx = 0;
		gbc_textLabel.gridy = 1;
		contentPane.add(textLabel, gbc_textLabel);
		GridBagConstraints gbc_importanceChoice = new GridBagConstraints();
		gbc_importanceChoice.fill = GridBagConstraints.BOTH;
		gbc_importanceChoice.insets = new Insets(0, 0, 5, 5);
		gbc_importanceChoice.gridwidth = 2;
		gbc_importanceChoice.gridx = 2;
		gbc_importanceChoice.gridy = 1;
		contentPane.add(importanceChoice, gbc_importanceChoice);

		// description
		var descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.fill = GridBagConstraints.BOTH;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridwidth = 2;
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 2;
		contentPane.add(descriptionLabel, gbc_descriptionLabel);

		var descriptionArea = new JTextArea();
		GridBagConstraints gbc_descriptionArea = new GridBagConstraints();
		gbc_descriptionArea.fill = GridBagConstraints.BOTH;
		gbc_descriptionArea.insets = new Insets(0, 0, 5, 0);
		gbc_descriptionArea.gridwidth = 5;
		gbc_descriptionArea.gridx = 0;
		gbc_descriptionArea.gridy = 3;
		contentPane.add(descriptionArea, gbc_descriptionArea);
		List.of(Month.values()).forEach(e -> monthChoice.add(e.name().toLowerCase()));

		// add button
		var addButton = new JButton("Add");
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

			builder.setCompleted(false).setCreationDate(creationDate).setId(parent.nextAvailableId());

			System.out.println(builder.validate());
			
			// TODO send query
			parent.addTask(builder.build());
			System.out.println(builder.build());
			// TODO reset fields
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.BOTH;
		gbc_addButton.gridx = 4;
		gbc_addButton.gridy = 4;
		contentPane.add(addButton, gbc_addButton);
		List.of(Importance.values()).forEach(e -> importanceChoice.add(e.name().toLowerCase()));
	}
}
