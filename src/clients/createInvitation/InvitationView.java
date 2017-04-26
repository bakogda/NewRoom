package clients.createInvitation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InvitationView extends JFrame{

	/**
	 * Meow
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private JLabel eventLabel = new JLabel("Event:");
	private JTextArea eventArea = new JTextArea(1,10);
	private JLabel usernameLabel = new JLabel("Username:");
	private JTextField username = new JTextField(10);
	private JButton add = new JButton("+");
	private JButton remove = new JButton("-");
	private JLabel cgLabel = new JLabel("Current Guests");
	private JTextArea cgArea = new JTextArea(7,20);
	private JButton done = new JButton("Done");
	
	
	public InvitationView(){
		JPanel shareView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(450, 300);
		eventArea.setEditable(false);
		
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5,5,5,5);//padding
	
		c.gridx = 0;
		c.gridy = 0;
		shareView.add(eventLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		shareView.add(eventArea, c);
		
		c.gridx = 0;
		c.gridy = 1;
		shareView.add(usernameLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		shareView.add(username, c);
		
		c.gridx = 2;
		c.gridy = 1;
		shareView.add(add, c);
		
		c.gridx = 3;
		c.gridy = 1;
		shareView.add(remove, c);
		
		c.gridx = 0;
		c.gridy = 2;
		shareView.add(cgLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.gridheight = 6;
		c.fill = GridBagConstraints.BOTH;
		shareView.add(cgArea, c);
		
		c.gridx = 0;
		c.gridy = 11;
		shareView.add(done, c);
		
		this.add(shareView);
	}	
}
