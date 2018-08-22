import java.sql.*;

import javax.swing.JOptionPane;

public class Student {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	protected String StdId;
	protected String Name;
	protected String Address;
	protected String DOB;
	protected String CourseId;
	protected int CurrentYear;
	protected String RegDate;
	protected static String FacId= SharedData.getFacId();
	
	public String getStdId() {
		return StdId;
	}
	public void setStdId(String stdId) {
		StdId = stdId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public int getCurrentYear() {
		return CurrentYear;
	}
	public void setCurrentYear(int currentYear) {
		CurrentYear = currentYear;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	//End of getters and setters
	
	public void addData(String StdId, String Name, String Address, String DOB, String CourseId, int CurrentYear, String RegDate)
	{
		this.setStdId(StdId);
		this.setName(Name);
		this.setAddress(Address);
		this.setDOB(DOB);
		this.setCourseId(CourseId);
		this.setCurrentYear(CurrentYear);
		this.setRegDate(RegDate);
	}

	public void changeData(String StdId, String Name, String Address, String DOB, String CourseId, int CurrentYear, String RegDate)
	{
		this.setStdId(StdId);
		this.setName(Name);
		this.setAddress(Address);
		this.setDOB(DOB);
		this.setCourseId(CourseId);
		this.setCurrentYear(CurrentYear);
		this.setRegDate(RegDate);
	}

	public void saveData()
	{
		try {
		String query = "UPDATE Student SET Name=?, Address=?, DOB=?, CourseId=?, CurrentYear=?, RegDate=?, FacId=? WHERE StdId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getName());
		ps.setString(2, this.getAddress());
		ps.setString(3, this.getDOB());
		ps.setString(4, this.getCourseId());
		ps.setInt(5, this.getCurrentYear());
		ps.setString(6, this.getRegDate());
		ps.setString(7, Student.FacId);
		ps.setString(8, this.getStdId());
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
		//Add NEW Data
		String query = "INSERT INTO Student(StdId, Name, Address, DOB, CourseId, CurrentYear, RegDate, FacId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getStdId());
		ps.setString(2, this.getName());
		ps.setString(3, this.getAddress());
		ps.setString(4, this.getDOB());
		ps.setString(5, this.getCourseId());
		ps.setInt(6, this.getCurrentYear());
		ps.setString(7, this.getRegDate());
		ps.setString(8, Student.FacId);
		
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	public void removeStudent(String StdId)
	{
		try {
			String query = "DELETE FROM Student WHERE StdId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, StdId);
			ps.executeUpdate();
			
			ps.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Undergrad c1 = new Undergrad();
		//c1.addData("STD001", "sajun", "Nugegoda", "24/12/1996", "C003", 1, SharedData.getDate());
		//c1.UaddData("16000455", "A", "B", "C", "B", 6000);
		//c1.saveNewData();
		
		//Postgrad c2 = new Postgrad();
		//c2.addData("STD002", "sandaruwan", "Delkanda", "31/12/1996", "C003", 1, SharedData.getDate());
		//c2.PaddData("ABC", "Ins1", 2010);
		//c2.saveNewData();
		
		
		
	}	
	
	
	
}
