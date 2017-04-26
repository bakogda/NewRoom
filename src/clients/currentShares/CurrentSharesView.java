package clients.currentShares;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CurrentSharesView extends JFrame{

	/**
	 * Meow
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private JLabel sm_enterNameLabel = new JLabel("Enter name here: ");
	private JTextField sm_enterName = new JTextField(10);//enter the staff you want to search for!
	private JButton searchButton = new JButton("Search");
	private JTextArea detailsArea = new JTextArea(7,25);
	private JButton request = new JButton("Request");
	private JButton share = new JButton("Share");
	private JButton cancel = new JButton("Cancel");
	
	
	public CurrentSharesView(){
//		super("Share Management");
//		setLayout(new GridBagLayout());
		JPanel shareView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(450, 300);
		detailsArea.setEditable(false);
		
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5,5,5,5);//padding
	
		c.gridx = 0;
		c.gridy = 0;
		shareView.add(sm_enterNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		shareView.add(sm_enterName, c);
		
		c.gridx = 3;
		c.gridy = 0;
		shareView.add(searchButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		
		c.gridwidth = 4;
		c.gridheight = 7;
		c.fill = GridBagConstraints.BOTH;
		shareView.add(detailsArea, c);
		
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;//override the width back to 1
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		shareView.add(request, c);
		
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;//override the width back to 1
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		shareView.add(share, c);
		
		c.gridx = 3;
		c.gridy = 11;
		c.gridwidth = 1;//override the width back to 1
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		shareView.add(cancel, c);
		
		this.add(shareView);
	}
	
	/*
	 * there needs to be:
	 * a listener for the request button, where the 
	 */
	
}
