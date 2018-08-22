import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class AddEditUnderGradStudent extends JFrame {

	private JPanel contentPane;
	private static AddEditUnderGradStudent frame;
	private JTextField textSub1;
	private JTextField textIndexNo;
	private JTextField textSub2;
	private JTextField textSub3;
	private JTextField textSub4;
	private JTextField textIslandRank;
	private JTextField textStdName;
	private JTextField textStdAddress;
	private JTextField textDOB;
	private JTextField textStdId;
	private JLabel lblLastInsertedStudent;
	private JComboBox comboBoxCourse;
	private JTextField textYear;
	private JButton btnSaveDo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditUnderGradStudent();
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
	public AddEditUnderGradStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 493);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddeditStudent = new JLabel("Add/Edit Undergraduate Student");
		lblAddeditStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddeditStudent.setBounds(10, 11, 254, 23);
		contentPane.add(lblAddeditStudent);
		
		lblLastInsertedStudent = new JLabel("Last Inserted Student ID : "+Undergrad.getLastUnderGradStdID());
		lblLastInsertedStudent.setBounds(294, 38, 191, 14);
		contentPane.add(lblLastInsertedStudent);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(10, 77, 99, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(10, 115, 99, 14);
		contentPane.add(lblStudentName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 151, 68, 14);
		contentPane.add(lblAddress);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(10, 187, 99, 14);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblSelectedCourse = new JLabel("Selected Course");
		lblSelectedCourse.setBounds(10, 227, 99, 14);
		contentPane.add(lblSelectedCourse);
		
		comboBoxCourse = new JComboBox();
		comboBoxCourse.setBounds(164, 224, 242, 20);
		contentPane.add(comboBoxCourse);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textYear.getText().equals("")||textIslandRank.getText().equals(""))) {
				if (SharedData.tempUndergrad==null)
				{
					Undergrad u1 = new Undergrad();
					u1.addData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
					u1.UaddData(textIndexNo.getText(), textSub1.getText(), textSub2.getText(), textSub3.getText(), textSub4.getText(), Integer.parseInt(textIslandRank.getText()) );
					u1.saveNewData();
					
					JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
					
					JOptionPane.showMessageDialog(null, "Undergraduate Student Added Succesfully");
					lblLastInsertedStudent.setText("Last Inserted Student ID : "+ Undergrad.getLastUnderGradStdID());
					
					textStdId.setText("");
					textStdName.setText("");
					textStdAddress.setText("");
					textDOB.setText("");
					comboBoxCourse.setSelectedIndex(0);
					textIndexNo.setText("");
					textSub1.setText("");
					textSub2.setText("");
					textSub3.setText("");
					textSub4.setText("");
					textIslandRank.setText("");
						
				}
				else
				{
					Undergrad u1 = new Undergrad();
					u1.changeData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
					u1.UchangeData(textIndexNo.getText(), textSub1.getText(), textSub2.getText(), textSub3.getText(), textSub4.getText(), Integer.parseInt(textIslandRank.getText()) );
					u1.saveData();
					
					JOptionPane.showMessageDialog(null, "Undergraduate Student Saved Succesfully");
					JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
					
					SharedData.tempUndergrad=null;
					StdManDash.frame.enable();
					frame.dispose();
				}
			}
			}
		});
		btnSave.setBounds(10, 405, 113, 23);
		contentPane.add(btnSave);
		
		btnSaveDo = new JButton("Save & Do Subject Selection");
		if(SharedData.tempUndergrad==null) {
			btnSaveDo.setEnabled(true);
		}
		else btnSaveDo.setEnabled(false);
		
		btnSaveDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textYear.getText().equals("")||textIslandRank.getText().equals(""))) {
					if (SharedData.tempUndergrad==null)
					{
						Undergrad u1 = new Undergrad();
						u1.addData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
						u1.UaddData(textIndexNo.getText(), textSub1.getText(), textSub2.getText(), textSub3.getText(), textSub4.getText(), Integer.parseInt(textIslandRank.getText()) );
						u1.saveNewData();
						
						JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Undergrad.retriveStudents()));
						
						JOptionPane.showMessageDialog(null, "Undergraduate Student Added Succesfully, Continue to Select Subjects >>>");
						
						
						SelectStdSub stdsub1 = new SelectStdSub();
						stdsub1.addData(u1.getStdId(), u1.getName(), u1.getCourseId(), u1.getCurrentYear(), null, null);
						stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
						stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
						SharedData.tempStdSubNew=stdsub1;
						
						SharedData.tempUndergrad=null;
						frame.dispose();
						
						SelectChangeUnderGradSubject.main(null);	
					}
					
				}
			}
		});
		btnSaveDo.setBounds(155, 405, 217, 23);
		contentPane.add(btnSaveDo);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SharedData.tempUndergrad=null;
				StdManDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(408, 405, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblAlIndexNumber = new JLabel("A/L Index Number");
		lblAlIndexNumber.setBounds(10, 266, 113, 14);
		contentPane.add(lblAlIndexNumber);
		
		JLabel lblAlResults = new JLabel("AL Results");
		lblAlResults.setBounds(10, 302, 80, 14);
		contentPane.add(lblAlResults);
		
		textSub1 = new JTextField();
		textSub1.setToolTipText("");
		textSub1.setBounds(131, 299, 86, 20);
		contentPane.add(textSub1);
		textSub1.setColumns(10);
		
		textIndexNo = new JTextField();
		textIndexNo.setBounds(161, 263, 121, 20);
		contentPane.add(textIndexNo);
		textIndexNo.setColumns(10);
		
		textSub2 = new JTextField();
		textSub2.setBounds(237, 299, 86, 20);
		contentPane.add(textSub2);
		textSub2.setColumns(10);
		
		textSub3 = new JTextField();
		textSub3.setBounds(350, 299, 86, 20);
		contentPane.add(textSub3);
		textSub3.setColumns(10);
		
		textSub4 = new JTextField();
		textSub4.setBounds(474, 299, 86, 20);
		contentPane.add(textSub4);
		textSub4.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject 1");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setBounds(131, 325, 86, 14);
		contentPane.add(lblSubject);
		
		JLabel lblSubject_1 = new JLabel("Subject 2");
		lblSubject_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject_1.setBounds(236, 325, 86, 14);
		contentPane.add(lblSubject_1);
		
		JLabel lblSubject_2 = new JLabel("Subject 3");
		lblSubject_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject_2.setBounds(350, 325, 86, 14);
		contentPane.add(lblSubject_2);
		
		JLabel lblSubject_3 = new JLabel("Subject 4");
		lblSubject_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject_3.setBounds(474, 325, 86, 14);
		contentPane.add(lblSubject_3);
		
		JLabel lblIslandRank = new JLabel("Island Rank");
		lblIslandRank.setBounds(10, 358, 80, 14);
		contentPane.add(lblIslandRank);
		
		textIslandRank = new JTextField();
		textIslandRank.setBounds(131, 355, 86, 20);
		contentPane.add(textIslandRank);
		textIslandRank.setColumns(10);
		
		textStdId = new JTextField();
		textStdId.setBounds(131, 74, 99, 20);
		contentPane.add(textStdId);
		textStdId.setColumns(10);
		
		textStdName = new JTextField();
		textStdName.setBounds(131, 112, 192, 20);
		contentPane.add(textStdName);
		textStdName.setColumns(10);
		
		textStdAddress = new JTextField();
		textStdAddress.setBounds(131, 148, 441, 20);
		contentPane.add(textStdAddress);
		textStdAddress.setColumns(10);
		
		textDOB = new JTextField();
		textDOB.setBounds(131, 184, 116, 20);
		contentPane.add(textDOB);
		textDOB.setColumns(10);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(433, 227, 38, 14);
		contentPane.add(lblYear);
		
		textYear = new JTextField();
		textYear.setHorizontalAlignment(SwingConstants.CENTER);
		textYear.setText("1");
		textYear.setEditable(false);
		textYear.setBounds(465, 224, 32, 20);
		contentPane.add(textYear);
		textYear.setColumns(10);
		
		if (SharedData.tempUndergrad!=null)
		{
			lblLastInsertedStudent.setText("Editing Student : "+SharedData.tempUndergrad.getStdId());
			textStdId.setText(SharedData.tempUndergrad.getStdId());
			textStdId.setEditable(false);
			textStdName.setText(SharedData.tempUndergrad.getName());
			textStdAddress.setText(SharedData.tempUndergrad.getAddress());
			textDOB.setText(SharedData.tempUndergrad.getDOB());
			
			textIndexNo.setText(SharedData.tempUndergrad.getAL_index());
			textSub1.setText(SharedData.tempUndergrad.getResultSub1());
			textSub2.setText(SharedData.tempUndergrad.getResultSub2());
			textSub3.setText(SharedData.tempUndergrad.getResultSub3());
			textSub4.setText(SharedData.tempUndergrad.getResultSub4());
			textIslandRank.setText(Integer.toString(SharedData.tempUndergrad.getIslandRank()));
			textYear.setText(Integer.toString(SharedData.tempUndergrad.getCurrentYear()));
			
			if (SharedData.tempUndergrad.getCurrentYear()==3)
			{
				textYear.setEditable(true);
			}
		
			//Filling & Setting Course combo
			ResultSet rs = Course.getCourseUnderGradList();
			try {
			while(rs.next())
			{
				comboBoxCourse.addItem(rs.getString(1));
				
			}
			comboBoxCourse.setSelectedItem(Course.getRelevantCourseName(SharedData.tempUndergrad.getCourseId()));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
			}
			
		}
		
		else 
		{
		//Show Available Courses when adding new student
		ResultSet rs = Course.getCourseUnderGradList();
		try {
		while(rs.next())
		{
			comboBoxCourse.addItem(rs.getString(1));
		}
		
		}catch (Exception e3) {
			JOptionPane.showMessageDialog(null, e3);
		}
		
		}
	}
}
