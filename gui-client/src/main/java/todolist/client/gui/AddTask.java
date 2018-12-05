package todolist.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AddTask extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea txtrPriority;
	private JTextArea txtrDescription;
	private JTextArea txtrTxtarea;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTask frame = new AddTask();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddTask() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrTask = new JTextArea();
		txtrTask.setText("Task");
		txtrTask.setBounds(10, 11, 50, 22);
		contentPane.add(txtrTask);
		txtrTask.setEditable(false);
		
		textField = new JTextField();
		textField.setBounds(86, 13, 161, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea txtrDueDate = new JTextArea();
		txtrDueDate.setText("Due Date");
		txtrDueDate.setBounds(10, 41, 69, 22);
		contentPane.add(txtrDueDate);
		txtrDueDate.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 44, 151, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		txtrPriority = new JTextArea();
		txtrPriority.setText("Priority");
		txtrPriority.setBounds(10, 74, 69, 22);
		txtrPriority.setEditable(false);
		contentPane.add(txtrPriority);
		
		Choice choice = new Choice();
		choice.add("Test1");
		choice.add("test2");
		choice.add("test3");
		choice.setBounds(86, 76, 151, 20);
		contentPane.add(choice);
		
		txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(10, 107, 93, 20);
		txtrDescription.setEditable(false);
		contentPane.add(txtrDescription);
		
		txtrTxtarea = new JTextArea();
		txtrTxtarea.setText("");
		txtrTxtarea.setBounds(10, 138, 414, 61);
		contentPane.add(txtrTxtarea);
		
		JCheckBox chckbxCompleted = new JCheckBox("Completed");
		chckbxCompleted.setBounds(6, 231, 97, 23);
		contentPane.add(chckbxCompleted);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(321, 231, 89, 23);
		contentPane.add(btnAdd);
	}
}
