import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Color;

public class JPanel_SelectStdSub extends JPanel {

	/**
	 * Create the panel.
	 */
	private JComboBox comboBox;
	private JButton btnSelectSubjects;
	private JButton btnChangeSubjects;
	public static JTable table;
	
	
	
	public JPanel_SelectStdSub() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0)
				{
					btnSelectSubjects.setEnabled(true);
					btnChangeSubjects.setEnabled(false);
					table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retriveUndergradSelectSub()));
				}
				else if (comboBox.getSelectedIndex()==1)
				{
					btnSelectSubjects.setEnabled(false);
					btnChangeSubjects.setEnabled(true);
					table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retriveUndergradChangeSub()));
				}
				else if (comboBox.getSelectedIndex()==2)
				{
					btnSelectSubjects.setEnabled(true);
					btnChangeSubjects.setEnabled(false);
					table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retrivePostgradSelectSub()));
				}
				else if (comboBox.getSelectedIndex()==3)
				{
					btnSelectSubjects.setEnabled(false);
					btnChangeSubjects.setEnabled(true);
					table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retrivePostgradChangeSub()));
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Undergraduates : Select Subjects", "Undergraduates : Change Subjects", "Postgraduates : Select Subjects", "Postgraduates : Change Subjects"}));
		comboBox.setBounds(187, 8, 237, 20);
		add(comboBox);
		
		btnSelectSubjects = new JButton("Select Subjects");
		btnSelectSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				if(comboBox.getSelectedIndex()==0) {
					SelectStdSub stdsub1 = new SelectStdSub();
					stdsub1.addData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), null, null);
					stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					SharedData.tempStdSubNew=stdsub1;
					StdManDash.frame.disable();
					SelectChangeUnderGradSubject.main(null);
				}
				else if (comboBox.getSelectedIndex()==2) {
					SelectStdSub stdsub1 = new SelectStdSub();
					stdsub1.addData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), null, null);
					stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					SharedData.tempStdSubNew=stdsub1;
					StdManDash.frame.disable();
					SelectChangePostGradSubject.main(null);
				}
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Student");
				}
				
			}
				
			
		});
		btnSelectSubjects.setBounds(586, 7, 141, 23);
		add(btnSelectSubjects);
		
		btnChangeSubjects = new JButton("Change Subjects");
		btnChangeSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
				if(comboBox.getSelectedIndex()==1) {
					SelectStdSub stdsub1 = new SelectStdSub();
					stdsub1.addData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), null, null);
					stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					
					SharedData.tempStdSubChange=stdsub1;
					StdManDash.frame.disable();
					SelectChangeUnderGradSubject.main(null);
				}
				else if (comboBox.getSelectedIndex()==3) {
					SelectStdSub stdsub1 = new SelectStdSub();
					stdsub1.addData(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 3).toString()), null, null);
					stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
					
					SharedData.tempStdSubChange=stdsub1;
					StdManDash.frame.disable();
					SelectChangePostGradSubject.main(null);
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Student");
				}
			}
		});
		btnChangeSubjects.setEnabled(false);
		btnChangeSubjects.setBounds(769, 8, 141, 23);
		add(btnChangeSubjects);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(165, 42, 745, 449);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		

		table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retriveUndergradSelectSub()));
	}
}
