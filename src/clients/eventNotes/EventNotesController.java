package clients.eventNotes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.View;
import database.DesEncrypter;

public class EventNotesController {

	private EventNotesModel enModel;
	private EventNotesView enView;
	String notesToDecrypt = new String();

	public EventNotesController(EventNotesModel enModel, EventNotesView enView) {

		System.out.println("Event Notes Controller:");
		enView.aclGet(new GetListener());
		enView.aclSave(new SaveListener());
	}

	class GetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String eName = (String) EventNotesView.eventNames.getSelectedItem();
			String eiName = (String) EventNotesView.ieventNames.getSelectedItem();
			String eventID = null;
			String usn = View.getLogin();
			String userid = null;
			String decryptedString = DesEncrypter.decrypt(notesToDecrypt, "AES");

			if (eName != null && eiName == null) {
				try {
					userid = database.queryDB.getId(usn);
					eventID = EventNotesModel.checkEventID(eiName, eName);
					enView.notes.setText("");
					notesToDecrypt = EventNotesModel.getNotes(eventID, userid);
					enView.notes.append(decryptedString);
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				} finally {

				}
			} else if (eName == null && eiName != null) {
				try {
					userid = database.queryDB.getId(usn);
					eventID = EventNotesModel.checkEventID(eiName, eName);
					enView.notes.setText("");
					notesToDecrypt = EventNotesModel.getNotes(eventID, userid);
					enView.notes.setText(decryptedString);
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {

				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Choose one event! & One event only");
				enView.notes.setText("");
			}
		}
	}

	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String eName = (String) EventNotesView.eventNames.getSelectedItem();
			String eiName = (String) EventNotesView.ieventNames.getSelectedItem();
			String eNotes = enView.notes.getText();
			String eventID = null;
			String usn = View.getLogin();
			String userid = null;
			String notesEnc = DesEncrypter.encrypt(eNotes, "AES");

			try {
				userid = database.queryDB.getId(usn);
				eventID = EventNotesModel.checkEventID(eiName, eName);
			} catch (SQLException e1) {
				System.out.println("Error!");
				e1.printStackTrace();
				// TODO Auto-generated catch block
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {

			}

			if (eName == null && eiName == null) {
				JOptionPane.showMessageDialog(null, "Please Choose only one event!");
			} else if (eName != null && eiName != null) {
				JOptionPane.showMessageDialog(null, "Please Choose only one event!");
			} else if (eName != null && eiName == null) {
				try {
					EventNotesModel.saveNotes(userid, eventID, notesEnc);
					enView.toggleOff();
				} catch (SQLException | ClassNotFoundException e1) {
					System.out.println("Error!");
					e1.printStackTrace();
				} finally {

				}
			} else if (eName == null && eiName != null) {
				try {
					EventNotesModel.saveNotes(userid, eventID, notesEnc);
					enView.toggleOff();
				} catch (SQLException | ClassNotFoundException e1) {
					System.out.println("Error!");
					e1.printStackTrace();
				} finally {

				}
			}
		}
	}

	public void addModel(EventNotesModel m) {
		System.out.println("Adding Event Notes Model");
		this.enModel = m;
	}

	public void addView(EventNotesView v) {
		System.out.println("Adding Event Notes View");
		this.enView = v;
	}

}
