package clients.mainPanel;

import clients.share.*;
/*import clients.addUser.*;
import clients.createInvitation.*;
import clients.removeUser.*;
import clients.notifications.*;
*/

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel shareLabel = new JLabel("Share Management: ");
	private JLabel createInvitationLabel = new JLabel("Create Invitation: ");
	private JLabel notificationsLabel = new JLabel("Notification(s): ");
	private JButton shareButton = new JButton("Share");
	private JButton addUserButton = new JButton("Add");
	private JButton removeUserButton = new JButton("Remove");
	private JButton createInvitationsButton = new JButton("Create");
	private JButton notificationsButton = new JButton("Manage");

	MainView(){
		JPanel mainView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450, 300);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);//padding
		
		//SHARE MANAGEMENT
		c.gridx = 0;
		c.gridy = 0;
		mainView.add(shareLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		mainView.add(shareButton, c);
		
		shareButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ShareView sView = new ShareView();
				sView.setTitle("Share Management");
				sView.setVisible(true);
			}
		});
		


		
		 /* 
		 
	     * //REMOVE USER
	 
		 
		c.gridx = 0;
		c.gridy = 2;
		Component removeUserLabel = null;
		mainView.add(removeUserLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		mainView.add(removeUserButton, c);
		
		removeUserButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ShareView sView = new ShareView();
				sView.setTitle("Share Management");
				sView.setVisible(true);
			}
			
		});
		*/
	
		
		//CREATE INVITATIONS
		c.gridx = 0;
		c.gridy = 3;
		mainView.add(createInvitationLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		mainView.add(createInvitationsButton, c);
		
		createInvitationsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ShareView sView = new ShareView();
				sView.setTitle("Create Event");
				sView.setVisible(true);
				clients.mainPanel.Main.runMainView();
				
				clients.NewEventWindow newEvent = new clients.NewEventWindow();
				
				
				
			}
			
		});
		
		//NOTIFICATIONS
		c.gridx = 0;
		c.gridy = 4;
		mainView.add(notificationsLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		mainView.add(notificationsButton, c);
		
		notificationsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ShareView sView = new ShareView();
				sView.setTitle("Share Management");
				sView.setVisible(true);
			}
			
		});
		
		this.add(mainView);
	}
}
