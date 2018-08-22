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

public class JPanel_ManageSubject extends JPanel {

	public static JTable table;
	/**
	 * Create the panel.
	 */
	public JPanel_ManageSubject() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		JButton btnAddSubject = new JButton("Add Subject");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditSubject.main(null);
			}
		});
		btnAddSubject.setBounds(147, 29, 129, 23);
		add(btnAddSubject);
		
		JButton btnEditSubject = new JButton("Edit Subject");
		btnEditSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Subject tempSubject = new Subject();
				tempSubject.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()), table.getModel().getValueAt(table.getSelectedRow(), 7).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 8).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 9).toString()) );
				SharedData.tempSubject=tempSubject;
				AdminDash.frame.disable();
				AddEditSubject.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Subject to Edit");
				}
			}
		});
		btnEditSubject.setBounds(367, 29, 129, 23);
		add(btnEditSubject);
		
		JButton btnRemoveSubject = new JButton("Remove Subject");
		btnRemoveSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Subject l1 = new Subject();
				String delSubjectID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeSubject(delSubjectID);
					table.setModel(DbUtils.resultSetToTableModel(Subject.retriveSubjects()));
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Subject to Remove");
				}
			}
		});
		btnRemoveSubject.setBounds(587, 29, 129, 23);
		add(btnRemoveSubject);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Subject.retriveSubjects()));

	}

}
