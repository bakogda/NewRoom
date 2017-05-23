package clients.removeUser;

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

public class RemoveUserView extends JFrame{


	private static final long serialVersionUID = 1L;
	
	//create all objects on which will be on the panel
	private JLabel usernameLabel = new JLabel("Username: ");
	private JTextField username = new JTextField(10);
	private JLabel reasonLabel = new JLabel("Reason: ");
	private JTextField reason = new JTextField(10);//the reason isn't added to the db ( just aesthetics)
	private Button remove = new Button("Remove");
	private Button cancel = new Button("Cancel");
	
	
	public RemoveUserView(){
		JPanel removeView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(450, 300);
		
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5,5,5,5);//padding
	
		c.gridx = 0;
		c.gridy = 0;
		removeView.add(usernameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		removeView.add(username, c);
		
		c.gridx = 0;
		c.gridy = 1;
		removeView.add(reasonLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		removeView.add(reason, c);
		
		c.gridx = 0;
		c.gridy = 2;
		removeView.add(remove, c);
		
		c.gridx = 1;
		c.gridy = 2;
		removeView.add(cancel, c);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		this.add(removeView);
	}
	
	public void aclRemove(ActionListener controller){
		System.out.println("Remove button action listener: ");
		remove.addActionListener(controller);
	}
	
	public String getUsername(){
		return username.getText();
	}
	
	public void toggleOff(){
		this.setVisible(false);
	}
	
	public void toggleOn(){
		this.setVisible(true);
	}

}
