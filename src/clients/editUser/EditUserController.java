package clients.editUser;

import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditUserController implements ActionListener {
	
	EditUserModel euModel;
	EditUserView euView;
	
	
	public EditUserController(){
		System.out.println("EditUser controller:");
	}
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e){
		String usn = euView.getUsername();
		String fn = euView.getFirstname();
		String ln = euView.getLastname();
		String typ = euView.getUserType();
		
		if(usn.isEmpty() || fn.isEmpty() || ln.isEmpty() || typ.isEmpty() ){
			//one or more fields are empty
			//error fill in all fields
			System.out.println("Fill in all fields");
		}
		else {
			//all tests passed
			//update user info on db
			try {
				EditUserModel.editUser(usn, fn, ln, typ);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			euView.toggleOff();
		}
	}
	
	public void addModel(EditUserModel m){
		System.out.println("adding editUser model");
		this.euModel = m;
	}
	
	public void addView(EditUserView v){
		System.out.println("adding editUser view");
		this.euView = v;
	}
	
}
