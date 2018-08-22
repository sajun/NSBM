import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class JPanel_Configurations extends JPanel {
	private JTextField textFacultyName;
	private static Connection conn2 = dbConnection.dbConnector();
	private static Connection conn = dbConnection.dbConnector();
	private JComboBox comboBox;
	private JCheckBox chckbxOpenForEnrollement;

	/**
	 * Create the panel.
	 */
	public JPanel_Configurations() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Faculty Configurations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(25, 11, 201, 26);
		add(lblNewLabel);
		
		JButton btnStart = new JButton("(2) Start New Acedemic Year ");
		btnStart.setHorizontalAlignment(SwingConstants.LEFT);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "All 3rd Year Student details will be removed. Please Make Sure That Relevant 3rd Year Students are Assigned for 4 Year Degree Programmes","Warning",dialogButton);
					
					if(dialogResult == JOptionPane.YES_OPTION) {
						String query1="DELETE FROM Student WHERE CurrentYear=3";
						String query2="UPDATE Student SET CurrentYear=3 WHERE CurrentYear=2";
						String query3="UPDATE Student SET CurrentYear=2 WHERE CurrentYear=1";
						String query4="DELETE FROM std_sub";
						
						PreparedStatement ps1= conn.prepareStatement(query1);
						PreparedStatement ps2= conn.prepareStatement(query2);
						PreparedStatement ps3= conn.prepareStatement(query3);
						PreparedStatement ps4= conn.prepareStatement(query4);
						
						
						ps1.executeUpdate();
						ps2.executeUpdate();
						ps3.executeUpdate();
						ps4.executeUpdate();
						
					}
					
					
				
				}catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
				
			}
		});
		btnStart.setBounds(25, 260, 254, 23);
		add(btnStart);
		
		textFacultyName = new JTextField();
		textFacultyName.setBounds(165, 48, 226, 20);
		add(textFacultyName);
		textFacultyName.setColumns(10);
		
		JLabel lblFacultyName = new JLabel("Faculty Name :");
		lblFacultyName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFacultyName.setBounds(25, 51, 96, 14);
		add(lblFacultyName);
		
		JLabel lblCurrentSemester = new JLabel("Current Semester");
		lblCurrentSemester.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentSemester.setBounds(25, 97, 129, 14);
		add(lblCurrentSemester);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		comboBox.setBounds(164, 94, 38, 20);
		add(comboBox);
		
		chckbxOpenForEnrollement = new JCheckBox("Open For Enrollement");
		chckbxOpenForEnrollement.setBounds(260, 93, 148, 23);
		add(chckbxOpenForEnrollement);
		
		textFacultyName.setText(this.getFacultyName(SharedData.getFacId()));
		comboBox.setSelectedItem(this.getFacultySemester(SharedData.getFacId()));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "UPDATE Faculty SET FacName=?, Semester=?, IsOpen=? WHERE FacId=?";
					PreparedStatement ps= conn.prepareStatement(query);
					ps.setString(1, textFacultyName.getText());
					ps.setString(2, comboBox.getSelectedItem().toString());
					ps.setInt(3, chckbxOpenForEnrollement.isSelected()?1:0);
					ps.setString(4, SharedData.getFacId());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Updated Succesfully, You'll be Logout");
					AdminDash.frame.dispose();
					Login.main(null);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
	
					}
			}
		});
		btnSave.setBounds(119, 157, 107, 23);
		add(btnSave);
		
		JButton btnRemovethYear = new JButton("(1) Remove 4th Year Students");
		btnRemovethYear.setHorizontalAlignment(SwingConstants.LEFT);
		btnRemovethYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure ? All 4th Year Student details will be removed","Warning",dialogButton);
					
					if(dialogResult == JOptionPane.YES_OPTION) {
						String query1="DELETE FROM Student WHERE CurrentYear=4";
						PreparedStatement ps1= conn.prepareStatement(query1);
						ps1.executeUpdate();
						
					}
					
					
				
				}catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, e2);
				}
			}
			
		});
		btnRemovethYear.setBounds(25, 217, 254, 23);
		add(btnRemovethYear);
		
		JButton btnNewButton = new JButton("(3) Remove Completed Postgraduates");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure ?","Warning",dialogButton);
					
					if(dialogResult == JOptionPane.YES_OPTION) {
						String query1="DELETE FROM Student WHERE CurrentYear=3 AND StdId IN(SELECT StdId FROM std_postgrad)";
						PreparedStatement ps1= conn.prepareStatement(query1);
						ps1.executeUpdate();
						
					}
					
					
				
				}catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnNewButton.setBounds(25, 303, 254, 23);
		add(btnNewButton);
		
		if(this.getFacultySemesterIsOpen(SharedData.getFacId())==1) chckbxOpenForEnrollement.setSelected(true);
		else chckbxOpenForEnrollement.setSelected(false);
		
	}
	
	public String getFacultyName(String FacId) {
		try {
			String query = "SELECT FacName FROM Faculty WHERE FacId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, FacId);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			return (rs.getString(1));
			
			
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}
	
	public String getFacultySemester(String FacId) {
		try {
			String query = "SELECT Semester FROM Faculty WHERE FacId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, FacId);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			return (rs.getString(1));
			
			
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}
	
	public int getFacultySemesterIsOpen(String FacId) {
		try {
			String query = "SELECT IsOpen FROM Faculty WHERE FacId=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, FacId);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			return (rs.getInt(1));
			
			
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return 0;
			}
	}
}
