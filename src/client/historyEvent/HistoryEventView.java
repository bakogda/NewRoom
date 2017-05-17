package client.historyEvent;
import java.awt.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import admin.View;
import clients.currentShares.CurrentSharesView;

public class HistoryEventView extends JFrame{
	/**
	 * Bako Gdaniec 
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private static JTextArea events = new JTextArea(15,35);
	private JScrollPane scroll;
	private Button cancel = new Button("Cancel");
	final static String newline = "\n";
	String usn = View.getLogin();
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	String date2 = format1.format(cal.getTime());


	public HistoryEventView() throws SQLException, ClassNotFoundException{
		JPanel view = new JPanel();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(800,500);
		ArrayList<String> columnNames = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        
		try{
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT USERNAME, TITLE AS EVENT_TITLE, DATE, ROOM, STARTTIME AS STARTED_AT, ENDTIME AS FINISHED_AT, DESCR AS DESCRIPTION, INV_ONE AS GUEST_1, INV_TWO AS GUEST_2, INV_THREE AS GUEST_3, INV_FOUR AS GUEST_4, INV_FIVE AS GUEST_5, INV_SIX AS GUEST_6 FROM event WHERE DATE<'" + date2 + "'AND USERNAME='" + usn +"'");
			ResultSetMetaData rsmd = resultSet.getMetaData(); 

		int cCount = rsmd.getColumnCount();
		for (int i = 1; i <= cCount; i++)
		{
			columnNames.add(rsmd.getColumnName(i));
		}
		
		while (resultSet.next())
		{
			ArrayList<Object> row = new ArrayList<Object>(cCount);
			
			for (int i = 1; i <= cCount; i++)
			{
				row.add(resultSet.getObject(i));
			}
				data.add(row);
		}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		Vector<String> columnNamesVector = new Vector<String>();
		Vector<Vector> dataVector = new Vector<Vector>();
		
		for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        JTable table = new JTable(dataVector,columnNamesVector)
        {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass1(int column) {
                return getValueAt(0, column).getClass();
            }
        	
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

	}

    public static void main(String[] args)
    {
        CurrentSharesView frame = new CurrentSharesView();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }

	}
	