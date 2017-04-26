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

import javax.swing.JPasswordField;

	class View implements java.util.Observer
	{
		Frame frame 	= new Frame("Login to the calendar!");
		private TextField login = new TextField(15);
		private JPasswordField pwd = new JPasswordField(15);
		private Button button = new Button("Login");
		
		View()
		{
			System.out.println("View()");
			
			frame.add("North", new Label("Enter your username and password here !"));
			frame.add("Center", login);
			Panel panel	= new Panel();
			
			panel.add(login);
			panel.add(pwd);
			panel.add(button);
			frame.add("Center" , panel);
			
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
		
		public String getLogin()
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