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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class JPanel_ManageStudent extends JPanel {

	public static JTable table;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public JPanel_ManageStudent() {
		setBackground(new Color(204, 255, 153));
		
		setLayout(null);
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
					StdManDash.frame.disable();
					AddEditUnderGradStudent.main(null);
				}
				else {
					StdManDash.frame.disable();
					AddEditPostGradStudent.main(null);
				}
			}
		});
		btnAddStudent.setBounds(583, 29, 129, 23);
		add(btnAddStudent);
		
		JButton btnEditStudent = new JButton("Edit Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				if(comboBox.getSelectedIndex()==0) {
					
					Undergrad tempStudent = new Undergrad();
					tempStudent.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 3).toString(), table.getModel().getValueAt(table.getSelectedRow(), 4).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()), table.getModel().getValueAt(table.getSelectedRow(), 6).toString() );
					tempStudent.UchangeData(table.getModel().getValueAt(table.getSelectedRow(), 7).toString(), table.getModel().getValueAt(table.getSelectedRow(), 8).toString(), table.getModel().getValueAt(table.getSelectedRow(), 9).toString(), table.getModel().getValueAt(table.getSelectedRow(), 10).toString(), table.getModel().getValueAt(table.getSelectedRow(), 11).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 12).toString()));
					SharedData.tempUndergrad=tempStudent;
					StdManDash.frame.disable();
					AddEditUnderGradStudent.main(null);
					
					}
				else {
						Postgrad tempStudent = new Postgrad();
						tempStudent.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 3).toString(), table.getModel().getValueAt(table.getSelectedRow(), 4).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()), table.getModel().getValueAt(table.getSelectedRow(), 6).toString() );
						tempStudent.PchangeData(table.getModel().getValueAt(table.getSelectedRow(), 7).toString(), table.getModel().getValueAt(table.getSelectedRow(), 8).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 9).toString()) );
						SharedData.tempPostgrad=tempStudent;
						StdManDash.frame.disable();
						AddEditPostGradStudent.main(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Student to Edit");
				}
			}
		});
		btnEditStudent.setBounds(767, 29, 129, 23);
		add(btnEditStudent);
		
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Student s1 = new Student();
				String delStudentID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					s1.removeStudent(delStudentID);
					if(comboBox.getSelectedIndex()==0) {
					table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
					}
					else {
						table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
					}
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Student to Remove");
				}
			}
		});
		btnRemoveStudent.setBounds(943, 29, 129, 23);
		add(btnRemoveStudent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(204, 255, 153));
		scrollPane.setBounds(10, 63, 1062, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()==0) {
					table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
					}
					else {
						table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
					}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Undergraduates", "Postgraduates"}));
		comboBox.setBounds(10, 30, 150, 20);
		add(comboBox);

		if(comboBox.getSelectedIndex()==0) {
			table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
			}
			else {
				table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
			}
	}
}
