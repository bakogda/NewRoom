package clients.mainPanel;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import admin.RunMVC;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.SQLException;
import java.text.DateFormatSymbols;

public class MainCalendar extends JFrame {
	static JLabel lblYear;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox cmbYear;
	//static JFrame this;
	static Container pane;
	static DefaultTableModel mtblCalendar; //Table model
	static JScrollPane stblCalendar; //The scrollpane
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	static JLabel label = new JLabel(getMonth(currentMonth));
	static String dom;
	static String moy;
	static String yod;
	public static String theDate;



	public MainCalendar() {


		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}


		//Prepare frame
		//this = new JFrame ("Main Calendar"); //Create frame
		setSize(773, 520); //Set size to 400x400 pixels
		setLocation(350, 100);
		pane = this.getContentPane(); //Get content pane
		pane.setLayout(null); //Apply null layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lblYear = new JLabel ("Year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<Previous");
		btnNext = new JButton ("Next>>");
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		tblCalendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {//left click to add button
					if (e.getClickCount() == 2) {
						int row = tblCalendar.getSelectedRow();
						int column = tblCalendar.getSelectedColumn();
						dom = tblCalendar.getValueAt(row, column).toString();
						if(currentMonth < 10) {
							moy = "0" + (currentMonth+1) + "";
						}
						else { 
							moy = (currentMonth+1) + "";
						}
						yod = cmbYear.getSelectedItem().toString();
						theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
						System.out.println(theDate);
						
						new NewEventWindow();
					}
				}
				if (e.getButton() == MouseEvent.BUTTON3){//right click twice to see if event on that day 
					if (e.getClickCount() == 2) {
				
						ViewEvents view = null;
						try {
							view = new ViewEvents();
							int row = tblCalendar.getSelectedRow();
							int column = tblCalendar.getSelectedColumn();
							dom = tblCalendar.getValueAt(row, column).toString();
							if(currentMonth < 10) {
								moy = "0" + (currentMonth+1) + "";
							}
							else { 
								moy = (currentMonth+1) + "";
							}
							yod = cmbYear.getSelectedItem().toString();
							theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
							System.out.println(theDate);
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						view.setTitle("View Events");
						view.setVisible(true);
						System.out.println("Right click");
					}
				}


			}});
		
		
		pnlCalendar = new JPanel(null);


		//Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

		//Register action listeners
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());

		//Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(stblCalendar);

		//Set bounds
		pnlCalendar.setBounds(125, 11, 784, 350);
		lblYear.setBounds(10, 316, 80, 20);
		cmbYear.setBounds(80, 316, 80, 20);
		btnPrev.setBounds(10, 25, 102, 25);
		btnNext.setBounds(525, 25, 102, 25);
		stblCalendar.setBounds(10, 50, 617, 255);

		//Make frame visible
		this.setResizable(false);
		this.setVisible(true);

		//Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); //Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
		realMonth = cal.get(GregorianCalendar.MONTH); //Get month
		realYear = cal.get(GregorianCalendar.YEAR); //Get year
		currentMonth = realMonth; //Match month and year
		currentYear = realYear;

		//Add headers
		String[] headers = {"Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			mtblCalendar.addColumn(headers[i]);
		}

		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

		//No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		//Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Set row/column count
		tblCalendar.setRowHeight(38);




		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(285, 25, 81, 25);
		pnlCalendar.add(label);
		label.setText(getMonth(currentMonth));


		JButton addEventButton = new JButton("Logout");
		//	final JFrame f = new JFrame();
		addEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//f.setVisible(false);
				//f.dispose();
				//f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				toggleOff();

				System.out.println("Logged out!");
				RunMVC mainRunMVC = new RunMVC();

			}

			private void toggleOff() {
				setVisible(false);

			}



		});



		addEventButton.setBounds(10, 55, 89, 23);
		this.getContentPane().add(addEventButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(10, 118, 89, 23);
		this.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(10, 180, 89, 23);
		this.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(10, 246, 89, 23);
		this.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(129, 395, 89, 23);
		this.getContentPane().add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(298, 395, 89, 23);
		this.getContentPane().add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(457, 395, 89, 23);
		this.getContentPane().add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setBounds(631, 395, 89, 23);
		this.getContentPane().add(btnNewButton_7);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		//Populate table
		for (int i=realYear-100; i<=realYear+100; i++){
			cmbYear.addItem(String.valueOf(i));

			//Code for adding to and 


		}

		//Refresh calendar
		refreshCalendar (realMonth, realYear); //Refresh calendar
	}




	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}

	public static void refreshCalendar(int month, int year){
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month

		//Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
		if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);}
		cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box


		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}

		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);


			if (column == 0 || column == 6){ //Week-end
				setBackground(new Color(233,243,252)); //blue
				//	setBackground(new Color(255, 220, 220)); //red
			}
			else{ //Week
				setBackground(new Color(255, 255, 255));
			}

			/*	
			 * Ignore this, was trying to find out each individual day of the calendar. just returns the current date 		
				if(currentMonth < 10) {
					theDate = currentYear + "-"+  "0" + (currentMonth+1) + "-" + realDay;
				}
				else { 
					theDate = currentYear + "-"+ (currentMonth+1) + "-" +  realDay;
				} 
				System.out.println("THE DATE: " + theDate);

			 */

			if (value != null){
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
					setBackground(new Color(220, 220, 255));
				}

			}
			/*
			 * 			try {
				if(database.queryDB.isEvent("2015-05-21") == true) {
					setBackground(new Color(255,220,220));
				}
			} catch (SQLException e) {
				System.out.println("Error with background colour, no date found?");
				e.printStackTrace();
			}
			 */

			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	static class btnPrev_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 0){ //Back one year
				currentMonth = 11;
				currentYear -= 1;
				label.setText(getMonth(currentMonth));
			}
			else{ //Back one month
				currentMonth -= 1;
				label.setText(getMonth(currentMonth));
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	static class btnNext_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 11){ //Foward one year
				currentMonth = 0;
				currentYear += 1;
				label.setText(getMonth(currentMonth));
			}
			else{ //Foward one month
				currentMonth += 1;
				label.setText(getMonth(currentMonth));
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	static class cmbYear_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (cmbYear.getSelectedItem() != null){
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}

	}



}