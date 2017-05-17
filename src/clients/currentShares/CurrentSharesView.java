package clients.currentShares;

import java.awt.BorderLayout;
import java.awt.Color;
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
	              JLabel description = new JLabel("Would you like to attend "+ table.getValueAt(table.getSelectedRow(), 1).toString());
	              description.setHorizontalAlignment(JLabel.CENTER);
	              JButton accept = new JButton("Accept Invitation");
	              JButton decline = new JButton("Decline Invitation");
	              JButton cancel = new JButton("Cancel");
	              newFrame.setResizable(false);
	              
	              accept.addActionListener(new ActionListener()
	            		  {
	            	  @Override
	            	  public void actionPerformed(ActionEvent e)
	            	  {
	            		  try{	
	            			  Connection connection = DriverManager.getConnection(
	            						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
	            						"password");
	            			  
	            					String SQL_statement1 = ("SELECT * FROM EVENT WHERE TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement1 = connection.createStatement();
	            					ResultSet rs = statement1.executeQuery(SQL_statement1);
	            					System.out.println(SQL_statement1);
	           
	            					while (rs.next())
	            					{
	            				if((rs.getString(10)).equals(usn) && rs.getString(11).equals("YES"))
	            				{
	            					
	            					String SQL_statement = ("UPDATE EVENT SET INV_ONE_CONFIRMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement = connection.createStatement();
	            					statement.executeUpdate(SQL_statement);
	            					System.out.println(SQL_statement);
	            				}else if((rs.getString(12)).equals(usn))
	            			  	{
	            					String SQL_statement2 = ("UPDATE EVENT SET INV_TWO_CONFRIMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement2 = connection.createStatement();
	            					statement2.executeUpdate(SQL_statement2);
	            					System.out.println(SQL_statement2);
	            			  	}else if((rs.getString(14)).equals(usn))
	            			  	{
	            			  		String SQL_statement3 = ("UPDATE EVENT SET INV_THREE_CONFIRMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement3 = connection.createStatement();
	            					statement3.executeUpdate(SQL_statement3);
	            			  	}else if((rs.getString(16)).equals(usn))
	            			  	{
	            			  		String SQL_statement4 = ("UPDATE EVENT SET INV_FOUR_CONFIRMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement4 = connection.createStatement();
	            					statement4.executeUpdate(SQL_statement4);
	            			  	}else if((rs.getString(18)).equals(usn))
	            			  	{
	            			  		String SQL_statement5 = ("UPDATE EVENT SET INV_FIVE_CONFIRMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement5 = connection.createStatement();
	            					statement5.executeUpdate(SQL_statement5);
	            			  	}else if((rs.getString(18)).equals(usn))
	            			  	{
	            			  		String SQL_statement6 = ("UPDATE EVENT SET INV_SIX_CONFIRMATION = 'YES' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
	            					Statement statement6 = connection.createStatement();
	            					statement6.executeUpdate(SQL_statement6);
	            			  	}
	            					}
	            			
	            		  } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally
	            		  {
	            			  
	            		  }
	            		  newFrame.setVisible(false);
	            	  }
	            		  });
	              
	              decline.addActionListener(new ActionListener()
        		  {
        	  @Override
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  try{	
        			  Connection connection = DriverManager.getConnection(
        						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
        						"password");
        			  
        					String SQL_statement1 = ("SELECT * FROM EVENT WHERE TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement1 = connection.createStatement();
        					ResultSet rs = statement1.executeQuery(SQL_statement1);
        					System.out.println(SQL_statement1);
       
        					while (rs.next())
        					{
        				if((rs.getString(10)).equals(usn) )
        				{
        					
        					String SQL_statement = ("UPDATE EVENT SET INV_ONE_CONFIRMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement = connection.createStatement();
        					statement.executeUpdate(SQL_statement);
        					System.out.println(SQL_statement);
        				}else if((rs.getString(12)).equals(usn))
        			  	{
        					String SQL_statement2 = ("UPDATE EVENT SET INV_TWO_CONFRIMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement2 = connection.createStatement();
        					statement2.executeUpdate(SQL_statement2);
        					System.out.println(SQL_statement2);
        			  	}else if((rs.getString(14)).equals(usn))
        			  	{
        			  		String SQL_statement3 = ("UPDATE EVENT SET INV_THREE_CONFIRMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement3 = connection.createStatement();
        					statement3.executeUpdate(SQL_statement3);
        			  	}else if((rs.getString(16)).equals(usn))
        			  	{
        			  		String SQL_statement4 = ("UPDATE EVENT SET INV_FOUR_CONFIRMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement4 = connection.createStatement();
        					statement4.executeUpdate(SQL_statement4);
        			  	}else if((rs.getString(18)).equals(usn))
        			  	{
        			  		String SQL_statement5 = ("UPDATE EVENT SET INV_FIVE_CONFIRMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement5 = connection.createStatement();
        					statement5.executeUpdate(SQL_statement5);
        			  	}else if((rs.getString(18)).equals(usn))
        			  	{
        			  		String SQL_statement6 = ("UPDATE EVENT SET INV_SIX_CONFIRMATION = 'NO' WHERE INV_ONE='"+ usn +"'AND TITLE='"+ table.getValueAt(table.getSelectedRow(), 1).toString() +"'");
        					Statement statement6 = connection.createStatement();
        					statement6.executeUpdate(SQL_statement6);
        			  	}
        					}
        			
        		  } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
        		  {
        			  
        		  }
        		  target.setBackground(Color.red);
        		  newFrame.setVisible(false);
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
	              decline.setBounds(235, 110, 145, 30);
	              accept.setBounds(90, 110, 145, 30);
	              cancel.setBounds(370, 110, 75, 30);
	              newFrame.add(cancel);
	              newFrame.add(decline);
	              newFrame.add(accept);
	              newFrame.add(description);
	              
	              
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
	
	

