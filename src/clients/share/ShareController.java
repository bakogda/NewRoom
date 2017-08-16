package clients.share;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import admin.View;
import database.dBV;

public class ShareController implements ActionListener {

	private ShareModel shModel;
	private ShareView shView;

	public ShareController(ShareModel shModel, ShareView shView) {

		System.out.println("Share Controller:");
		shView.aclShare(new ShareListener());
		shView.aclRemove(new RemoveListener());
		shView.aclSearch(new SearchListener());
	}

	class ShareListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ename = (String) shView.eventNames.getSelectedItem();
			String name1 = shView.getFirstName();
			String name2 = shView.getSecondName();
			String name3 = shView.getThirdName();
			String name4 = shView.getFourthName();
			String name5 = shView.getFithName();
			String name6 = shView.getSixthName();
			String usn = View.getLogin();
			String userid = null;
			String eventID = null;
			try {
				userid = database.queryDB.getId(usn);
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				eventID = ShareModel.checkEventId(ename, userid);
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			ArrayList<String> list = new ArrayList<String>();
			if (name1 != null && !name1.isEmpty()) {
				list.add(name1);
			}
			if (name2 != null && !name2.isEmpty()) {
				list.add(name2);
			}
			if (name3 != null && !name3.isEmpty()) {
				list.add(name3);
			}
			if (name4 != null && !name4.isEmpty()) {
				list.add(name4);
			}
			if (name5 != null && !name5.isEmpty()) {
				list.add(name5);
			}
			if (name6 != null && !name6.isEmpty()) {
				list.add(name6);
			}

			try {
				for (String name : list) {
					ShareModel.inviteUser(eventID, userid, name);
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			shView.toggleOff();
		}
	}

	class RemoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String eName = (String) shView.eventNames.getSelectedItem();
			String userName = shView.removeUser.getText();
			String userid = null;
			String usn = View.getLogin();
			String eventID = null;

			try {
				userid = database.queryDB.getId(usn);
				eventID = ShareModel.checkEventId(eName, userid);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} finally {

			}
			try {
				ShareModel.removeUser(eventID, userName);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class SearchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String eName = (String) shView.eventNames.getSelectedItem();
			ShareView.sm_enterName.setText(null);
			ShareView.sm_enterName2.setText(null);
			ShareView.sm_enterName3.setText(null);
			ShareView.sm_enterName4.setText(null);
			ShareView.sm_enterName5.setText(null);
			ShareView.sm_enterName6.setText(null);
			String usn = View.getLogin();
			String userid = null;
			String eventID = null;

			try {
				userid = database.queryDB.getId(usn);
				String SQL_statement = "SELECT EVENT_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" + userid
						+ "' AND TITLE='" + eName + "'";

				Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

				// create a new statement
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_statement);
				while (resultSet.next()) {
					eventID = resultSet.getString("EVENT_ID");
				}

				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "ERROR");
			} finally {

			}
			try {
				ShareView.sm_enterName.setText(shModel.getFirstInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName.setEditable(false);
				} else {
					ShareView.sm_enterName.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ShareView.sm_enterName2.setText(shModel.getSecondInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName2.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName2.setEditable(false);
				} else {
					ShareView.sm_enterName2.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ShareView.sm_enterName3.setText(shModel.getThirdInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName3.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName3.setEditable(false);

				} else {
					ShareView.sm_enterName3.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ShareView.sm_enterName4.setText(shModel.getFourthInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName4.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName4.setEditable(false);

				} else {
					ShareView.sm_enterName4.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ShareView.sm_enterName5.setText(shModel.getFithInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName5.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName5.setEditable(false);

				} else {
					ShareView.sm_enterName5.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ShareView.sm_enterName6.setText(shModel.getSixthInvitation(userid, eventID, eName));
				String data = ShareView.sm_enterName6.getText().trim();
				if (!data.equals("")) {
					ShareView.sm_enterName6.setEditable(false);
				} else {
					ShareView.sm_enterName6.setEditable(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void addModel(ShareModel m) {
		System.out.println("Adding Share Model");
		this.shModel = m;
	}

	public void addView(ShareView v) {
		System.out.println("Adding Share View");
		this.shView = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
