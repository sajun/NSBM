import java.sql.*;

import javax.swing.JOptionPane;

public class Hall {
	
	//DB Connections
	Connection conn = dbConnection.dbConnector();
	static Connection conn2 = dbConnection.dbConnector();
	
	private String HallId;
	private int Capacity;
	private String Location;
	private static String FacId = SharedData.getFacId();
	

	public String getHallId() {
		return HallId;
	}

	public void setHallId(String hallId) {
		HallId = hallId;
	}
	
	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	
	//End of getters and setters
	
	public void addData(String HallId, int Capacity, String Location)
	{
		this.setHallId(HallId);
		this.setCapacity(Capacity);
		this.setLocation(Location);
	}

	public void changeData(String HallId, int Capacity, String Location)
	{
		this.setHallId(HallId);
		this.setCapacity(Capacity);
		this.setLocation(Location);
	}

	public void saveData()
	{
		try {
		String query = "UPDATE Hall SET Capacity=?, Location=? WHERE HallId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, this.getCapacity());
		ps.setString(2, this.getLocation());
		ps.setString(3, this.getHallId());
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
		String query = "INSERT INTO Hall(HallId, Capacity, Location, FacId) VALUES(?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, this.getHallId());
		ps.setInt(2, this.getCapacity());
		ps.setString(3, this.getLocation());
		ps.setString(4, Hall.FacId);
		ps.executeUpdate();
		
		ps.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	public void removeHall(String HallId)
	{
		try {
			String query = "DELETE FROM Hall WHERE HallId=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, HallId);
			ps.executeUpdate();
			
			ps.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static ResultSet retriveHalls()
	{
		try {
			String query = "SELECT HallId, Capacity, Location FROM Hall WHERE FacId = ?";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Hall.FacId);
			ResultSet rs = ps.executeQuery();
			
			return (rs);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return(null);
		}
	}

	public static String getLastHallID() {
		try {
			String query = "SELECT HallId FROM Hall WHERE FacId=? ORDER BY HallId DESC LIMIT 1";
			PreparedStatement ps = conn2.prepareStatement(query);
			ps.setString(1, Hall.FacId);
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
		Hall h1 = new Hall();
		h1.addData("H001", 10, "abc");
		h1.saveNewData();
		
		
	}

	

}
