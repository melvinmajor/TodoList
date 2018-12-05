package todolist.client.gui;

import todolist.client.base.BaseClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MainScreen extends BaseClient {

	private JFrame frame;
	private JTextField textField;

	@Override
	public void run() {
		super.run();

<<<<<<< HEAD
		EventQueue.invokeLater(() -> {
			try {
				MainScreen window = new MainScreen();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
=======
		this.frame.setVisible(true);
>>>>>>> 50cab2a73d1222eccec800eb403aaa3cce4c3dbd
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
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
		txtrID.setEditable(false);
		frame.getContentPane().add(txtrID);
		
		
		JTextArea txtrTask = new JTextArea();
		txtrTask.setText("Task");
		txtrTask.setBounds(109, 79, 98, 23);
		txtrTask.setEditable(false);
		frame.getContentPane().add(txtrTask);
		
		JTextArea txtrDescr = new JTextArea();
		txtrDescr.setText("Description");
		txtrDescr.setBounds(210, 79, 89, 23);
		txtrDescr.setEditable(false);
		frame.getContentPane().add(txtrDescr);
		
		JTextArea txtrDueDate = new JTextArea();
		txtrDueDate.setText("Due Date");
		txtrDueDate.setBounds(394, 79, 99, 23);
		txtrDueDate.setEditable(false);
		frame.getContentPane().add(txtrDueDate);
		
		JTextArea txtrImportance = new JTextArea();
		txtrImportance.setText("Importance");
		txtrImportance.setBounds(304, 79, 89, 22);
		txtrImportance.setEditable(false);
		frame.getContentPane().add(txtrImportance);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(418, 304, 89, 23);
		frame.getContentPane().add(btnExit);
	}

	@Override
	public void onConnectionError() {

	}

	@Override
	public void onExit() {

	}
}
