import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Postgrad extends Student {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String QualificationType;
	private String Institute;
	private int Year;
	
	public String getQualificationType() {
		return QualificationType;
	}
	public void setQualificationType(String qualificationType) {
		QualificationType = qualificationType;
	}
	public String getInstitute() {
		return Institute;
	}
	public void setInstitute(String institute) {
		Institute = institute;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	//End of getters and setters
	
	public void PaddData(String QualificationType, String Institute, int Year)
	{
		this.setQualificationType(QualificationType);
		this.setInstitute(Institute);
		this.setYear(Year);
	}
	
	public void PchangeData(String QualificationType, String Institute, int Year)
	{
		this.setQualificationType(QualificationType);
		this.setInstitute(Institute);
		this.setYear(Year);
	}
	
	public void saveData()
	{
		super.saveData();
		try {
		String query = "UPDATE Std_postgrad SET QualificationType=?, Institute=?, Year=? WHERE StdId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getQualificationType());
		ps.setString(2, this.getInstitute());
		ps.setInt(3, this.getYear());
		ps.setString(4, this.getStdId());
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public void saveNewData()
	{
		super.saveNewData();
		try {
		//Add NEW Data
		String query = "INSERT INTO Std_postgrad(StdId, QualificationType, Institute, Year) VALUES(?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getStdId());
		ps.setString(2, this.getQualificationType());
		ps.setString(3, this.getInstitute());
		ps.setInt(4, this.getYear());
		
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	
	public static ResultSet retriveStudents()
	{
		try {
			String query = "SELECT Student.StdId, Name, Address, DOB, CourseId, CurrentYear, RegDate, QualificationType, Institute, Year FROM (Student INNER JOIN Std_postgrad ON Student.StdId=Std_postgrad.StdId) WHERE Student.FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Student.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	

	public static String getLastPostGradStdID() {
		try {
			String query = "SELECT Std_postgrad.StdId FROM (Std_postgrad INNER JOIN Student on Std_postgrad.StdID=Student.StdId) WHERE Student.FacId=? ORDER BY StdId DESC LIMIT 1";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Student.FacId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
}
