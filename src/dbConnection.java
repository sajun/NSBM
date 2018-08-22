import java.sql.*;
import javax.swing.*;

public class dbConnection {
	
	public static Connection dbConnector()
	
	{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nsbm","root","12qw34er");
				//JOptionPane.showMessageDialog(null, "Connection2 Success");
				return conn;
				
			}catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
		
		
		}
	
	
	
}