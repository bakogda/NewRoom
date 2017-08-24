package clients.removeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RemoveEventController implements ActionListener {

	RemoveEventModel rmModel;
	RemoveEventView rmView;
	
	public RemoveEventController(RemoveEventModel rmModel, RemoveEventView rmView)
	{
		System.out.println("Remove Event Controller: ");
		rmView.aclRemove(new RemoveListener());
	}
	
	class RemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String event = (String)RemoveEventView.listEvents.getSelectedItem();
			try{
					RemoveEventModel.removeEvent(event);
					rmView.toggleOff();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
	
	
	public void addModel(RemoveEventModel m) {
		System.out.println("Adding removeEvent Model");
		this.rmModel = m;
	}

	public void addView(RemoveEventView v) {
		System.out.println("Adding removeEvent View");
		this.rmView = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
