package clients.adminPanel;


public class AdminController {
	AdminModel model;
	AdminView view;
	
	public AdminController(){
		System.out.println("admin controller");
	}
	
//	public void actionPerformed(ActionEvent e){
//		AddUserView auView = new AddUserView();
//		auView.setTitle("Add User");
//		auView.setVisible(true);
//	}
	
	public void addModel(AdminModel m){
		this.model = m;
	}
	
	public void addView(AdminView v){
		this.view = v;
	}
}
