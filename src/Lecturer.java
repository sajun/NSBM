import java.sql.*;

import javax.swing.JOptionPane;

public class Lecturer {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	
	private String LecturerId;
	private String Name;
	private String Qualification;
	private static String FacId = SharedData.getFacId();
	

	public String getLecturerId() {
		return LecturerId;
	}

	public void setLecturerId(String lecturerId) {
		LecturerId = lecturerId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	
	//End of getters and setters
	
	public void addData(String LecturerId, String name, String Qualification)
	{
		this.setLecturerId(LecturerId);
		this.setName(name);
		this.setQualification(Qualification);
	}

	public void changeData(String LecturerId, String name, String Qualification)
	{
		this.setLecturerId(LecturerId);
		this.setName(name);
		this.setQualification(Qualification);
	}

	public void saveData()
	{
		try {
		String query = "UPDATE Lecturer SET Name=?, Qualification=?  WHERE LecturerId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getName());
		ps.setString(2, this.getQualification());
		ps.setString(3, this.getLecturerId());
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
		String query = "INSERT INTO Lecturer(LecturerId, Name, Qualification, FacId) VALUES(?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getLecturerId());
		ps.setString(2, this.getName());
		ps.setString(3, this.getQualification());
		ps.setString(4, Lecturer.FacId);
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	public void removeLecturer(String LecturerId)
	{
		try {
			String query = "DELETE FROM Lecturer WHERE LecturerId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, LecturerId);
			ps.executeUpdate();
			
			ps.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static ResultSet retriveLecturers()
	{
		try {
			String query = "SELECT LecturerID, Name, Qualification FROM Lecturer WHERE FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Lecturer.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}

	public static String getLastLecturerID() {
		try {
			String query = "SELECT LecturerId FROM Lecturer WHERE FacId=? ORDER BY LecturerId DESC LIMIT 1";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Lecturer.FacId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Lecturer l1 = new Lecturer();
		//l1.addData("L001", "sajun", "Q1, Q2, Q3");
		//l1.saveNewData();
		
		
	}


	

}
