package admin;
import java.awt.Button;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()

import javax.swing.JLabel;
import javax.swing.JPasswordField;

	public class View implements java.util.Observer
	{
		Frame frame 	= new Frame("Login to the calendar!");
		private static TextField login = new TextField(15);
		private JPasswordField pwd = new JPasswordField(15);
		private Button button = new Button("Login");
		JLabel welcome = new JLabel("Welcome Login to start using the service");
		JLabel loginLabel = new JLabel("Enter your username: ");
		JLabel passLabel = new JLabel("Enter your password: ");
		
		View()
		{
			System.out.println("View()");
			Panel panel	= new Panel();
			panel.setLayout(null);
			loginLabel.setBounds(6, 118, 137, 16);
			
			panel.add(loginLabel);
			passLabel.setBounds(6, 170, 128, 16);
			panel.add(passLabel);
			welcome.setBounds(59, 65, 256, 16);
			panel.add(welcome);
			login.setBounds(157, 118, 190, 22);
			panel.add(login);
			pwd.setBounds(157, 165, 190, 26);
			panel.add(pwd);
			button.setBounds(157, 226, 137, 29);
			panel.add(button);
			frame.add(panel);
			
			frame.addWindowListener(new CloseListener());
			frame.setSize(400,400);
			frame.setLocation(350,350);
			frame.setVisible(true);
			
		}
		
		public void update(Observable obs, Object obj)
		{
			
		}
		
		public void toggleOff()
		{
			frame.setVisible(false);
		}
		
		public void toggleOn() {
			frame.setVisible(true);
		}
		
		public static void shut()
		{
			Frame frame 	= new Frame("");
			frame.dispose();
		}
		
		public static String getLogin()
		{
			return login.getText();
		}
		
		public String getPass()
		{
			String a = new String(pwd.getPassword());
			return a;
		}
		
		public void addController(ActionListener controller)
		{
			System.out.println("View     : adding controller");
			button.addActionListener(controller);
		}
		
		public static class CloseListener extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				e.getWindow().setVisible(false);
				System.exit(0);
			}
		}
	}