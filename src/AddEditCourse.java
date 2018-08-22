import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class AddEditCourse extends JFrame {

	private JPanel contentPane;
	private static AddEditCourse frame;
	private JTextField textCourseId;
	private JTextField textName;
	private JTextField textCredits;
	private JLabel lblLastInsertedCourse;
	private JComboBox comboBox;
	private JComboBox comboBoxYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditCourse();
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
	public AddEditCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 441);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLastInsertedCourse = new JLabel("Last Inserted Course ID : "+Course.getLastCourseID());
		lblLastInsertedCourse.setBounds(283, 39, 188, 14);
		contentPane.add(lblLastInsertedCourse);
		
		JLabel lblAddeditCourses = new JLabel("Add/Edit Courses");
		lblAddeditCourses.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddeditCourses.setBounds(10, 11, 115, 23);
		contentPane.add(lblAddeditCourses);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(10, 81, 80, 14);
		contentPane.add(lblCourseId);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(10, 116, 80, 14);
		contentPane.add(lblCourseName);
		
		JLabel lblNumberOfYears = new JLabel("Number of Years");
		lblNumberOfYears.setBounds(10, 196, 115, 14);
		contentPane.add(lblNumberOfYears);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 157, 46, 14);
		contentPane.add(lblType);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Credits");
		lblTotalNumberOf.setBounds(10, 240, 135, 14);
		contentPane.add(lblTotalNumberOf);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SharedData.tempCourse==null)
				{
					Course c1 = new Course();
					c1.addData(textCourseId.getText(), textName.getText(), Integer.parseInt(comboBoxYear.getSelectedItem().toString()), comboBox.getSelectedItem().toString(), Integer.parseInt(textCredits.getText()));
					c1.saveNewData();
					JPanel_ManageCourse.table.setModel(DbUtils.resultSetToTableModel(Course.retriveCourses()));
					JOptionPane.showMessageDialog(null, "Course Added Succesfully");
					lblLastInsertedCourse.setText("Last Inserted Course ID : "+Course.getLastCourseID());
					textCourseId.setText("");
					textName.setText("");
					comboBoxYear.setSelectedIndex(0);
					comboBox.setSelectedIndex(0);
					textCredits.setText("24");
					
					
				}
				else
				{
					Course c2 = new Course();
					c2.changeData(textCourseId.getText(), textName.getText(), Integer.parseInt(comboBoxYear.getSelectedItem().toString()), comboBox.getSelectedItem().toString(), Integer.parseInt(textCredits.getText()));
					c2.saveData();
					JOptionPane.showMessageDialog(null, "Course Saved Succesfully");
					JPanel_ManageCourse.table.setModel(DbUtils.resultSetToTableModel(Course.retriveCourses()));
					SharedData.tempCourse=null;
					AdminDash.frame.enable();
					frame.dispose();
				}
			}
		});
		btnSubmit.setBounds(153, 294, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempCourse=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(285, 294, 89, 23);
		contentPane.add(btnExit);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex()==0) {
					textCredits.setText("24");
					comboBoxYear.setSelectedIndex(0);
				}
				else if (comboBox.getSelectedIndex()==1) {
					textCredits.setText("30");
					comboBoxYear.setSelectedIndex(1);
				}
				else {
					textCredits.setText("32");
					comboBoxYear.setSelectedIndex(2);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Master", "3-Year Bachelor", "4-Year Bachelor"}));
		comboBox.setBounds(153, 153, 120, 20);
		contentPane.add(comboBox);
		
		textCourseId = new JTextField();
		textCourseId.setBounds(153, 77, 86, 20);
		contentPane.add(textCourseId);
		textCourseId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(153, 112, 232, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textCredits = new JTextField();
		textCredits.setText("24");
		textCredits.setEditable(false);
		textCredits.setBounds(153, 236, 86, 20);
		contentPane.add(textCredits);
		textCredits.setColumns(10);
		
		comboBoxYear = new JComboBox();
		comboBoxYear.setEnabled(false);
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4"}));
		comboBoxYear.setBounds(153, 192, 51, 20);
		contentPane.add(comboBoxYear);
		
		//Fill Course Data in case of edit
		if (SharedData.tempCourse!=null)
		{
			lblLastInsertedCourse.setText("Editing Course : "+SharedData.tempCourse.getCourseId());
			textCourseId.setText(SharedData.tempCourse.getCourseId());
			textCourseId.setEditable(false);
			textName.setText(SharedData.tempCourse.getCourseName());
			
			int no_years = SharedData.tempCourse.getYears();
			
			if (no_years==2) {
				comboBoxYear.setSelectedIndex(0);
			}
			else if (no_years==3) {
				comboBoxYear.setSelectedIndex(1);
			}
			else {
				comboBoxYear.setSelectedIndex(2);
			}
			
			String courseType=SharedData.tempCourse.getType();
			
			if (courseType.equals("Master"))
			{
				comboBox.setSelectedIndex(0);
			}
			else if (courseType.equals("3-Year Bachelor"))
			{
				comboBox.setSelectedIndex(1);
			}
			else
			{
				comboBox.setSelectedIndex(2);
			}
			
			textCredits.setText(Integer.toString(SharedData.tempCourse.getTotalCredits()));
			
		}
	}
}
