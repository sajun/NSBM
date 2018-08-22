import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;


public class JPanel_ManageLecturer extends JPanel {
	
	public static JTable table;

	/**
	 * Create the panel.
	 */
	public JPanel_ManageLecturer() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		//Selecting All lecturers from database and show in JPanel
		
		
		JButton btnAddLecturer = new JButton("Add Lecturer");
		btnAddLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditLecturer.main(null);
			}
		});
		btnAddLecturer.setBounds(147, 29, 129, 23);
		add(btnAddLecturer);
		
		JButton btnEditLecturer = new JButton("Edit Lecturer");
		btnEditLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Lecturer tempLecturer = new Lecturer();
				tempLecturer.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				SharedData.tempLecturer=tempLecturer;
				AdminDash.frame.disable();
				AddEditLecturer.main(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select a Lecturer to Edit");
				}
			}
		});
		btnEditLecturer.setBounds(367, 29, 129, 23);
		add(btnEditLecturer);
		
		JButton btnRemoveLecturer = new JButton("Remove Lecturer");
		btnRemoveLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Lecturer l1 = new Lecturer();
				String delLecturerID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeLecturer(delLecturerID);
					table.setModel(DbUtils.resultSetToTableModel(Lecturer.retriveLecturers()));
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Lecturer to Remove");
				}
			}
		});
		btnRemoveLecturer.setBounds(587, 29, 149, 23);
		add(btnRemoveLecturer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Lecturer.retriveLecturers()));

		
		
	}

}
