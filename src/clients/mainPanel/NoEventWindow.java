package clients.mainPanel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class NoEventWindow extends JFrame {
	public static void main(String[] args) {
		NoEventWindow frameTabel = new NoEventWindow();
	}

	String[] times = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	String [] rooms = {"Room 1","Room 2","IT Room"};
	JButton cancel = new JButton("OK!");
	JPanel panel = new JPanel();
	JLabel eventTitleLabel = new JLabel("No events on this day!");

	NoEventWindow(){
		super("No events on this day!");
		setSize(419,198);
		setLocation(750,250);
		panel.setLayout (null);
	
		//JTextField text = new JTextField(20);
		final JFrame f = new JFrame();
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
				f.setVisible(false);
				
			}
		});
		cancel.setBounds(166, 105, 75, 30);
		eventTitleLabel.setBounds(136,28,148,20);
		panel.add(cancel);
		panel.add(eventTitleLabel);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}
