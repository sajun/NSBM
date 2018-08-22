import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class AddEditLab extends JFrame {

	private JPanel contentPane;
	private JTextField textLabId;
	private JTextField textName;
	private static AddEditLab frame;
	private JLabel lblLastInsertLaboratory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditLab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddEditLab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 299);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddEditLaboratory = new JLabel("Add/ Edit Laboratory");
		lblAddEditLaboratory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddEditLaboratory.setBounds(10, 11, 165, 23);
		contentPane.add(lblAddEditLaboratory);
		
		lblLastInsertLaboratory = new JLabel("Last Insert Laboratory ID : "+Lab.getLastLabID());
		lblLastInsertLaboratory.setBounds(259, 30, 207, 14);
		contentPane.add(lblLastInsertLaboratory);
		
		JLabel lblLaboratoryName = new JLabel("Laboratory Name");
		lblLaboratoryName.setBounds(10, 131, 119, 14);
		contentPane.add(lblLaboratoryName);
		
		JLabel lblLaboratoryId = new JLabel("Laboratory ID");
		lblLaboratoryId.setBounds(10, 90, 93, 14);
		contentPane.add(lblLaboratoryId);
		
		textLabId = new JTextField();
		textLabId.setBounds(132, 87, 98, 20);
		contentPane.add(textLabId);
		textLabId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(132, 128, 141, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SharedData.tempLab==null)
				{
					Lab l1 = new Lab();
					l1.addData(textLabId.getText(), textName.getText());
					l1.saveNewData();
					JPanel_ManageLab.table.setModel(DbUtils.resultSetToTableModel(Lab.retriveLabs()));
					JOptionPane.showMessageDialog(null, "Lab Added Succesfully");
					lblLastInsertLaboratory.setText("Last Inserted Lab ID : "+Lab.getLastLabID());
					textLabId.setText("");
					textName.setText("");
				}
				else
				{
					Lab l2 = new Lab();
					l2.changeData(textLabId.getText(), textName.getText());
					l2.saveData();
					JOptionPane.showMessageDialog(null, "Lab Saved Succesfully");
					JPanel_ManageLab.table.setModel(DbUtils.resultSetToTableModel(Lab.retriveLabs()));
					SharedData.tempLab=null;
					AdminDash.frame.enable();
					frame.dispose();
				}
			}
		});
		btnSubmit.setBounds(132, 173, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SharedData.tempLab=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(264, 173, 89, 23);
		contentPane.add(btnExit);
		
		//Fill Lab Data in case of edit
		if (SharedData.tempLab!=null)
		{
			lblLastInsertLaboratory.setText("Editing Lecturer : "+SharedData.tempLab.getLabId());
			textLabId.setText(SharedData.tempLab.getLabId());
			textLabId.setEditable(false);
			textName.setText(SharedData.tempLab.getLabName());
		}
	}
}
