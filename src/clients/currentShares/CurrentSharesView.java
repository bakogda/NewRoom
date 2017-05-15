package clients.currentShares;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import admin.View;

public class CurrentSharesView extends JFrame{

	/**
	 * Bako Gdaniec
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	String usn = View.getLogin();
	
	public CurrentSharesView(){
		super("Invitations");
		JPanel shareView = new JPanel();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(800,500);
		ArrayList<String> columnNames = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        
		try{
		String SQL_statement = "SELECT USERNAME AS INVITE_BY, TITLE, DATE, ROOM, STARTTIME as Time_From, ENDTIME AS Finish_Time, DESCR AS DESCRIPTION FROM " + database.JDBConnect.tbl_event + " WHERE INV_ONE='"+ usn +"'OR INV_TWO='"+ usn +"'OR INV_THREE='"+ usn +"'OR INV_FOUR='"+ usn +"'OR INV_FIVE='"+ usn +"'OR INV_SIX='"+ usn +"'";
		System.out.println(SQL_statement);
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");
		
		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
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

       
        
 
	table.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
		      if (e.getClickCount() == 2) {
		         JTable target = (JTable)e.getSource();
		         int row = target.getSelectedRow();
		         int column = target.getSelectedColumn();
		         JFrame newFrame = new JFrame();
		         newFrame.setTitle("Detail Screen");
		         newFrame.setVisible(true);
		         newFrame.setSize(500, 200);
	              JLabel desription = new JLabel("Would you like to attend "+ table.getValueAt(table.getSelectedRow(), 1).toString());
	              JButton accept = new JButton("Accept Invitation");
	              JButton decline = new JButton("Decline Invitation");
	              JButton cancel = new JButton("Cancel");
	              
	              accept.addActionListener(new ActionListener()
	            		  {
	            	  @Override
	            	  public void actionPerformed(ActionEvent e)
	            	  {
	            		  try{
	            				String SQL_statement = "SELECT '"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"' AS INVITE_BY, TITLE, DATE, ROOM, STARTTIME as Time_From, ENDTIME AS Finish_Time, DESCR AS DESCRIPTION FROM " + database.JDBConnect.tbl_event + " WHERE INV_ONE='"+ usn +"'OR INV_TWO='"+ usn +"'OR INV_THREE='"+ usn +"'OR INV_FOUR='"+ usn +"'OR INV_FIVE='"+ usn +"'OR INV_SIX='"+ usn +"'";
	            				System.out.println(SQL_statement);
	            				Connection connection = DriverManager.getConnection(
	            						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
	            						"password");
	            		  } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally
	            		  {
	            			  
	            		  }
	            	  }
	            		  });
	              
	              decline.addActionListener(new ActionListener()
        		  {
        	  @Override
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  try{
      				String SQL_statement = "SELECT '"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"' AS INVITE_BY, TITLE, DATE, ROOM, STARTTIME as Time_From, ENDTIME AS Finish_Time, DESCR AS DESCRIPTION FROM " + database.JDBConnect.tbl_event + " WHERE INV_ONE='"+ usn +"'OR INV_TWO='"+ usn +"'OR INV_THREE='"+ usn +"'OR INV_FOUR='"+ usn +"'OR INV_FIVE='"+ usn +"'OR INV_SIX='"+ usn +"'";
      				System.out.println(SQL_statement);
      				Connection connection = DriverManager.getConnection(
      						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
      						"password");
      		  } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
      		  {
      			  
      		  }
      	  }
      		  });
	              
	              cancel.addActionListener(new ActionListener()
        		  {
        	  @Override
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  newFrame.dispose();
        	  }
        		  });
	              decline.setBounds(100, 110, 75, 30);
	              accept.setBounds(200, 110, 75, 30);
	              cancel.setBounds(10, 110, 75, 30);
	              newFrame.add(cancel);
	              newFrame.add(decline);
	              newFrame.add(accept);
	              newFrame.add(desription);
	              
	              
		         }
		   }
		});
	}

    public static void main(String[] args)
    {
        CurrentSharesView frame = new CurrentSharesView();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }
	private void toggleOff() {
		this.setVisible(false);
	}

	}
	
	/*
	 * there needs to be:
	 * a listener for the request button, where the 
	 */
	

