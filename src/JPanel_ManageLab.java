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

public class JPanel_ManageLab extends JPanel {

	public static JTable table;
	/**
	 * Create the panel.
	 */
	public JPanel_ManageLab() {
		setBackground(new Color(204, 255, 153));

		setLayout(null);
		
		JButton btnAddLab = new JButton("Add Lab");
		btnAddLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDash.frame.disable();
				AddEditLab.main(null);
			}
		});
		btnAddLab.setBounds(147, 29, 129, 23);
		add(btnAddLab);
		
		JButton btnEditLab = new JButton("Edit Lab");
		btnEditLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Lab tempLab = new Lab();
				tempLab.changeData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				SharedData.tempLab=tempLab;
				AdminDash.frame.disable();
				AddEditLab.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Lab to Edit");
				}
			}
		});
		btnEditLab.setBounds(367, 29, 129, 23);
		add(btnEditLab);
		
		JButton btnRemoveLab = new JButton("Remove Lab");
		btnRemoveLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				Lab l1 = new Lab();
				String delLabID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure?","Warning",dialogButton);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					l1.removeLab(delLabID);
					table.setModel(DbUtils.resultSetToTableModel(Lab.retriveLabs()));
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Lab to Remove");
				}
			}
		});
		btnRemoveLab.setBounds(587, 29, 129, 23);
		add(btnRemoveLab);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 63, 807, 426);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(DbUtils.resultSetToTableModel(Lab.retriveLabs()));
	}

}
