package clients.adminPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admin.RunMVC;
import clients.addUser.AddUserController;
import clients.addUser.AddUserModel;
import clients.addUser.AddUserView;
import clients.editUser.EditUserController;
import clients.editUser.EditUserModel;
import clients.editUser.EditUserView;
import clients.removeEvent.RemoveEventController;
import clients.removeEvent.RemoveEventModel;
import clients.removeEvent.RemoveEventView;
import clients.removeUser.RemoveUserController;
import clients.removeUser.RemoveUserModel;
import clients.removeUser.RemoveUserView;
import clients.resetPassword.ResetPasswordController;
import clients.resetPassword.ResetPasswordModel;
import clients.resetPassword.ResetPasswordView;
import clients.viewUsers.ViewUsersController;
import clients.viewUsers.ViewUsersModel;
import clients.viewUsers.ViewUsersView;

public class AdminView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addUserButton 		= new JButton("Add User");
	private JButton editUserButton 		= new JButton("Edit User");
	private JButton viewUsersButton 	= new JButton("View Users");
	private JButton removeUserButton 	= new JButton("Remove User");
	private JButton resetPassButton 	= new JButton("Reset Password");
	private JButton removeEventButton   = new JButton("Remove Event");
	private JButton viewLogsButton 		= new JButton("View Logs");
	private JButton btnLogout 			= new JButton("Logout");
	
	/**
	 * Create the frame.This is essentially the view
	 * @throws SQLException 
	 */
	public AdminView() {
		JPanel adminView = new JPanel(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);//padding
		
		c.gridx = 0; c.gridy = 0;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(addUserButton, c);
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUserModel auModel = new AddUserModel();
				AddUserView auView = new AddUserView();
				AddUserController auCont = new AddUserController();
				auCont.addModel(auModel);
				auCont.addView(auView);
				auView.aclCreate(auCont);
				auView.setTitle("Add User");
				auView.setVisible(true);
			}
		});
		
		
		c.gridx = 0; c.gridy = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(editUserButton, c);
		editUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditUserModel euModel = new EditUserModel();
				EditUserView euView = new EditUserView();
				EditUserController euCont = new EditUserController();
				euCont.addModel(euModel);
				euCont.addView(euView);
				euView.aclChange(euCont);
				euView.setTitle("Edit User");
				euView.setVisible(true);
			}
		});
		
		c.gridx = 0; c.gridy = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(viewUsersButton, c);
		viewUsersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewUsersModel model = new ViewUsersModel();
				ViewUsersView view = new ViewUsersView();
				ViewUsersController cont = new ViewUsersController();
				cont.addModel(model);
				cont.addView(view);
				view.setTitle("View Users");
				view.setVisible(true);
			}
		});
		
		c.gridx = 0; c.gridy = 3;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(removeUserButton, c);
		removeUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveUserModel rModel = new RemoveUserModel();
				RemoveUserView rView = new RemoveUserView();
				RemoveUserController rCont = new RemoveUserController();
				rCont.addModel(rModel);
				rCont.addView(rView);
				rView.aclRemove(rCont);
				rView.setTitle("Remove User");
				rView.setVisible(true);	
			}
		});
		
		c.gridx = 0; c.gridy = 4;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(resetPassButton, c);
		
		resetPassButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetPasswordModel auModel = new ResetPasswordModel();
				ResetPasswordView auView = new ResetPasswordView();
				ResetPasswordController auCont = new ResetPasswordController();
				auCont.addModel(auModel);
				auCont.addView(auView);
				auView.aclReset(auCont);
				auView.setTitle("Reset User Password");
				auView.setVisible(true);
				
			}
		});
		
		c.gridx = 0; c.gridy = 5;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(removeEventButton, c);
		
		removeEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveEventModel rmModel = new RemoveEventModel();
				RemoveEventView rmView = new RemoveEventView();
				RemoveEventController rmCont = new RemoveEventController();
				rmCont.addModel(rmModel);
				rmCont.addView(rmView);
				rmView.aclRemove(rmCont);
				rmView.setTitle("Remove Event");
				rmView.setVisible(true);
				
			}
		});
		
		
		//####----THIS WILL NOT BE IMPLEMENTED YET----####//
		c.gridx = 0; c.gridy = 5;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(viewLogsButton, c);
		
		
		
		c.gridx = 0; c.gridy = 6;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		
		
		
		//LOGOUT BUTTON
		
		
		adminView.add(btnLogout,c);
		btnLogout.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
				System.out.println("Logged out!");
				RunMVC mainRunMVC = new RunMVC();
			}
		});
		
		this.add(adminView);
	}
	
	public void toggleOff(){
		this.setVisible(false);
	}
	
	
//	public void aclAddUser(ActionListener controller){
//		System.out.println("add user selected");
//		addUserButton.addActionListener(controller);
//	}
	

}


