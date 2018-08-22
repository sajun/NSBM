import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;


public class User {
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
private int userId;
private String username;
private String password;
private int isAdmin;
private int isStdMan;
private int isExamMan;
private static String facId = SharedData.getFacId();


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getIsAdmin() {
	return isAdmin;
}
public void setIsAdmin(int isAdmin) {
	this.isAdmin = isAdmin;
}
public int getIsStdMan() {
	return isStdMan;
}
public void setIsStdMan(int isStdMan) {
	this.isStdMan = isStdMan;
}

public int getUserID() {
	return userId;
}
public void setUserID(int userId) {
	this.userId = userId;
}

public int getIsExamMan() {
	return isExamMan;
}

public void setIsExamMan(int isExamMan) {
	this.isExamMan = isExamMan;
}



//End of getters and setters

public void addData(String username, String password, int isAdmin, int isStdMan, int isExamMan)
{
	this.setUsername(username);
	this.setPassword(password);
	this.setIsAdmin(isAdmin);
	this.setIsStdMan(isStdMan);
	this.setIsExamMan(isExamMan);
}

public void changeData(int userId, String username, String password, int isAdmin, int isStdMan, int isExamMan)
{
	this.setUserID(userId);
	this.setUsername(username);
	this.setPassword(password);
	this.setIsAdmin(isAdmin);
	this.setIsStdMan(isStdMan);
	this.setIsExamMan(isExamMan);
}

public void saveData()
{
	try {
	String query = "UPDATE User SET username=?, password=?, isAdmin=?, isStdMan=?, isExamMan=? WHERE userId=?";
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, this.getUsername());
	ps.setString(2, SharedData.md5(this.getPassword()));
	ps.setInt(3, this.getIsAdmin());
	ps.setInt(4, this.getIsStdMan());
	ps.setInt(5, this.getIsExamMan());
	ps.setInt(6, this.getUserID());
	ps.executeUpdate();
	
	ps.close();
	}
	catch (Exception e){
		JOptionPane.showMessageDialog(null, e);
	}
	
}

public void saveDataNoPass()
{
	try {
	String query = "UPDATE User SET username=?, isAdmin=?, isStdMan=?, isExamMan=? WHERE userId=?";
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, this.getUsername());
	ps.setInt(2, this.getIsAdmin());
	ps.setInt(3, this.getIsStdMan());
	ps.setInt(4, this.getIsExamMan());
	ps.setInt(5, this.getUserID());
	ps.executeUpdate();
	
	ps.close();
	}
	catch (Exception e){
		JOptionPane.showMessageDialog(null, e);
	}
	
}

public void saveNewData()
{
	try {
	//Reset the auto-increment
		String query1 = "ALTER TABLE `User` AUTO_INCREMENT = ?";
		PreparedStatement ps1 = conn.prepareStatement(query1);
		ps1.setInt(1, User.getLastUserID()+1);
		ps1.executeUpdate();
		
	//Add NEW Data
	String query = "INSERT INTO User(username, password, isAdmin, isStdMan, isExamMan, FacId) VALUES(?, ?, ?, ?, ?, ?)";
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, this.getUsername());
	ps.setString(2, SharedData.md5(this.getPassword()));
	ps.setInt(3, this.getIsAdmin());
	ps.setInt(4, this.getIsStdMan());
	ps.setInt(5, this.getIsExamMan());
	ps.setString(6, User.facId);
	ps.executeUpdate();
	
	ps.close();
	}
	catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}
	
}

public void removeUser(int userId)
{
	try {
		String query = "DELETE FROM User WHERE UserId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		ps.executeUpdate();
		
		ps.close();
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}
}

public static ResultSet retriveUsers()
{
	try {
		String query = "SELECT UserId, Username, Password, IsAdmin, IsStdMan, IsExamMan FROM User WHERE FacId = ?";
		PreparedStatement ps = conn2.prepareStatement(query);
		ps.setString(1, User.facId);
		ResultSet rs = ps.executeQuery();
		
		return (rs);
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
		return(null);
	}
}

public static int getLastUserID() {
	try {
		String query = "SELECT UserId FROM User WHERE FacId=? ORDER BY UserID DESC LIMIT 1";
		PreparedStatement ps = conn2.prepareStatement(query);
		ps.setString(1, User.facId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return (rs.getInt(1));
		
		}catch (Exception e3) {
			JOptionPane.showMessageDialog(null, e3);
			return 0;
		}
}

public static void main(String args[]) {
	//User u2 = new User();
	//u2.addData("sajun", "12qw34er", 1, 1, 1);
	//u2.saveNewData();
}






}//end of class
