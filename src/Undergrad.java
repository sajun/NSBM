import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Undergrad extends Student {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String AL_index;
	private String ResultSub1;
	private String ResultSub2;
	private String ResultSub3;
	private String ResultSub4;
	private int IslandRank;
	
	
	public String getAL_index() {
		return AL_index;
	}
	public void setAL_index(String aL_index) {
		AL_index = aL_index;
	}
	public String getResultSub1() {
		return ResultSub1;
	}
	public void setResultSub1(String resultSub1) {
		ResultSub1 = resultSub1;
	}
	public String getResultSub2() {
		return ResultSub2;
	}
	public void setResultSub2(String resultSub2) {
		ResultSub2 = resultSub2;
	}
	public String getResultSub3() {
		return ResultSub3;
	}
	public void setResultSub3(String resultSub3) {
		ResultSub3 = resultSub3;
	}
	public String getResultSub4() {
		return ResultSub4;
	}
	public void setResultSub4(String resultSub4) {
		ResultSub4 = resultSub4;
	}
	public int getIslandRank() {
		return IslandRank;
	}
	public void setIslandRank(int islandRank) {
		IslandRank = islandRank;
	}
	//End of getters and setters
	
	public void UaddData(String Al_index, String ResultSub1, String ResultSub2, String ResultSub3, String ResultSub4, int IslandRank)
	{
		this.setAL_index(Al_index);
		this.setResultSub1(ResultSub1);
		this.setResultSub2(ResultSub2);
		this.setResultSub3(ResultSub3);
		this.setResultSub4(ResultSub4);
		this.setIslandRank(IslandRank);
	}
	
	public void UchangeData(String Al_index, String ResultSub1, String ResultSub2, String ResultSub3, String ResultSub4, int IslandRank)
	{
		this.setAL_index(Al_index);
		this.setResultSub1(ResultSub1);
		this.setResultSub2(ResultSub2);
		this.setResultSub3(ResultSub3);
		this.setResultSub4(ResultSub4);
		this.setIslandRank(IslandRank);
	}
	
	public void saveData()
	{	super.saveData();
		try {
		String query = "UPDATE Std_undergrad SET Al_index=?, ResultSub1=?, ResultSub2=?, ResultSub3=?, ResultSub4=?, IslandRank=? WHERE StdId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getAL_index());
		ps.setString(2, this.getResultSub1());
		ps.setString(3, this.getResultSub2());
		ps.setString(4, this.getResultSub3());
		ps.setString(5, this.getResultSub4());
		ps.setInt(6, this.getIslandRank());
		ps.setString(7, this.getStdId());
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
		String query = "INSERT INTO Std_undergrad(StdId, Al_index, ResultSub1, ResultSub2, ResultSub3, ResultSub4, IslandRank) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getStdId());
		ps.setString(2, this.getAL_index());
		ps.setString(3, this.getResultSub1());
		ps.setString(4, this.getResultSub2());
		ps.setString(5, this.getResultSub3());
		ps.setString(6, this.getResultSub4());
		ps.setInt(7, this.getIslandRank());
		
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
			String query = "SELECT Student.StdId, Name, Address, DOB, CourseId, CurrentYear, RegDate, Al_index, ResultSub1, ResultSub2, ResultSub3, ResultSub4, IslandRank FROM (Student INNER JOIN Std_undergrad ON Student.StdId=Std_undergrad.StdId) WHERE Student.FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Student.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static String getLastUnderGradStdID() {
		try {
			String query = "SELECT Std_undergrad.StdId FROM (Std_undergrad INNER JOIN Student on Std_undergrad.StdID=Student.StdId) WHERE Student.FacId=? ORDER BY StdId DESC LIMIT 1";
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


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Undergrad c1 = new Undergrad();
		
		//c1.saveNewData();
		
		
		
	}

}
