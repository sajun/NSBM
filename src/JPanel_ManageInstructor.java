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

public class JPanel_ManageInstructor extends JPanel {

	public static JTable table;
	
	/**
	 * Create the panel.
	 */
	public JPanel_ManageInstructor() {
		setBackground(new Color(204, 255, 153));

		setLayout(null);
		
		//Selecting All users from database and show in JPanel
		
		
		JButton btnAddInstructor = new JButton("Add Instructor");
		btnAddInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditInstructor.main(null);
			}
		});
		btnAddInstructor.setBounds(147, 29, 139, 23);
		add(btnAddInstructor);
		
		JButton btnEditInstructor = new JButton("Edit Instructor");
		btnEditInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Instructor tempInstructor = new Instructor();
				tempInstructor.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				SharedData.tempInstructor=tempInstructor;
				AdminDash.frame.disable();
				AddEditInstructor.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select an Instructor to Edit");
				}
			}
		});
		btnEditInstructor.setBounds(377, 29, 139, 23);
		add(btnEditInstructor);
		
		JButton btnRemoveInstructor = new JButton("Remove Instructor");
		btnRemoveInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Instructor l1 = new Instructor();
				String delInstructorID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeInstructor(delInstructorID);
					table.setModel(DbUtils.resultSetToTableModel(Instructor.retriveInstructors()));
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select an Instructor to Remove");
				}
			}
		});
		btnRemoveInstructor.setBounds(607, 29, 139, 23);
		add(btnRemoveInstructor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Instructor.retriveInstructors()));
	}

}
