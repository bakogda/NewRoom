package clients.removeUser;

import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RemoveUserController implements ActionListener {

	RemoveUserModel rModel;
	RemoveUserView rView;

	public RemoveUserController() {
		System.out.println("RemoveUser controller:");
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		String un = rView.getUsername();

		if (un.isEmpty()) {
			System.out.println("cant delete anything without a username");
		} else {
			try {
				RemoveUserModel.removeUser(un);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rView.toggleOff();
		}
	}

	public void addModel(RemoveUserModel m) {
		System.out.println("adding removeUser model");
		this.rModel = m;
	}

	public void addView(RemoveUserView v) {
		System.out.println("adding removeUser view");
		this.rView = v;
	}
}
