package clients.viewUsers;

public class ViewUsersController {
	ViewUsersModel rsModel;
	ViewUsersView rsView;
	
	public ViewUsersController(){
		System.out.println("view users controller");
	}
	
	public void addModel(ViewUsersModel m){
		this.rsModel = m;
	}
	
	public void addView(ViewUsersView v){
		this.rsView = v;
	}
}
