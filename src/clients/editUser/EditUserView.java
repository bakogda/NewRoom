package clients.editUser;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EditUserView extends JFrame {

	/**
	 * Bako Gdaniec
	 */
	private static final long serialVersionUID = 1L;// stops eclipse from
													// complaining
	// create all objects which will be on the panel
	// LABELS
	private JLabel firstnameLabel = new JLabel("Firstname: ");
	private JLabel lastnameLabel = new JLabel("Lastname: ");
	// private JLabel dobLabel = new JLabel("Date of Birth: ");
	private JLabel userTypeLabel = new JLabel("User type: ");
	private JLabel usernameLabel = new JLabel("Username: ");

	// TEXT FIELDS
	// there could be a button to automatically generate the username from the
	// first,
	// lastname and a random number
	// private JButton genUsernameButton = new JButton("Generate Username");
	private JTextField firstname = new JTextField(10);
	private JTextField lastname = new JTextField(10);
	private JTextField dob = new JTextField(10);
	private JTextField email = new JTextField(10);
	private JTextField userType = new JTextField(10);
	private JTextField username = new JTextField(10);

	// BUTTONS
	private Button getDet = new Button("Get Details");
	private Button change = new Button("Change");
	private JButton cancel = new JButton("Cancel");

	public EditUserView() {
		JPanel auView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(400, 360);

		/*
		 * laying it out nicely
		 */
		GridBagConstraints c = new GridBagConstraints();// would like to have an
														// anchor
		c.insets = new Insets(5, 5, 5, 5);// padding

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(usernameLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(username, c);

		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(getDet, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(firstnameLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(firstname, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(lastnameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(lastname, c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(userTypeLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(userType, c);

		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(change, c);

		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(cancel, c);

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});

		getDet.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				try {
					firstname.setText(EditUserModel.getFN(getUsername()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					lastname.setText(EditUserModel.getLN(getUsername()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					userType.setText(EditUserModel.getUT(getUsername()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.add(auView);
	}

	// action listener for the create button. This doesn't do anything
	public void aclChange(ActionListener controller) {
		System.out.println("Change button action listener: ");
		change.addActionListener(controller);
	}

	// get the firstname
	public String getFirstname() {
		return firstname.getText();
	}

	// get the lastname
	public String getLastname() {
		return lastname.getText();
	}

	// get the username
	public String getUsername() {
		return username.getText();
	}

	// get the user type
	public String getUserType() {
		return userType.getText();
	}

	// easy way to close a window
	public void toggleOff() {
		this.setVisible(false);
	}

	// easy way to open a window
	public void toggleOn() {
		this.setVisible(true);
	}

}
