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

public class JPanel_ManageUser extends JPanel {
	public static JTable table;

	/**
	 * Create the panel.
	 */
	public JPanel_ManageUser() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		//Selecting All users from database and show in JPanel
		
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditUser.main(null);
			}
		});
		btnAddUser.setBounds(147, 29, 129, 23);
		add(btnAddUser);
		
		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				User tempuser = new User();
				tempuser.changeData(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), "", Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString()), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 5).toString()));
				SharedData.tempUser=tempuser;
				AdminDash.frame.disable();
				AddEditUser.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a User to Edit");
				}
				
			}
		});
		btnEditUser.setBounds(367, 29, 129, 23);
		add(btnEditUser);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				User u1 = new User();
				int delUserID = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				if (SharedData.getLoggedId()!=delUserID) 
				{
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION) {
						u1.removeUser(delUserID);
						table.setModel(DbUtils.resultSetToTableModel(User.retriveUsers()));
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You Can't Delete Your Own account !!");
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a User to Remove");
				}
			}
		});
		btnRemoveUser.setBounds(587, 29, 129, 23);
		add(btnRemoveUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(User.retriveUsers()));

	}
}
