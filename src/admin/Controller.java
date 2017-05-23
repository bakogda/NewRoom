package admin;
import java.sql.SQLException;

import clients.adminPanel.AdminView;

class Controller implements java.awt.event.ActionListener {


	Model model;
	View view;

	Controller() {	
		System.out.println ("Login Controller:");
	} 

	//when login button is pushed
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e){
		System.out.println("Controller: acting on Model");

		String usn = View.getLogin();
		String pwd = view.getPass();
		String type = "1";
		
		try {
			/*
			 * if(database.queryDB.checkUserType(type)==true){
			 * ask the admin whether they want to login as a user or an admin
			 * }
			 */
			if(database.queryDB.checkUser(usn) == true && database.queryDB.checkPass(database.hash.sha1(usn + pwd)) == true) {
				if(usn.equals("admin")){
					
					AdminView aView = new AdminView();
					aView.setTitle("Admin Control Panel");
					aView.setVisible(true);
					view.toggleOff();
					
					//open adminControl GUI && Hide login screen
					adminControl sView = new adminControl();
				sView.setTitle("Admin Control Panel");
				sView.setVisible(false);
				view.toggleOff();
				}
				else {
					//open normal user GUI here
					clients.mainPanel.Main.runMainView();
					view.toggleOff();
					
					
					
				}
				System.out.println("Username " + usn + " has successfully logged into the system!");
			}
			else if(database.queryDB.checkUser(usn) == true && database.queryDB.checkPass(pwd) == false) {
				System.out.println("Wrong password supplied!");
			}
			else if(database.queryDB.checkUser(usn) == false) {
				System.out.println("Couldn't find the username supplied: " + usn);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("Error: login failed");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	} 

	public void addView(View v){
		System.out.println("Controller: adding view");
		this.view = v;
	} 


}