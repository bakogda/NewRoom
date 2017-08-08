package clients.editEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import admin.View;

public class EditEventController {

	private EditEventModel edModel;
	private EditEventView edView;
	String usn = View.getLogin();

	
	public EditEventController(EditEventModel edModel, EditEventView edView)
	{
		System.out.println("Edit Event Controller: ");
		edView.aclGet(new GetListener());
		edView.aclSave(new SaveListener());
	}
	
	class GetListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String userid = database.queryDB.getId(usn);
				EditEventModel.getDetails(userid);
			} catch(SQLException e1)
			{
			System.out.println("Error!");
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				
			}
	}
	}
	
	class SaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String eName = (String)EditEventView.eventNames.getSelectedItem();
			String dateEvent = EditEventView.dateField.getText();
			String room = (String)EditEventView.roomList.getSelectedItem();
			String timeFr = (String)EditEventView.timeList1.getSelectedItem();
			String timeTo = (String)EditEventView.timeList2.getSelectedItem();
			String descr = EditEventView.eventDesc.getText();
			
			
			try
			{
			String userid = database.queryDB.getId(usn);
			EditEventModel.saveChanges(dateEvent, timeFr, timeTo, room, descr, userid, eName);
			edView.toggleOff();
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}finally
			{
			}
		}
		}
	
	public void addModel(EditEventModel m) {
		System.out.println("Adding Edit Event Model");
		this.edModel = m;
	}
	public void addView(EditEventView v) {
		System.out.println("Adding Edit Event Model");
		this.edView = v;
	}

}
