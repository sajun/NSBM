import java.sql.*;

import javax.swing.JOptionPane;

public class Lab {

		//DB Connections
		Connection conn = dbConnection.dbConnector();
		static Connection conn2 = dbConnection.dbConnector();
		
		private String LabId;
		private String LabName;
		private static String FacId = SharedData.getFacId();
		

		public String getLabId() {
			return LabId;
		}

		public void setLabId(String labId) {
			LabId = labId;
		}

		public String getLabName() {
			return LabName;
		}

		public void setLabName(String labName) {
			LabName = labName;
		}

		//End of getters and setters
		
		public void addData(String LabId, String LabName)
		{
			this.setLabId(LabId);
			this.setLabName(LabName);
		}

		public void changeData(String LabId, String LabName)
		{
			this.setLabId(LabId);
			this.setLabName(LabName);
		}

		public void saveData()
		{
			try {
			String query = "UPDATE Lab SET LabName=? WHERE LabId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getLabName());
			ps.setString(2, this.getLabId());
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
			String query = "INSERT INTO Lab(LabId, LabName, FacId) VALUES(?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.getLabId());
			ps.setString(2, this.getLabName());
			ps.setString(3, Lab.FacId);
			ps.executeUpdate();
			
			ps.close();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			
		}
		
		

		public void removeLab(String LabId)
		{
			try {
				String query = "DELETE FROM Lab WHERE LabId=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, LabId);
				ps.executeUpdate();
				
				ps.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		public static ResultSet retriveLabs()
		{
			try {
				String query = "SELECT LabID, LabName FROM Lab WHERE FacId = ?";
				PreparedStatement ps = conn2.prepareStatement(query);
				ps.setString(1, Lab.FacId);
				ResultSet rs = ps.executeQuery();
				
				return (rs);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return(null);
			}
		}

		public static String getLastLabID() {
			try {
				String query = "SELECT LabId FROM Lab WHERE FacId=? ORDER BY LabId DESC LIMIT 1";
				PreparedStatement ps = conn2.prepareStatement(query);
				ps.setString(1, Lab.FacId);
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
			//Lab l1 = new Lab();
			//l1.addData("LB001", "lab1");
			//l1.saveNewData();
			
			
		}

}
