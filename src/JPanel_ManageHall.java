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

public class JPanel_ManageHall extends JPanel {

	public static JTable table;
	/**
	 * Create the panel.
	 */
	public JPanel_ManageHall() {
		setBackground(new Color(204, 255, 153));

		setLayout(null);
		
		JButton btnAddHall = new JButton("Add Hall");
		btnAddHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditHall.main(null);
			}
		});
		btnAddHall.setBounds(147, 29, 129, 23);
		add(btnAddHall);
		
		JButton btnEditHall = new JButton("Edit Hall");
		btnEditHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Hall tempHall = new Hall();
				tempHall.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 1).toString()), table.getModel().getValueAt(table.getSelectedRow(), 2).toString() );
				SharedData.tempHall=tempHall;
				AdminDash.frame.disable();
				AddEditHall.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Hall to Edit");
				}
			}
		});
		btnEditHall.setBounds(367, 29, 129, 23);
		add(btnEditHall);
		
		JButton btnRemoveHall = new JButton("Remove Hall");
		btnRemoveHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Hall l1 = new Hall();
				String delHallID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeHall(delHallID);
					table.setModel(DbUtils.resultSetToTableModel(Hall.retriveHalls()));
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Hall to Remove");
				}
			}
		});
		btnRemoveHall.setBounds(587, 29, 129, 23);
		add(btnRemoveHall);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Hall.retriveHalls()));		
	}

}
