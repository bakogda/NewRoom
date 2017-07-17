package clients.share;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.View;

public class ShareController implements ActionListener {
	
	ShareModel shModel;
	ShareView shView;
	
	public ShareController()
	{
		System.out.println("Share Controller:");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String ename = (String) shView.eventNames.getSelectedItem();
		String name1 = shView.getFirstName();
		String name2 = shView.getSecondName();
		String name3 = shView.getThirdName();
		String name4 = shView.getFourthName();
		String name5 = shView.getFithName();
		String name6 = shView.getSixthName();
		String usn = View.getLogin();
		String userid = null;
		try {
			userid = database.queryDB.getId(usn);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String eventID = null;
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
		if(name1 != null && !name1.isEmpty())
		{
			list.add(name1);
		}
		if(name2 !=  null && !name2.isEmpty())
		{
			list.add(name2);
		}
		if(name3 !=  null && !name3.isEmpty())
		{
			list.add(name3);
		}
		if(name4 !=  null && !name4.isEmpty())
		{
			list.add(name4);
		}
		if(name5 !=  null && !name5.isEmpty())
		{
			list.add(name5);
		}
		if(name6 != null && !name6.isEmpty())
		{
			list.add(name6);
		}
		
		try{
		for(String name: list)
		{		
		ShareModel.inviteUser(eventID, userid, name);
		}
		}catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		System.out.println("7");
		}
	
	
	
	
	public void addModel(ShareModel m)
	{
		System.out.println("Adding Share Model");
		this.shModel = m;
	}
	
	public void addView(ShareView v)
	{
		System.out.println("Adding Share View");
		this.shView = v;
	}

}
