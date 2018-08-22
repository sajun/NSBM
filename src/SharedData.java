import java.math.BigInteger;
import java.security.*;

import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.*;
import java.util.Date;

public class SharedData {
	//static variables
public static String LoggedUser;
public static int PrgNo = 1;
public static User tempUser=null;
public static Lecturer tempLecturer=null;
public static Instructor tempInstructor=null;
public static Lab tempLab=null;
public static Hall tempHall=null;
public static Course tempCourse=null;
public static Subject tempSubject=null;
public static Undergrad tempUndergrad=null;
public static Postgrad tempPostgrad=null;
public static SelectStdSub tempStdSubNew=null;
public static SelectStdSub tempStdSubChange=null;


//DB connection
private static Connection conn2 = dbConnection.dbConnector();


//Static methods
public static String getLoggedUser() {
	return LoggedUser;
}

public static void setLoggedUser(String loggedUser) {
	LoggedUser = loggedUser;
}

public static String md5(String str) {
	try {
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(str.getBytes(),0,str.length());
	return (new BigInteger(1,md.digest()).toString(16));
	
	
	}
	catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
		return null;
	}
	
}

public static String getFacId() {
	try {
	String query = "SELECT FacId FROM Faculty WHERE PrgNo=?";
	PreparedStatement ps= conn2.prepareStatement(query);
	ps.setInt(1, PrgNo);
	ResultSet rs = ps.executeQuery();
	
	rs.next();
	
	return (rs.getString(1));
	
	
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
		return null;
	}
}

public static int getLoggedId() {
	try {
	String query = "SELECT UserId FROM User WHERE Username=? and FacId=?";
	PreparedStatement ps = conn2.prepareStatement(query);
	ps.setString(1, SharedData.getLoggedUser());
	ps.setString(2, SharedData.getFacId());
	ResultSet rs = ps.executeQuery();
	rs.next();
	return (rs.getInt(1));
	
	}catch (Exception e2) {
		JOptionPane.showMessageDialog(null, e2);
		return 0;
	}
}

public static String getDate() {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date = new Date();  
    return formatter.format(date);
}

public static int getCurrentSem() {
	try {
		String query = "SELECT Semester FROM Faculty WHERE PrgNo=?";
		PreparedStatement ps= conn2.prepareStatement(query);
		ps.setInt(1, PrgNo);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		return (rs.getInt(1));
		
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 0;
		}
}

public static int getCurrentSemIsOpen() {
	try {
		String query = "SELECT IsOpen FROM Faculty WHERE PrgNo=?";
		PreparedStatement ps= conn2.prepareStatement(query);
		ps.setInt(1, PrgNo);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		return (rs.getInt(1));
		
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 0;
		}
}




public static void main(String args[]){
	//SharedData.getFacId();
	ArrayList lst = new ArrayList();
	lst.add("sajun");
    System.out.println(lst.get(0));
}



}
