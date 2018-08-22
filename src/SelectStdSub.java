import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectStdSub {
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String StdId;
	private String Name;
	private String CourseId;
	private int CurrentYear;
	private ArrayList myCompulsorySubjects;
	private ArrayList myOptionalSubjects;
	private static String FacId= SharedData.getFacId();
	
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

	public ArrayList getMyCompulsorySubjects() {
		return myCompulsorySubjects;
	}

	public void setMyCompulsorySubjects(ArrayList mycompulsorySubjects) {
		myCompulsorySubjects = mycompulsorySubjects;
	}

	public ArrayList getMyOptionalSubjects() {
		return myOptionalSubjects;
	}

	public void setMyOptionalSubjects(ArrayList myoptionalSubjects) {
		myOptionalSubjects = myoptionalSubjects;
	}
	//End of getters and setters
	
	public void addData(String StdId, String Name, String CourseId, int CurrentYear, ArrayList myCompulsorySubjects, ArrayList myOptionalSubjects ) {
		this.setStdId(StdId);
		this.setName(Name);
		this.setCourseId(CourseId);
		this.setCurrentYear(CurrentYear);
		this.setMyCompulsorySubjects(myCompulsorySubjects);
		this.setMyOptionalSubjects(myOptionalSubjects);
		
	}
	
	public void changeData(String StdId, String Name, String CourseId, int CurrentYear, ArrayList myCompulsorySubjects, ArrayList myOptionalSubjects ) {
		this.setStdId(StdId);
		this.setName(Name);
		this.setCourseId(CourseId);
		this.setCurrentYear(CurrentYear);
		this.setMyCompulsorySubjects(myCompulsorySubjects);
		this.setMyOptionalSubjects(myOptionalSubjects);
		
	}
	
	public void saveNewData() {
		try {
			//Add compulsory Subjects
			for(int i=0;i<this.getMyCompulsorySubjects().size();i++)
			{
				String query = "INSERT INTO std_sub (StdId, SubId, Year, Semester) VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getStdId());
				ps.setString(2, this.getMyCompulsorySubjects().get(i).toString());
				ps.setInt(3, this.getCurrentYear());
				ps.setInt(4, Subject.getRelevantSemester(this.getMyCompulsorySubjects().get(i).toString() ));
				ps.executeUpdate();
			}
			//Add optional Subjects
			for(int i=0;i<this.getMyOptionalSubjects().size();i++)
			{
				String query = "INSERT INTO std_sub (StdId, SubId, Year, Semester) VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getStdId());
				ps.setString(2, this.getMyOptionalSubjects().get(i).toString());
				ps.setInt(3, this.getCurrentYear());
				ps.setInt(4, Subject.getRelevantSemester(this.getMyOptionalSubjects().get(i).toString() ));
				ps.executeUpdate();
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public void saveData() {
		try {
			//Add compulsory subjects
			for(int i=0;i<this.getMyCompulsorySubjects().size();i++)
			{
				String query = "INSERT INTO std_sub (StdId, SubId, Year, Semester) VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getStdId());
				ps.setString(2, this.getMyCompulsorySubjects().get(i).toString());
				ps.setInt(3, this.getCurrentYear());
				ps.setInt(4, Subject.getRelevantSemester(this.getMyCompulsorySubjects().get(i).toString() ));
				ps.executeUpdate();
			}
			//Add changed optional Subjects
			for(int i=0;i<this.getMyOptionalSubjects().size();i++)
			{
				String query = "INSERT INTO std_sub (StdId, SubId, Year, Semester) VALUES (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getStdId());
				ps.setString(2, this.getMyOptionalSubjects().get(i).toString());
				ps.setInt(3, this.getCurrentYear());
				ps.setInt(4, Subject.getRelevantSemester(this.getMyOptionalSubjects().get(i).toString() ));
				ps.executeUpdate();
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public void removeSubjects() {
		try {
		if (SharedData.getCurrentSem()==1) 
		{
			String query = "DELETE FROM std_sub WHERE StdId=? AND Year=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getStdId());
			ps.setInt(2, this.getCurrentYear());
			ps.executeUpdate();
		}
		else if(SharedData.getCurrentSem()==2)
		{
			String query = "DELETE FROM std_sub WHERE StdId=? AND Year=? AND Semester=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getStdId());
			ps.setInt(2, this.getCurrentYear());
			ps.setInt(3, 2);
			ps.executeUpdate();
		}
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public static ResultSet retriveUndergradSelectSub()
	{
		try {
			String query = "SELECT Student.StdId, Name, CourseId, CurrentYear FROM (Student INNER JOIN Std_undergrad ON Student.StdId=Std_undergrad.StdId) WHERE Student.StdId NOT IN (SELECT std_sub.StdId FROM std_sub INNER JOIN student ON (std_sub.StdId=student.StdId AND std_sub.Year=student.CurrentYear) WHERE Student.FacId=?)";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, SelectStdSub.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ResultSet retrivePostgradSelectSub()
	{
		try {
			String query = "SELECT Student.StdId, Name, CourseId, CurrentYear FROM (Student INNER JOIN Std_postgrad ON Student.StdId=Std_postgrad.StdId) WHERE Student.StdId NOT IN (SELECT std_sub.StdId FROM std_sub INNER JOIN student ON (std_sub.StdId=student.StdId AND std_sub.Year=student.CurrentYear) WHERE Student.FacId=?)";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, SelectStdSub.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ResultSet retriveUndergradChangeSub()
	{
		try {
			String query = "SELECT Student.StdId, Name, CourseId, CurrentYear FROM (Student INNER JOIN Std_undergrad ON Student.StdId=Std_undergrad.StdId) WHERE Student.StdId IN (SELECT std_sub.StdId FROM std_sub INNER JOIN student ON (std_sub.StdId=student.StdId AND std_sub.Year=student.CurrentYear) WHERE Student.FacId=?)";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, SelectStdSub.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ResultSet retrivePostgradChangeSub()
	{
		try {
			String query = "SELECT Student.StdId, Name, CourseId, CurrentYear FROM (Student INNER JOIN Std_postgrad ON Student.StdId=Std_postgrad.StdId) WHERE Student.StdId IN (SELECT std_sub.StdId FROM std_sub INNER JOIN student ON (std_sub.StdId=student.StdId AND std_sub.Year=student.CurrentYear) WHERE Student.FacId=?)";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, SelectStdSub.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}

	public static ArrayList getCompulsorySubjects(String CourseId, int CurrentYear) {
		ArrayList SubjectList = new ArrayList();
		try {
		if (SharedData.getCurrentSem()==1)
		{
			String query = "SELECT Subject.SubId FROM (Course INNER JOIN Subject ON Course.CourseId=Subject.CourseId) WHERE Subject.Year=? AND Course.CourseId=? AND Subject.IsOptional=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setInt(1, CurrentYear);
			ps.setString(2, CourseId);
			ps.setInt(3, 0);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else if (SharedData.getCurrentSem()==2)
		{
			String query = "SELECT Subject.SubId FROM (Course INNER JOIN Subject ON Course.CourseId=Subject.CourseId) WHERE Subject.Year=? AND Course.CourseId=? AND Subject.Semester=? AND Subject.IsOptional=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setInt(1, CurrentYear);
			ps.setString(2, CourseId);
			ps.setInt(3, 2);
			ps.setInt(4, 0);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else
			return null;
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	
	public static ArrayList getOptionalSubjects(String CourseId, int CurrentYear) {
		ArrayList SubjectList = new ArrayList();
		try {
		if (SharedData.getCurrentSem()==1)
		{
			String query = "SELECT Subject.SubId FROM (Course INNER JOIN Subject ON Course.CourseId=Subject.CourseId) WHERE Subject.Year=? AND Course.CourseId=? AND Subject.IsOptional=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setInt(1, CurrentYear);
			ps.setString(2, CourseId);
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else if (SharedData.getCurrentSem()==2)
		{
			String query = "SELECT Subject.SubId FROM (Course INNER JOIN Subject ON Course.CourseId=Subject.CourseId) WHERE Subject.Year=? AND Course.CourseId=? AND Subject.Semester=? AND Subject.IsOptional=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setInt(1, CurrentYear);
			ps.setString(2, CourseId);
			ps.setInt(3, 2);
			ps.setInt(4, 1);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else
			return null;
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static ArrayList getAlreadySelectedOptionalSubjects(String StdId, int CurrentYear) {
		ArrayList SubjectList = new ArrayList();
		try {
		if (SharedData.getCurrentSem()==1)
		{
			String query = "SELECT std_sub.SubId FROM (std_sub INNER JOIN Subject ON std_sub.SubId=Subject.SubId) WHERE std_sub.StdId=? AND Subject.IsOptional=? AND std_sub.Year=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, StdId);
			ps.setInt(2, 1);
			ps.setInt(3, CurrentYear);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else if (SharedData.getCurrentSem()==2)
		{
			String query = "SELECT std_sub.SubId FROM (std_sub INNER JOIN Subject ON std_sub.SubId=Subject.SubId) WHERE std_sub.StdId=? AND Subject.IsOptional=? AND std_sub.Year=? AND std_sub.Semester=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, StdId);
			ps.setInt(2, 1);
			ps.setInt(3, CurrentYear);
			ps.setInt(4, 2);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SubjectList.add(rs.getString(1));
			}
			return SubjectList;
		}
		else
			return null;
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}
	
	public static int getStudentSubjectCredits(String StdId, int Year) 
	{
		try {
			String query ="SELECT SUM(Subject.Credits) FROM (std_sub INNER JOIN Subject on std_sub.SubId=Subject.SubId) WHERE std_sub.StdId=? AND std_sub.Year=?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, StdId);
			ps.setInt(2, Year);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			return (rs.getInt(1));
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return 0;
		}
	}
		
	
}
