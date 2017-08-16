package clients.resetPassword;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ResetPasswordView extends JFrame {

	/**
	 * Bako Gdaniec
	 */
	private static final long serialVersionUID = 1L;
	// create all objects on which will be on the panel
	private JLabel unLabel = new JLabel("Username: ");
	private JLabel pwLabel = new JLabel("Password: ");
	private JLabel pw2Label = new JLabel("Retype Password: ");
	private JTextField un = new JTextField(10);
	private JTextField pw = new JTextField(10);
	private JTextField pw2 = new JTextField(10);

	private Button reset = new Button("Reset");
	private Button cancel = new Button("Cancel");

	public ResetPasswordView() {
		JPanel resetView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(450, 300);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);// padding

		c.gridx = 0;
		c.gridy = 0;
		resetView.add(unLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		resetView.add(un, c);

		c.gridx = 0;
		c.gridy = 1;
		resetView.add(pwLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		resetView.add(pw, c);

		c.gridx = 0;
		c.gridy = 2;
		resetView.add(pw2Label, c);

		c.gridx = 1;
		c.gridy = 2;
		resetView.add(pw2, c);

		c.gridx = 0;
		c.gridy = 3;
		resetView.add(reset, c);

		c.gridx = 1;
		c.gridy = 3;
		resetView.add(cancel, c);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});

		this.add(resetView);
	}

	public void aclReset(ActionListener controller) {
		System.out.println("Reset button action listener: ");
		reset.addActionListener(controller);
	}

	public String getUsername() {
		return un.getText();
	}

	public String getPass() {
		return pw.getText();
	}

	public String get2Pass() {
		return pw2.getText();
	}

	public void toggleOff() {
		this.setVisible(false);
	}

	public void toggleOn() {
		this.setVisible(true);
	}

}
