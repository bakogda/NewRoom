package clients.mainPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clients.events.EventsView;


public class MainCalendar extends JFrame {
	static JLabel lblYear;
	static JLabel lblMont;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox cmbYear;
	static JComboBox cmbMont;
	// static JFrame this;
	static Container pane;
	static DefaultTableModel mtblCalendar; // Table model
	static JScrollPane stblCalendar; // The scrollpane
	static JPanel pnlCalendar;
	public static int realYear;
	public static int realMonth;
	static int realDay;
	static int currentYear;
	static int currentMonth;
	static JLabel label = new JLabel(getMonth(currentMonth));
	public static String dom;
	public static String moy;
	public static String yod;
	public static String theDate;

	public MainCalendar() {

		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		// Prepare frame
		// this = new JFrame ("Main Calendar"); //Create frame
		setSize(773, 520); // Set size to 400x400 pixels
		setLocation(350, 100);
		pane = this.getContentPane(); // Get content pane
		pane.setLayout(null); // Apply null layout
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		lblYear = new JLabel("Year:");
		cmbYear = new JComboBox();
		lblMont = new JLabel("Month:");
		cmbMont = new JComboBox();
		btnPrev = new JButton("<<Previous");
		btnNext = new JButton("Next>>");
		mtblCalendar = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);

		tblCalendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {// left click to add
															// button
					if (e.getClickCount() == 1) {
						int row = tblCalendar.getSelectedRow();
						int column = tblCalendar.getSelectedColumn();
						dom = tblCalendar.getValueAt(row, column).toString();
						if (currentMonth < 10) {
							moy = "0" + (currentMonth + 1) + "";
						} else {
							moy = (currentMonth + 1) + "";
						}
						yod = cmbYear.getSelectedItem().toString();
						cmbYear.setSelectedItem(Color.black);
						theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
						System.out.println(theDate);
					} else if (e.getClickCount() == 2) {
						int row = tblCalendar.getSelectedRow();
						int column = tblCalendar.getSelectedColumn();
						dom = tblCalendar.getValueAt(row, column).toString();
						if (currentMonth < 10) {
							moy = "0" + (currentMonth + 1) + "";
						} else {
							moy = (currentMonth + 1) + "";
						}
						yod = cmbYear.getSelectedItem().toString();
						cmbYear.setSelectedItem(Color.black);
						theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
						new NewEventWindow();
					}

				}

				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						int row = tblCalendar.getSelectedRow();
						int column = tblCalendar.getSelectedColumn();
						dom = tblCalendar.getValueAt(row, column).toString();
						if (currentMonth < 10) {
							moy = "0" + (currentMonth + 1) + "";
						} else {
							moy = (currentMonth + 1) + "";
						}
						yod = cmbYear.getSelectedItem().toString();
						moy = cmbMont.getSelectedItem().toString();
						theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;

					}
				}
				if (e.getButton() == MouseEvent.BUTTON3) {// right click twice
															// to see if event
															// on that day
					if (e.getClickCount() == 2) {

						EventsView view = null;
						try {
							view = new EventsView();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int row = tblCalendar.getSelectedRow();
						int column = tblCalendar.getSelectedColumn();
						dom = tblCalendar.getValueAt(row, column).toString();
						if (currentMonth < 10) {
							moy = "0" + (currentMonth + 1) + "";
						} else {
							moy = (currentMonth + 1) + "";
						}
						yod = cmbYear.getSelectedItem().toString();
						theDate = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
						System.out.println(theDate);
						view.setTitle("View Events");
						view.setVisible(true);
						System.out.println("Right click");
					}
				}
			}
		});

		pnlCalendar = new JPanel(null);

		// Set border
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

		// Register action listeners
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
		cmbMont.addActionListener(new cmbMont_Action());

		// Add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(lblMont);
		pnlCalendar.add(cmbMont);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(stblCalendar);

		// Set bounds
		pnlCalendar.setBounds(125, 11, 784, 350);
		lblYear.setBounds(10, 316, 80, 20);
		cmbYear.setBounds(80, 316, 80, 20);
		lblMont.setBounds(10, 350, 80, 20);
		cmbMont.setBounds(80, 350, 80, 20);
		btnPrev.setBounds(10, 25, 102, 25);
		btnNext.setBounds(525, 25, 102, 25);
		stblCalendar.setBounds(10, 50, 617, 255);

		// Make frame visible
		this.setResizable(false);
		this.setVisible(true);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(Calendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(Calendar.MONTH); // Get month
		realYear = cal.get(Calendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }; // All
																				// headers
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}

		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); // Set
																			// background

		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCalendar.setSelectionBackground(Color.red);

		// Set row/column count
		tblCalendar.setRowHeight(38);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(285, 25, 81, 25);
		pnlCalendar.add(label);
		label.setText(getMonth(currentMonth));

		/**
		 * JButton btnNewButton_2 = new JButton("New button");
		 * btnNewButton_2.setBounds(10, 180, 89, 23);
		 * this.getContentPane().add(btnNewButton_2);
		 * 
		 * JButton btnNewButton_3 = new JButton("New button");
		 * btnNewButton_3.setBounds(10, 246, 89, 23);
		 * this.getContentPane().add(btnNewButton_3);
		 * 
		 * JButton btnNewButton_4 = new JButton("New button");
		 * btnNewButton_4.setBounds(129, 395, 89, 23);
		 * this.getContentPane().add(btnNewButton_4);
		 * 
		 * JButton btnNewButton_5 = new JButton("New button");
		 * btnNewButton_5.setBounds(298, 395, 89, 23);
		 * this.getContentPane().add(btnNewButton_5);
		 * 
		 * JButton btnNewButton_6 = new JButton("New button");
		 * btnNewButton_6.setBounds(457, 395, 89, 23);
		 * this.getContentPane().add(btnNewButton_6);
		 * 
		 * JButton btnNewButton_7 = new JButton("New button");
		 * btnNewButton_7.setBounds(631, 395, 89, 23);
		 * this.getContentPane().add(btnNewButton_7);
		 */
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Populate table
		for (int i = realYear - 2; i <= realYear + 2; i++) {
			cmbYear.addItem(String.valueOf(i));

			// Code for adding to and
			for (int m = realMonth - 11; m <= realMonth + 11; m++) {
				cmbMont.addItem(String.valueOf(m));
			}

		}

		// Refresh calendar
		refreshCalendar(realMonth, realYear); // Refresh calendar
	}

	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}

	public static void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear - 10) {
			btnPrev.setEnabled(false);
		} // Too early
		if (month == 11 && year >= realYear + 100) {
			btnNext.setEnabled(false);
		}
		cmbYear.setSelectedItem(String.valueOf(year)); // Select the correct
														// year in the combo box
		cmbMont.setSelectedItem(String.valueOf(month));

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		som = cal.get(Calendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);

			if (column == 0 || column == 6) { // Week-end
				setBackground(new Color(255, 220, 220)); // red
			} else { // Week
				setBackground(new Color(255, 255, 255));
			}

			// background for todays date
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth
						&& currentYear == realYear) { // Today
					setBackground(new Color(220, 220, 255));
				}

			}

			try {
				if (database.queryDB.isEvent("2015-05-21") == true) {
					setBackground(new Color(255, 220, 220));
				}

			} catch (ClassNotFoundException e) {
				System.out.println("Error with background colour, no date found?");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Error with background colour, no date found?");
				e.printStackTrace();
			}

			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	static class btnPrev_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) { // Back one year
				currentMonth = 11;
				currentYear -= 1;
				label.setText(getMonth(currentMonth));
			} else { // Back one month
				currentMonth -= 1;
				label.setText(getMonth(currentMonth));
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class btnNext_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) { // Foward one year
				currentMonth = 0;
				currentYear += 1;
				label.setText(getMonth(currentMonth));
			} else { // Foward one month
				currentMonth += 1;
				label.setText(getMonth(currentMonth));
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class cmbYear_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}

	static class cmbMont_Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmbMont.getSelectedItem() != null) {
				String m = cmbMont.getSelectedItem().toString();
				currentMonth = Integer.parseInt(m);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}

}