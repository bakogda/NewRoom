package clients.removeEvent;

import java.sql.SQLException;


public class RemoveEventClient {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RemoveEventView view = new RemoveEventView();
		view.setTitle("Remove Event Client: ");
		view.setVisible(true);
	}
}
