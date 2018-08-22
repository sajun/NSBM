import java.sql.*;

import javax.swing.JOptionPane;

public class Subject {
	
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String SubId;
	private String SubName;
	private int IsPracticle;
	private int IsTheory;
	private int IsOptional;
	private int Credits;
	private int Price;
	private String CourseId;
	private int Year;
	private int Semester;
	private static String FacId = SharedData.getFacId();
	

	public String getSubId() {
		return SubId;
	}

	public void setSubId(String subId) {
		SubId = subId;
	}

	public String getSubName() {
		return SubName;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	public int getIsPracticle() {
		return IsPracticle;
	}

	public void setIsPracticle(int isPracticle) {
		IsPracticle = isPracticle;
	}

	public int getIsTheory() {
		return IsTheory;
	}

	public void setIsTheory(int isTheory) {
		IsTheory = isTheory;
	}
	
	public int getIsOptional() {
		return IsOptional;
	}

	public void setIsOptional(int isOptional) {
		IsOptional = isOptional;
	}

	public int getCredits() {
		return Credits;
	}

	public void setCredits(int credits) {
		Credits = credits;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getSemester() {
		return Semester;
	}

	public void setSemester(int semester) {
		Semester = semester;
	}
	//End of getters and setters
	
	public void addData(String SubId, String SubName, int IsPracticle, int IsTheory, int IsOptional, int Credits, int Price, String CourseId, int Year, int Semester)
	{
		this.setSubId(SubId);
		this.setSubName(SubName);
		this.setIsPracticle(IsPracticle);
		this.setIsTheory(IsTheory);
		this.setIsOptional(IsOptional);
		this.setCredits(Credits);
		this.setPrice(Price);
		this.setCourseId(CourseId);
		this.setYear(Year);
		this.setSemester(Semester);
		
	}

	public void changeData(String SubId, String SubName, int IsPracticle, int IsTheory, int IsOptional, int Credits, int Price, String CourseId, int Year, int Semester)
	{
		this.setSubId(SubId);
		this.setSubName(SubName);
		this.setIsPracticle(IsPracticle);
		this.setIsTheory(IsTheory);
		this.setIsOptional(IsOptional);
		this.setCredits(Credits);
		this.setPrice(Price);
		this.setCourseId(CourseId);
		this.setYear(Year);
		this.setSemester(Semester);
	}

	public void saveData()
	{
		try {
		String query = "UPDATE Subject SET SubName=?, IsPracticle=?, IsTheory=?, IsOptional=?, Credits=?, Price=?, CourseId=?, Year=?, Semester=? WHERE SubId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getSubName());
		ps.setInt(2, this.getIsPracticle());
		ps.setInt(3, this.getIsTheory());
		ps.setInt(4, this.getIsOptional());
		ps.setInt(5, this.getCredits());
		ps.setInt(6, this.getPrice());
		ps.setString(7, this.getCourseId());
		ps.setInt(8, this.getYear());
		ps.setInt(9, this.getSemester());
		ps.setString(10, this.getSubId());
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
		String query = "INSERT INTO Subject(SubId, SubName, IsPracticle, IsTheory, IsOptional, Credits, Price, CourseId, Year, Semester, FacId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getSubId());
		ps.setString(2, this.getSubName());
		ps.setInt(3, this.getIsPracticle());
		ps.setInt(4, this.getIsTheory());
		ps.setInt(5, this.getIsOptional());
		ps.setInt(6, this.getCredits());
		ps.setInt(7, this.getPrice());
		ps.setString(8, this.getCourseId());
		ps.setInt(9, this.getYear());
		ps.setInt(10, this.getSemester());
		ps.setString(11, Subject.FacId);
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	public void removeSubject(String SubId)
	{
		try {
			String query = "DELETE FROM Subject WHERE SubId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, SubId);
			ps.executeUpdate();
			
			ps.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static ResultSet retriveSubjects()
	{
		try {
			String query = "SELECT SubId, SubName, IsPracticle, IsTheory, IsOptional, Credits, Price, CourseId, Year, Semester FROM Subject WHERE FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}

	public static String getLastSubjectID() {
		try {
			String query = "SELECT SubId FROM Subject WHERE FacId=? ORDER BY SubId DESC LIMIT 1";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static String getRelevantSubId(String SubName) {
		try {
			String query = "SELECT SubId FROM Subject WHERE FacId=? and SubName=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ps.setString(2, SubName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static String getRelevantSubjectName(String SubId) {
		try {
			String query = "SELECT SubName FROM Subject WHERE FacId=? and SubId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ps.setString(2, SubId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static int getRelevantSemester(String SubId) {
		try {
			String query = "SELECT Semester FROM Subject WHERE FacId=? and SubId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ps.setString(2, SubId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getInt(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return 0;
			}
	}
	
	public static int getSubjectCredits(String SubId) {
		try {
			String query = "SELECT Credits FROM Subject WHERE FacId=? and SubId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Subject.FacId);
			ps.setString(2, SubId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getInt(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return 0;
			}
	}
	
	
	//public static Result


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Subject s1 = new Subject();
		//s1.addData("S001","CS",0,1,5,1000,"C003");
		//s1.saveNewData();
		
		
	}

	

	

}
