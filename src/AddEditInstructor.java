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

public class AddEditInstructor extends JFrame {

	private JPanel contentPane;
	private JTextField textLecId;
	private JTextField textName;
	private JTextField textQualification;
	private static AddEditInstructor frame;
	private JLabel lblLastInsertedInstructor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditInstructor();
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
	public AddEditInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 459);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddEdit = new JLabel("Add / Edit Instructor");
		lblAddEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddEdit.setBounds(10, 11, 170, 23);
		contentPane.add(lblAddEdit);
		
		JLabel lblInstructorId = new JLabel("Instructor ID");
		lblInstructorId.setBounds(10, 82, 80, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 127, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setBounds(10, 171, 90, 14);
		contentPane.add(lblQualification);
		
		textLecId = new JTextField();
		textLecId.setBounds(100, 79, 113, 20);
		contentPane.add(textLecId);
		textLecId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(100, 124, 203, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textQualification = new JTextField();
		textQualification.setBounds(100, 171, 203, 51);
		contentPane.add(textQualification);
		textQualification.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SharedData.tempInstructor==null)
				{
					Instructor l1 = new Instructor();
					l1.addData(textLecId.getText(), textName.getText(), textQualification.getText());
					l1.saveNewData();
					JPanel_ManageInstructor.table.setModel(DbUtils.resultSetToTableModel(Instructor.retriveInstructors()));
					JOptionPane.showMessageDialog(null, "Instructor Added Succesfully");
					lblLastInsertedInstructor.setText("Last Inserted Instructor ID : "+Instructor.getLastInstructorID());
					textLecId.setText("");
					textName.setText("");
					textQualification.setText("");
				}
				else
				{
					Instructor l2 = new Instructor();
					l2.changeData(textLecId.getText(), textName.getText(), textQualification.getText());
					l2.saveData();
					JOptionPane.showMessageDialog(null, "Instructor Saved Succesfully");
					JPanel_ManageInstructor.table.setModel(DbUtils.resultSetToTableModel(Instructor.retriveInstructors()));
					SharedData.tempInstructor=null;
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
				SharedData.tempInstructor=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		
		
		
		
		btnExit.setBounds(232, 260, 89, 23);
		contentPane.add(btnExit);
		
		lblLastInsertedInstructor = new JLabel("Last Inserted Instructor ID : "+Instructor.getLastInstructorID());
		lblLastInsertedInstructor.setBounds(257, 39, 191, 14);
		contentPane.add(lblLastInsertedInstructor);
		
		//Fill Instructor Data in case of edit
				if (SharedData.tempInstructor!=null)
				{
					lblLastInsertedInstructor.setText("Editing Instructor : "+SharedData.tempInstructor.getInstructorId());
					textLecId.setText(SharedData.tempInstructor.getInstructorId());
					textLecId.setEditable(false);
					textName.setText(SharedData.tempInstructor.getName());
					textQualification.setText(SharedData.tempInstructor.getQualification());
				}
	}

}
