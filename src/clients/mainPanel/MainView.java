package clients.mainPanel;

import clients.share.*;
import client.historyEvent.*;

import clients.mainPanel.ViewEvents;
//import clients.notifications.*;


import java.awt.Component;
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

public class MainView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel shareLabel = new JLabel("Share Management: ");
	private JLabel createInvitationLabel = new JLabel("Create Invitation: ");
	private JLabel notificationsLabel = new JLabel("Notification(s): ");
	private JLabel myEventsLabel = new  JLabel("My Event(s): ");
	private JLabel historyEventLabel = new JLabel("Past Event(s): ");
	private JButton shareButton = new JButton("Share");
	private JButton addUserButton = new JButton("Add");
	private JButton removeUserButton = new JButton("Remove");
	private JButton createInvitationsButton = new JButton("Create");
	private JButton notificationsButton = new JButton("Manage");
	private JButton myEvents	= new JButton("Events");
	private JButton historyEvent = new JButton("Event(s):");

	MainView(){
		JPanel mainView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(6,6,6,6);//padding
		
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
		c.gridx = 0;
		c.gridy = 5;
		mainView.add(myEventsLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		mainView.add(myEvents, c);
		
		myEvents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEvents myEvent;
				try {
					myEvent = new ViewEvents();
					myEvent.setTitle("My Event(s)");
					myEvent.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				clients.mainPanel.Main.runMainView();
				
			}
		});
		c.gridx = 0;
		c.gridy = 6;
		mainView.add(historyEventLabel, c);
		
		c.gridx = 1;
		c.gridy = 6;
		mainView.add(historyEvent, c);
		
		historyEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					HistoryEventView histEvent = null;
					try {
						histEvent = new HistoryEventView();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					histEvent.setTitle("Past Event(s)");
					histEvent.setVisible(true);
			}
		});
		
		this.add(mainView);
	}
}
