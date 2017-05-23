package clients.currentShares;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import admin.View;

public class CurrentSharesView extends JFrame{

	/**
	 * Bako Gdaniec
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	String usn = View.getLogin();
	String userid = null;
	
	public CurrentSharesView(){
		super("Invitations");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1000,800);
		ArrayList<String> columnNames = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        
        try {
			userid = database.queryDB.getId(usn);
			System.out.println(userid);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
       
        
		try{
		String SQL_statement = "SELECT FIRSTNAME AS FIRST_NAME, LASTNAME AS LAST_NAME, TITLE, DATE, ROOM, TO_CHAR(STARTTIME, 'HH24:MI') , TO_CHAR(ENDTIME, 'HH24:MI'), ATTENDING FROM EVENT,INVITE,USERDET WHERE USERNAME_INVITED ='"+usn+"' AND EVENT_ID=E_ID AND EVENT.USER_ID=USERDET.USER_ID";
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
            @Override
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
		   @Override
		public void mouseClicked(MouseEvent e) {
		      if (e.getClickCount() == 2) {
		         JTable target = (JTable)e.getSource();
		         JFrame newFrame = new JFrame();
		         newFrame.setTitle("Detail Screen");
		         newFrame.setVisible(true);
		         newFrame.setSize(700, 300);
	              JLabel description = new JLabel("Would you like to attend "+ table.getValueAt(table.getSelectedRow(), 2).toString()+ "?");
	              description.setHorizontalAlignment(SwingConstants.CENTER);
	              JButton accept = new JButton("Accept Invitation");
	              JButton decline = new JButton("Decline Invitation");
	              JButton cancel = new JButton("Cancel");
	      		JLabel reasonLabel = new JLabel("Reason: ");
	    		JTextArea reason = new JTextArea();
	    		reason.setSize(300, 300);
	    		reason.setEditable(true);
	    		reason.setLineWrap(true);
	              newFrame.setResizable(false);
	              
	              accept.addActionListener(new ActionListener()
	            		  {
	            	  @Override
	            	  public void actionPerformed(ActionEvent e)
	            	  {
	            		  String eventID = null;
	            		  try{	
	            			  Connection connection = DriverManager.getConnection(
	            						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
	            						"password");
	            			  
	            					String SQL_statement1 = ("SELECT TITLE,E_ID,USERNAME_INVITED FROM INVITE,EVENT WHERE TITLE='"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"' AND USERNAME_INVITED='"+ usn +"'");
	            					Statement statement1 = connection.createStatement();
	            					ResultSet rs = statement1.executeQuery(SQL_statement1);
	            					System.out.println(SQL_statement1);
	           
	            					while (rs.next())
	            					{
	        							eventID = rs.getString("E_ID");
	            					}
	        
	            		  } catch (SQLException e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				}finally
	            		  {
	            			  
	            		  }
	            		  try
	            		  {
	            			  Connection connection = DriverManager.getConnection(
	          						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
	          						"password");
	          			  
	          					String SQL_statement12 = ("UPDATE INVITE SET ATTENDING='Yes' WHERE EVENT_ID='"+ eventID +"' and USERNAME_INVITED='"+ usn +"'");
	          					System.out.println(SQL_statement12);
	          					Statement statement12 = connection.createStatement();
	          					int rs = statement12.executeUpdate(SQL_statement12);
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
	      
	            	  
	              
	              decline.addActionListener(new ActionListener()
        		  {
        	  @Override
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  String eventID = null;
        		  String reasonFor = reason.getText();
        		  try{	
        			  Connection connection = DriverManager.getConnection(
        						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
        						"password");
        			  
        					String SQL_statement1 = ("SELECT TITLE,E_ID,USERNAME_INVITED FROM INVITE,EVENT WHERE TITLE='"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"' AND USERNAME_INVITED='"+ usn +"'");
        					Statement statement1 = connection.createStatement();
        					ResultSet rs = statement1.executeQuery(SQL_statement1);
        					System.out.println(SQL_statement1);
       
        					while (rs.next())
        					{
    							eventID = rs.getString("E_ID");
        					}
    
        		  } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
        		  {
        			  
        		  }
        		  try
        		  {
        			  Connection connection = DriverManager.getConnection(
      						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
      						"password");
      			  
      					String SQL_statement12 = ("UPDATE INVITE SET ATTENDING='No',REASON = '"+ reasonFor +"' WHERE EVENT_ID='"+ eventID +"' and USERNAME_INVITED='"+ usn +"'");
      					System.out.println(SQL_statement12);
      					Statement statement12 = connection.createStatement();
      					int rs = statement12.executeUpdate(SQL_statement12);
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
	              decline.setBounds(315, 170, 145, 30);
	              accept.setBounds(170, 170, 145, 30);
	              cancel.setBounds(470, 170, 75, 30);
	              reason.setBounds(150, 210, 400, 50);
	              reasonLabel.setBounds(70, 205, 80, 30);
	              newFrame.add(reason);
	              newFrame.add(reasonLabel);
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
	
	

