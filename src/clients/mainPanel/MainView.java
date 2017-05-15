package clients.mainPanel;

import clients.share.*;
import client.historyEvent.*;
import clients.currentShares.CurrentSharesView;
import clients.editEvent.EditEventView;
import clients.eventNotes.EventNotesView;
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
	//LABELS
	private JLabel shareLabel = new JLabel("Share Management: ");
	private JLabel createInvitationLabel = new JLabel("Create Invitation: ");
	private JLabel notificationsLabel = new JLabel("Notification(s): ");
	private JLabel myEventsLabel = new  JLabel("My Event(s): ");
	private JLabel historyEventLabel = new JLabel("Past Event(s): ");
	private JLabel editEventLabel = new JLabel("Edit Your Event");
	private JLabel eventNotesLabel = new JLabel("View Your Event Notes");
	//BUTTONS
	private JButton shareButton = new JButton("Invite Users");
	private JButton addUserButton = new JButton("Add");
	private JButton removeUserButton = new JButton("Remove");
	private JButton createInvitationsButton = new JButton("Create");
	private JButton notificationsButton = new JButton("Manage");
	private JButton myEvents	= new JButton("Events");
	private JButton historyEvent = new JButton("Event(s):");
	private JButton editEvent = new JButton("Edit Event");
	private JButton eventNotes = new JButton("View Notes");

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
				sView.setTitle("Invite User(s)");
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
				CurrentSharesView sView = new CurrentSharesView();
				sView.setTitle("Notification of new Invitations");
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
		
		c.gridx = 0;
		c.gridy = 7;
		mainView.add(editEventLabel, c);
		
		c.gridx = 1;
		c.gridy = 7;
		mainView.add(editEvent, c);
		
		editEvent.addActionListener(new ActionListener(){
			@Override
			
			public void actionPerformed(ActionEvent e)
			{
				EditEventView editEvent = null;
				editEvent = new EditEventView();
				editEvent.setTitle("Edit Event");
				editEvent.setVisible(true);
				}
				
		});
		
		c.gridx = 0;
		c.gridy = 8;
		mainView.add(eventNotesLabel, c);
		
		c.gridx = 1;
		c.gridy = 8;
		mainView.add(eventNotes, c);
		
		eventNotes.addActionListener(new ActionListener(){
			@Override
			
			public void actionPerformed(ActionEvent e)
			{
				EventNotesView eventNotes = null;
				eventNotes = new EventNotesView();
				eventNotes.setTitle("Event Notes");
				eventNotes.setVisible(true);
			}
		});
		
		this.add(mainView);
	}
}
