import java.sql.*;

import javax.swing.JOptionPane;

public class Course {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String CourseId;
	private String CourseName;
	private int Years;
	private String Type;
	private int TotalCredits;
	private static String FacId= SharedData.getFacId();
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public int getYears() {
		return Years;
	}
	public void setYears(int years) {
		Years = years;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getTotalCredits() {
		return TotalCredits;
	}
	public void setTotalCredits(int totalCredits) {
		TotalCredits = totalCredits;
	}
	

	//End of getters and setters
	public void addData(String CourseId, String CourseName, int Years, String Type, int TotalCredits)
	{
		this.setCourseId(CourseId);
		this.setCourseName(CourseName);
		this.setYears(Years);
		this.setType(Type);
		this.setTotalCredits(TotalCredits);
	}

	public void changeData(String CourseId, String CourseName, int Years, String Type, int TotalCredits)
	{
		this.setCourseId(CourseId);
		this.setCourseName(CourseName);
		this.setYears(Years);
		this.setType(Type);
		this.setTotalCredits(TotalCredits);
	}

	public void saveData()
	{
		try {
		String query = "UPDATE Course SET CourseName=?, Years=?, Type=?, TotalCredits=?  WHERE CourseId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getCourseName());
		ps.setInt(2, this.getYears());
		ps.setString(3, this.getType());
		ps.setInt(4, this.getTotalCredits());
		ps.setString(5, this.getCourseId());
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
		String query = "INSERT INTO Course(CourseId, CourseName, Years, Type, TotalCredits, FacId) VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getCourseId());
		ps.setString(2, this.getCourseName());
		ps.setInt(3, this.getYears());
		ps.setString(4, this.getType());
		ps.setInt(5, this.getTotalCredits());
		ps.setString(6, Course.FacId);
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	public void removeCourse(String CourseId)
	{
		try {
			String query = "DELETE FROM Course WHERE CourseId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, CourseId);
			ps.executeUpdate();
			
			ps.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static ResultSet retriveCourses()
	{
		try {
			String query = "SELECT CourseId, CourseName, Years, Type, TotalCredits FROM Course WHERE FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}

	public static String getLastCourseID() {
		try {
			String query = "SELECT CourseId FROM Course WHERE FacId=? ORDER BY CourseId DESC LIMIT 1";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static ResultSet getCourseList() {
		try {
			String query = "SELECT CourseName FROM Course WHERE FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ResultSet getCourseUnderGradList() {
		try {
			String query = "SELECT CourseName FROM Course WHERE FacId = ? AND Type != ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, "Master");
			ResultSet rs = ps.executeQuery();
			
			return (rs);
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ResultSet getCoursePostGradList() {
		try {
			String query = "SELECT CourseName FROM Course WHERE FacId = ? AND Type = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, "Master");
			ResultSet rs = ps.executeQuery();
			
			return (rs);
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static String getCourseID(String CourseName) {
		try {
			String query = "SELECT CourseId FROM Course WHERE FacId=? and CourseName=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, CourseName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static String getRelevantCourseName(String CourseId) {
		try {
			String query = "SELECT CourseName FROM Course WHERE FacId=? and CourseId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, CourseId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getString(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return null;
			}
	}
	
	public static int getCourseYears(String CourseId) {
		try {
			String query = "SELECT Years FROM Course WHERE FacId=? and CourseId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, CourseId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getInt(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return 0;
			}
	}
	
	public static int getCourseCredits(String CourseId) {
		try {
			String query = "SELECT TotalCredits FROM Course WHERE FacId=? and CourseId=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Course.FacId);
			ps.setString(2, CourseId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getInt(1));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
				return 0;
			}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Course c1 = new Course();
		//c1.addData("C001", "c1", 2010, "t1", 5);
		//c1.saveNewData();
		
		
	}
	
	
	
}
