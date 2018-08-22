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

public class AddEditPostGradStudent extends JFrame {

	private JPanel contentPane;
	private static AddEditPostGradStudent frame;
	private JTextField textInstitute;
	private JTextField textQualification;
	private JTextField textGradYear;
	private JTextField textStdName;
	private JTextField textStdAddress;
	private JTextField textDOB;
	private JTextField textStdId;
	private JTextField textYear;
	private JComboBox comboBoxCourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditPostGradStudent();
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
	public AddEditPostGradStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 493);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblAddeditStudent = new JLabel("Add/Edit Postgraduate Student");
		lblAddeditStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddeditStudent.setBounds(10, 11, 254, 23);
		contentPane.add(lblAddeditStudent);
		
		JLabel lblLastInsertedStudent = new JLabel("Last Inserted Student ID : "+Postgrad.getLastPostGradStdID());
		lblLastInsertedStudent.setBounds(294, 38, 191, 14);
		contentPane.add(lblLastInsertedStudent);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(10, 77, 99, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(10, 115, 99, 14);
		contentPane.add(lblStudentName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 151, 80, 14);
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
				if(!(textYear.getText().equals("")||textGradYear.getText().equals(""))) {
				if (SharedData.tempPostgrad==null)
				{
					Postgrad p1 = new Postgrad();
					p1.addData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
					p1.PaddData(textQualification.getText(), textInstitute.getText(), Integer.parseInt(textGradYear.getText()) );
					p1.saveNewData();
					
					JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
					
					JOptionPane.showMessageDialog(null, "Postgraduate Student Added Succesfully");
					lblLastInsertedStudent.setText("Last Inserted Student ID : "+ Postgrad.getLastPostGradStdID());
					
					textStdId.setText("");
					textStdName.setText("");
					textStdAddress.setText("");
					textDOB.setText("");
					comboBoxCourse.setSelectedIndex(0);
					
					textQualification.setText("");
					textInstitute.setText("");
					textGradYear.setText("");
						
				}
				else
				{
					Postgrad p1 = new Postgrad();
					p1.changeData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
					p1.PchangeData(textQualification.getText(), textInstitute.getText(), Integer.parseInt(textGradYear.getText()) );
					p1.saveData();
					
					JOptionPane.showMessageDialog(null, "Postgraduate Student Saved Succesfully");
					JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
					
					SharedData.tempPostgrad=null;
					StdManDash.frame.enable();
					frame.dispose();
				}
				}
			}
		});
		btnSave.setBounds(10, 405, 113, 23);
		contentPane.add(btnSave);
		
		JButton btnSaveDo = new JButton("Save & Do Subject Selection");
		btnSaveDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textYear.getText().equals("")||textGradYear.getText().equals(""))) {
					if (SharedData.tempPostgrad==null)
					{
						Postgrad p1 = new Postgrad();
						p1.addData(textStdId.getText(), textStdName.getText(), textStdAddress.getText(), textDOB.getText(), Course.getCourseID(comboBoxCourse.getSelectedItem().toString()), Integer.parseInt(textYear.getText()), SharedData.getDate() );
						p1.PaddData(textQualification.getText(), textInstitute.getText(), Integer.parseInt(textGradYear.getText()) );
						p1.saveNewData();
						
						JPanel_ManageStudent.table.setModel(DbUtils.resultSetToTableModel(Postgrad.retriveStudents()));
						
						JOptionPane.showMessageDialog(null, "Postgraduate Student Added Succesfully, Continue to Select Subjects >>>");
						
						SelectStdSub stdsub1 = new SelectStdSub();
						stdsub1.addData(p1.getStdId(), p1.getName(), p1.getCourseId(), p1.getCurrentYear(), null, null);
						stdsub1.setMyCompulsorySubjects(SelectStdSub.getCompulsorySubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
						stdsub1.setMyOptionalSubjects(SelectStdSub.getOptionalSubjects(stdsub1.getCourseId(), stdsub1.getCurrentYear()));
						SharedData.tempStdSubNew=stdsub1;
						
						SharedData.tempPostgrad=null;
						frame.dispose();
						
						SelectChangePostGradSubject.main(null);
							
					}
					
					}
			}
		});
		if(SharedData.tempPostgrad==null) {
			btnSaveDo.setEnabled(true);
		}
		else btnSaveDo.setEnabled(false);
		btnSaveDo.setBounds(155, 405, 191, 23);
		contentPane.add(btnSaveDo);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempPostgrad=null;
				StdManDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(382, 405, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblQualificationType = new JLabel("Qualification Type");
		lblQualificationType.setBounds(10, 266, 113, 14);
		contentPane.add(lblQualificationType);
		
		JLabel lblInstitute = new JLabel("Institute");
		lblInstitute.setBounds(10, 302, 80, 14);
		contentPane.add(lblInstitute);
		
		textInstitute = new JTextField();
		textInstitute.setToolTipText("");
		textInstitute.setBounds(131, 299, 86, 20);
		contentPane.add(textInstitute);
		textInstitute.setColumns(10);
		
		textQualification = new JTextField();
		textQualification.setBounds(161, 263, 199, 20);
		contentPane.add(textQualification);
		textQualification.setColumns(10);
		
		textGradYear = new JTextField();
		textGradYear.setBounds(131, 330, 86, 20);
		contentPane.add(textGradYear);
		textGradYear.setColumns(10);
		
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
		
		JLabel lblGraduatedYear = new JLabel("Graduated Year");
		lblGraduatedYear.setBounds(10, 333, 89, 14);
		contentPane.add(lblGraduatedYear);
		
		
		if (SharedData.tempPostgrad!=null)
		{
			lblLastInsertedStudent.setText("Editing Student : "+SharedData.tempPostgrad.getStdId());
			textStdId.setText(SharedData.tempPostgrad.getStdId());
			textStdId.setEditable(false);
			textStdName.setText(SharedData.tempPostgrad.getName());
			textStdAddress.setText(SharedData.tempPostgrad.getAddress());
			textDOB.setText(SharedData.tempPostgrad.getDOB());
			textYear.setText(Integer.toString(SharedData.tempPostgrad.getCurrentYear()));
			textQualification.setText(SharedData.tempPostgrad.getQualificationType());
			textInstitute.setText(SharedData.tempPostgrad.getInstitute());
			textGradYear.setText(Integer.toString(SharedData.tempPostgrad.getYear()));
		
			//Filling & Setting Course combo
			ResultSet rs = Course.getCoursePostGradList();
			try {
			while(rs.next())
			{
				comboBoxCourse.addItem(rs.getString(1));
				
			}
			comboBoxCourse.setSelectedItem(Course.getRelevantCourseName(SharedData.tempPostgrad.getCourseId()));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
			}
			
		}
		
		else 
		{
		//Show Available Courses when adding new student
		ResultSet rs = Course.getCoursePostGradList();
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
