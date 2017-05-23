package clients.addUser;

import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddUserController implements ActionListener {
	
	AddUserModel auModel;
	AddUserView auView;
	
	public AddUserController(){
		System.out.println("AddUser controller:");
	}
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e){
		String usn = auView.getUsername();
		String pwd = auView.getPassword();
		String pwd2 = auView.get2Password();
		String fn = auView.getFirstname();
		String ln = auView.getLastname();
		String typ = auView.getUserType();
		
		if(usn.isEmpty() || pwd.isEmpty() || pwd2.isEmpty() || fn.isEmpty() || ln.isEmpty() || typ.isEmpty() ){
			//one or more fields are empty
			//error fill in all fields
			System.out.println("Fill in all fields");
		}
		else if(!pwd.equals(pwd2)){
			//error passwords don't match
			System.out.println("Passwords dont match");
		}
		else {
			//all tests passed
			//add user info to db
			try {
				AddUserModel.addUser(usn, pwd, fn, ln, typ);
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			auView.toggleOff();
			
		}
	}
	
	public void addModel(AddUserModel m){
		System.out.println("adding addUser model");
		this.auModel = m;
	}
	
	public void addView(AddUserView v){
		System.out.println("adding addUser view");
		this.auView = v;
	}
	
}
