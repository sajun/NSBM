import java.sql.*;
import javax.swing.JOptionPane;

public class Instructor {
		//DB Connections
		Connection conn = dbConnection.dbConnector();
		static Connection conn2 = dbConnection.dbConnector();
		
		
		private String InstructorId;
		private String Name;
		private String Qualification;
		private static String FacId = SharedData.getFacId();
		

		public String getInstructorId() {
			return InstructorId;
		}

		public void setInstructorId(String instructorId) {
			InstructorId = instructorId;
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
		
		public void addData(String InstructorId, String name, String Qualification)
		{
			this.setInstructorId(InstructorId);
			this.setName(name);
			this.setQualification(Qualification);
		}

		public void changeData(String InstructorId, String name, String Qualification)
		{
			this.setInstructorId(InstructorId);
			this.setName(name);
			this.setQualification(Qualification);
		}

		public void saveData()
		{
			try {
			String query = "UPDATE Instructor SET Name=?, Qualification=?  WHERE InstructorId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getName());
			ps.setString(2, this.getQualification());
			ps.setString(3, this.getInstructorId());
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
			String query = "INSERT INTO Instructor(InstructorId, Name, Qualification, FacId) VALUES(?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getInstructorId());
			ps.setString(2, this.getName());
			ps.setString(3, this.getQualification());
			ps.setString(4, Instructor.FacId);
			ps.executeUpdate();
			
			ps.close();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			
		}
		
		

		public void removeInstructor(String InstructorId)
		{
			try {
				String query = "DELETE FROM Instructor WHERE InstructorId=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, InstructorId);
				ps.executeUpdate();
				
				ps.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		public static ResultSet retriveInstructors()
		{
			try {
				String query = "SELECT InstructorID, Name, Qualification FROM Instructor WHERE FacId = ?";
				PreparedStatement ps = conn2.prepareStatement(query);
				ps.setString(1, Instructor.FacId);
				ResultSet rs = ps.executeQuery();
				
				return (rs);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return(null);
			}
		}

		public static String getLastInstructorID() {
			try {
				String query = "SELECT InstructorId FROM Instructor WHERE FacId=? ORDER BY InstructorId DESC LIMIT 1";
				PreparedStatement ps = conn2.prepareStatement(query);
				ps.setString(1, Instructor.FacId);
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
			//Instructor l1 = new Instructor();
			//l1.addData("E002", "sajun2", "Q1, Q2, Q3");
			//l1.saveNewData();
			
			
		}
}
