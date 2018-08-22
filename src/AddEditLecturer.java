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

public class AddEditLecturer extends JFrame {

	private JPanel contentPane;
	private JTextField textLecId;
	private JTextField textName;
	private JTextField textQualification;
	private static AddEditLecturer frame;
	private JLabel lblLastInsertedLecturer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditLecturer();
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
	public AddEditLecturer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 459);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddEdit = new JLabel("Add / Edit Lecturer");
		lblAddEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddEdit.setBounds(10, 11, 156, 23);
		contentPane.add(lblAddEdit);
		
		JLabel lblLecturerId = new JLabel("Lecturer ID");
		lblLecturerId.setBounds(10, 81, 80, 14);
		contentPane.add(lblLecturerId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 126, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setBounds(10, 170, 90, 14);
		contentPane.add(lblQualification);
		
		textLecId = new JTextField();
		textLecId.setBounds(100, 78, 113, 20);
		contentPane.add(textLecId);
		textLecId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(100, 123, 203, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textQualification = new JTextField();
		textQualification.setBounds(100, 170, 203, 51);
		contentPane.add(textQualification);
		textQualification.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SharedData.tempLecturer==null)
				{
					Lecturer l1 = new Lecturer();
					l1.addData(textLecId.getText(), textName.getText(), textQualification.getText());
					l1.saveNewData();
					JPanel_ManageLecturer.table.setModel(DbUtils.resultSetToTableModel(Lecturer.retriveLecturers()));
					JOptionPane.showMessageDialog(null, "Lecturer Added Succesfully");
					lblLastInsertedLecturer.setText("Last Inserted Lecturer ID : "+Lecturer.getLastLecturerID());
					textLecId.setText("");
					textName.setText("");
					textQualification.setText("");
				}
				else
				{
					Lecturer l2 = new Lecturer();
					l2.changeData(textLecId.getText(), textName.getText(), textQualification.getText());
					l2.saveData();
					JOptionPane.showMessageDialog(null, "Lecturer Saved Succesfully");
					JPanel_ManageLecturer.table.setModel(DbUtils.resultSetToTableModel(Lecturer.retriveLecturers()));
					SharedData.tempLecturer=null;
					AdminDash.frame.enable();
					frame.dispose();
				}
			}
		});
		btnSubmit.setBounds(100, 260, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempLecturer=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		
		
		
		
		btnExit.setBounds(232, 260, 89, 23);
		contentPane.add(btnExit);
		
		lblLastInsertedLecturer = new JLabel("Last Inserted Lecturer ID : "+Lecturer.getLastLecturerID());
		lblLastInsertedLecturer.setBounds(259, 39, 203, 14);
		contentPane.add(lblLastInsertedLecturer);
		
		//Fill Lecturer Data in case of edit
				if (SharedData.tempLecturer!=null)
				{
					lblLastInsertedLecturer.setText("Editing Lecturer : "+SharedData.tempLecturer.getLecturerId());
					textLecId.setText(SharedData.tempLecturer.getLecturerId());
					textLecId.setEditable(false);
					textName.setText(SharedData.tempLecturer.getName());
					textQualification.setText(SharedData.tempLecturer.getQualification());
				}
	}
}
