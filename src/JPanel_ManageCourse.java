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

public class JPanel_ManageCourse extends JPanel {

	public static JTable table;
	/**
	 * Create the panel.
	 */
	public JPanel_ManageCourse() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		//Selecting All course from database and show in JPanel
		
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditCourse.main(null);
			}
		});
		btnAddCourse.setBounds(147, 29, 129, 23);
		add(btnAddCourse);
		
		JButton btnEditCourse = new JButton("Edit Course");
		btnEditCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Course tempCourse = new Course();
				tempCourse.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()), table.getModel().getValueAt(table.getSelectedRow(), 3).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString()));
				SharedData.tempCourse=tempCourse;
				AdminDash.frame.disable();
				AddEditCourse.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Course to Edit");
				}
			}
		});
		btnEditCourse.setBounds(367, 29, 129, 23);
		add(btnEditCourse);
		
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Course l1 = new Course();
				String delCourseID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeCourse(delCourseID);
					table.setModel(DbUtils.resultSetToTableModel(Course.retriveCourses()));
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Course to Remove");
				}
			}
		});
		btnRemoveCourse.setBounds(587, 29, 129, 23);
		add(btnRemoveCourse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Course.retriveCourses()));

	}

}
