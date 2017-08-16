package admin;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class adminControl extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField_1;

	private JTextArea userLabel = new JTextArea(4, 20);
	private JButton addUserButton = new JButton("Add User");
	private JButton editUserButton = new JButton("Edit User");
	private JButton viewUsersButton = new JButton("View Users");
	private JButton removeUserButton = new JButton("Remove User");

	private JLabel resetPassLabel = new JLabel("Reset user's password");
	private JButton resetPassButton = new JButton("Reset Password");
	private JLabel viewLogsLabel = new JLabel("View the logs that monutor user access to the applicaion.");
	private JButton viewLogsButton = new JButton("View Logs");

	private JButton btnLogout = new JButton("Logout");

	/**
	 * Launch the application.This is essentially the client
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					adminControl frame = new adminControl();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	public void toggleOn() {
		this.setVisible(true);
	}

	public void toggleOff() {
		this.setVisible(false);
	}
}
