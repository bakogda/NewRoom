package clients.mainPanel;

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

import admin.RunMVC;
import client.historyEvent.HistoryEventView;
import clients.currentShares.CurrentSharesView;
import clients.editEvent.EditEventController;
import clients.editEvent.EditEventModel;
import clients.editEvent.EditEventView;
import clients.eventNotes.EventNotesController;
import clients.eventNotes.EventNotesModel;
import clients.eventNotes.EventNotesView;
import clients.events.EventsView;
import clients.share.ShareController;
import clients.share.ShareModel;
import clients.share.ShareView;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	// LABELS
	private JLabel shareLabel = new JLabel("Share Management: ");
	private JLabel notificationsLabel = new JLabel("Notification(s): ");
	private JLabel myEventsLabel = new JLabel("My Event(s): ");
	private JLabel historyEventLabel = new JLabel("Past Event(s): ");
	private JLabel editEventLabel = new JLabel("Edit Your Event");
	private JLabel eventNotesLabel = new JLabel("View Your Event Notes");
	private JLabel openCalendarLabel = new JLabel("View Calendar");
	// BUTTONS
	private JButton openCalendar = new JButton("Open Calendar");
	private JButton shareButton = new JButton("Invite Users");
	private JButton notificationsButton = new JButton("Manage");
	private JButton myEvents = new JButton("Events");
	private JButton historyEvent = new JButton("Event(s):");
	private JButton editEvent = new JButton("Edit Event");
	private JButton eventNotes = new JButton("View Notes");
	private JButton logOut = new JButton("Logout");

	MainView() {
		JPanel mainView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(6, 6, 6, 6);// padding

		// SHARE MANAGEMENT
		c.gridx = 0;
		c.gridy = 2;
		mainView.add(shareLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		mainView.add(shareButton, c);

		// Open Calendar
		c.gridx = 0;
		c.gridy = 1;
		mainView.add(openCalendarLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		mainView.add(openCalendar, c);

		// NOTIFICATIONS
		c.gridx = 0;
		c.gridy = 4;
		mainView.add(notificationsLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		mainView.add(notificationsButton, c);

		c.gridx = 0;
		c.gridy = 5;
		mainView.add(myEventsLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		mainView.add(myEvents, c);

		c.gridx = 0;
		c.gridy = 6;
		mainView.add(historyEventLabel, c);

		c.gridx = 1;
		c.gridy = 6;
		mainView.add(historyEvent, c);

		c.gridx = 0;
		c.gridy = 7;
		mainView.add(editEventLabel, c);

		c.gridx = 1;
		c.gridy = 7;
		mainView.add(editEvent, c);

		c.gridx = 0;
		c.gridy = 8;
		mainView.add(eventNotesLabel, c);

		c.gridx = 1;
		c.gridy = 8;
		mainView.add(eventNotes, c);

		c.gridx = 1;
		c.gridy = 9;
		mainView.add(logOut, c);

		// ACTION LISTENERS

		shareButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShareModel shModel = new ShareModel();
				ShareView shView = new ShareView();
				ShareController shCont = new ShareController(shModel, shView);
				shCont.addModel(shModel);
				shCont.addView(shView);
				shView.setTitle("Invite User(s)");
				shView.setVisible(true);
			}
		});

		editEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditEventModel edModel = new EditEventModel();
				EditEventView edView = new EditEventView();
				EditEventController edCont = new EditEventController(edModel, edView);
				edCont.addModel(edModel);
				edCont.addView(edView);
				edView.setTitle("Edit Event");
				edView.setVisible(true);
			}
		});

		openCalendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainCalendar cView = new MainCalendar();
				cView.setTitle("Calendar");

			}
		});

		notificationsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CurrentSharesView sView = new CurrentSharesView();
				sView.setTitle("Notification of new Invitations");
				sView.setVisible(true);
			}

		});

		myEvents.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventsView eView;
				try {
					eView = new EventsView();
					eView.setTitle("My Upcoming Events");
					eView.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});

		historyEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistoryEventView histEvent = null;
				try {
					histEvent = new HistoryEventView();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				histEvent.setTitle("Past Event(s)");
				histEvent.setVisible(true);
			}
		});

		eventNotes.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				EventNotesModel enModel = new EventNotesModel();
				EventNotesView enView = new EventNotesView();
				EventNotesController enCont = new EventNotesController(enModel, enView);
				enCont.addModel(enModel);
				enCont.addView(enView);
				enView.setTitle("Event Notes");
				enView.setVisible(true);
			}
		});

		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// f.setVisible(false);
				// f.dispose();
				// f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				toggleOff();

				System.out.println("Logged out!");
				RunMVC mainRunMVC = new RunMVC();
				MainCalendar cView = new MainCalendar();
				cView.setVisible(false);
			}

			private void toggleOff() {
				setVisible(false);

			}
		});

		this.add(mainView);
	}
}
