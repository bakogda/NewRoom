package clients.resetPassword;

import java.awt.event.ActionListener;

import java.sql.SQLException;

public class ResetPasswordController implements ActionListener{
	
	ResetPasswordModel rsModel;
	ResetPasswordView rsView;
	
	public ResetPasswordController(){
		System.out.println("reset password controller");
	}
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e){
		String un = rsView.getUsername();
		String pw = rsView.getPass();
		String pw2 = rsView.get2Pass();
		
		if(un.isEmpty() || pw.isEmpty() || pw2.isEmpty()){
			System.out.println("All fields must be filled in");
		}
		else if(!pw.equals(pw2)){
			System.out.println("You must enter the same password twice");
		}
		else {
			try {
				ResetPasswordModel.resetPassword(un,pw);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rsView.toggleOff();
			
		}
		
	}
	
	public void addModel(ResetPasswordModel m){
		this.rsModel = m;
	}
	
	public void addView(ResetPasswordView v){
		this.rsView = v;
	}
}
