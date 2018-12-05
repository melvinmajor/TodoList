package todolist.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Menu {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnEditTask = new JButton("Edit Task");
		btnEditTask.setBounds(210, 11, 89, 23);
		frame.getContentPane().add(btnEditTask);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.setBounds(377, 11, 116, 23);
		frame.getContentPane().add(btnDeleteTask);
		
		textField = new JTextField();
		textField.setBounds(10, 102, 483, 167);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(true);
		
		JTextArea txtrID = new JTextArea();
		txtrID.setText("ID");
		txtrID.setBounds(10, 79, 98, 23);
		frame.getContentPane().add(txtrID);
		
		JTextArea txtrTask = new JTextArea();
		txtrTask.setText("Task");
		txtrTask.setBounds(109, 79, 98, 23);
		frame.getContentPane().add(txtrTask);
		
		JTextArea txtrDescr = new JTextArea();
		txtrDescr.setText("Description");
		txtrDescr.setBounds(210, 79, 89, 23);
		frame.getContentPane().add(txtrDescr);
		
		JTextArea txtrDueDate = new JTextArea();
		txtrDueDate.setText("Due Date");
		txtrDueDate.setBounds(394, 79, 99, 23);
		frame.getContentPane().add(txtrDueDate);
		
		JTextArea txtrImportance = new JTextArea();
		txtrImportance.setText("Importance");
		txtrImportance.setBounds(304, 79, 89, 22);
		frame.getContentPane().add(txtrImportance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(418, 304, 89, 23);
		frame.getContentPane().add(btnExit);
	}
}
