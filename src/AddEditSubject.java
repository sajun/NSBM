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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;

public class AddEditSubject extends JFrame {

	private JPanel contentPane;
	private JLabel lblLastInsertedSubject;
	private JLabel lblSubjectId;
	private JLabel lblSubjectName;
	private JLabel lblNumberOfCredits;
	private JLabel lblPrice;
	private JLabel lblCourseId;
	private JTextField textSubjectId;
	private JTextField textSubjectName;
	private JTextField textCredits;
	private JTextField textPrice;
	private static AddEditSubject frame;
	private JCheckBox chckbxPracticle;
	private JCheckBox chckbxTheory;
	private JCheckBox chckbxOptional;
	private String selectedCourseId;
	private JComboBox comboBoxSem;
	private JComboBox comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditSubject();
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
	
	public AddEditSubject() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 505);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddeditSubject = new JLabel("Add/Edit Subject");
		lblAddeditSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddeditSubject.setBounds(10, 11, 159, 23);
		contentPane.add(lblAddeditSubject);
		
		lblLastInsertedSubject = new JLabel("Last Inserted Subject ID : "+Subject.getLastSubjectID());
		lblLastInsertedSubject.setBounds(273, 39, 243, 14);
		contentPane.add(lblLastInsertedSubject);
		
		lblSubjectId = new JLabel("Subject ID");
		lblSubjectId.setBounds(10, 81, 95, 14);
		contentPane.add(lblSubjectId);
		
		lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setBounds(10, 121, 116, 14);
		contentPane.add(lblSubjectName);
		
		lblNumberOfCredits = new JLabel("Number of Credits");
		lblNumberOfCredits.setBounds(10, 164, 116, 14);
		contentPane.add(lblNumberOfCredits);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 204, 46, 14);
		contentPane.add(lblPrice);
		
		chckbxPracticle = new JCheckBox("Practicle");
		chckbxPracticle.setBounds(141, 241, 97, 23);
		contentPane.add(chckbxPracticle);
		
		chckbxTheory = new JCheckBox("Theory");
		chckbxTheory.setBounds(257, 241, 95, 23);
		contentPane.add(chckbxTheory);
		
		JLabel lblRelevantCourse = new JLabel("Relevant Course");
		lblRelevantCourse.setBounds(10, 285, 95, 14);
		contentPane.add(lblRelevantCourse);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedCourseId=Course.getCourseID(comboBox.getSelectedItem().toString());
				lblCourseId.setText("Course ID : "+selectedCourseId);
				comboBox_1.removeAllItems();
				for(int i=1; i<=Course.getCourseYears(Course.getCourseID(comboBox.getSelectedItem().toString()));i++) {
					comboBox_1.addItem(Integer.toString(i));
				}
			}
		});
		comboBox.setBounds(141, 282, 235, 20);
		contentPane.add(comboBox);
		
		
		lblCourseId = new JLabel("Course ID : ");
		lblCourseId.setBounds(386, 285, 130, 14);
		contentPane.add(lblCourseId);
		
		textSubjectId = new JTextField();
		textSubjectId.setBounds(141, 78, 86, 20);
		contentPane.add(textSubjectId);
		textSubjectId.setColumns(10);
		
		textSubjectName = new JTextField();
		textSubjectName.setText("");
		textSubjectName.setBounds(141, 118, 218, 20);
		contentPane.add(textSubjectName);
		textSubjectName.setColumns(10);
		
		textCredits = new JTextField();
		textCredits.setBounds(141, 161, 86, 20);
		contentPane.add(textCredits);
		textCredits.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(141, 201, 86, 20);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textCredits.getText().equals("")||textPrice.getText().equals("")))
				{
				if (SharedData.tempSubject==null)
				{
					Subject s1 = new Subject();
					s1.addData(textSubjectId.getText(),textSubjectName.getText(), chckbxPracticle.isSelected()?1:0, chckbxTheory.isSelected()?1:0, chckbxOptional.isSelected()?1:0, Integer.parseInt(textCredits.getText()), Integer.parseInt(textPrice.getText()), selectedCourseId, Integer.parseInt(comboBox_1.getSelectedItem().toString()), Integer.parseInt(comboBoxSem.getSelectedItem().toString()) );
					s1.saveNewData();
					JPanel_ManageSubject.table.setModel(DbUtils.resultSetToTableModel(Subject.retriveSubjects()));
					JOptionPane.showMessageDialog(null, "Subject Added Succesfully");
					lblLastInsertedSubject.setText("Last Inserted Subject ID : "+Subject.getLastSubjectID());
					textSubjectId.setText("");
					textSubjectName.setText("");
					chckbxPracticle.setSelected(false);
					chckbxTheory.setSelected(false);
					chckbxOptional.setSelected(false);
					textCredits.setText("");
					textPrice.setText("");
					
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					comboBoxSem.setSelectedIndex(0);
					
				}
				else
				{
					Subject s2 = new Subject();
					s2.changeData(textSubjectId.getText(),textSubjectName.getText(), chckbxPracticle.isSelected()?1:0, chckbxTheory.isSelected()?1:0, chckbxOptional.isSelected()?1:0, Integer.parseInt(textCredits.getText()), Integer.parseInt(textPrice.getText()), selectedCourseId, Integer.parseInt(comboBox_1.getSelectedItem().toString()), Integer.parseInt(comboBoxSem.getSelectedItem().toString())  );
					s2.saveData();
					JOptionPane.showMessageDialog(null, "Subject Saved Succesfully");
					JPanel_ManageSubject.table.setModel(DbUtils.resultSetToTableModel(Subject.retriveSubjects()));
					SharedData.tempSubject=null;
					AdminDash.frame.enable();
					frame.dispose();
				}
			}
			}
		});
		
		btnSubmit.setBounds(141, 411, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempSubject=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(273, 411, 89, 23);
		contentPane.add(btnExit);
		
		chckbxOptional = new JCheckBox("Optional");
		chckbxOptional.setBounds(364, 241, 97, 23);
		contentPane.add(chckbxOptional);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(10, 323, 46, 14);
		contentPane.add(lblYear);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(10, 358, 66, 14);
		contentPane.add(lblSemester);
		
		comboBoxSem = new JComboBox();
		comboBoxSem.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		comboBoxSem.setBounds(141, 355, 54, 20);
		contentPane.add(comboBoxSem);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"10"}));
		comboBox_1.setBounds(141, 320, 54, 20);
		contentPane.add(comboBox_1);
		
		if (SharedData.tempSubject!=null)
		{
			lblLastInsertedSubject.setText("Editing Subject : "+SharedData.tempSubject.getSubId());
			textSubjectId.setText(SharedData.tempSubject.getSubId());
			textSubjectId.setEditable(false);
			textSubjectName.setText(SharedData.tempSubject.getSubName());
			textCredits.setText(Integer.toString(SharedData.tempSubject.getCredits()));
			textPrice.setText(Integer.toString(SharedData.tempSubject.getPrice()));
			chckbxPracticle.setSelected(SharedData.tempSubject.getIsPracticle()==1?true:false);
			chckbxTheory.setSelected(SharedData.tempSubject.getIsTheory()==1?true:false);
			chckbxOptional.setSelected(SharedData.tempSubject.getIsOptional()==1?true:false);
			
			//Filling comboBox
			ResultSet rs = Course.getCourseList();
			try {
			while(rs.next())
			{
				comboBox.addItem(rs.getString(1));
			}
			comboBox.setSelectedItem(Course.getRelevantCourseName(SharedData.tempSubject.getCourseId()));
			comboBox_1.removeAllItems();
			for(int i=1; i<=Course.getCourseYears(Course.getCourseID(comboBox.getSelectedItem().toString()));i++) {
				comboBox_1.addItem(Integer.toString(i));
			}
			comboBox_1.setSelectedItem(Integer.toString(SharedData.tempSubject.getYear()));
			comboBoxSem.setSelectedItem(Integer.toString(SharedData.tempSubject.getSemester()));
			
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3);
			}
		}
		else
		{
		//Filling ComboBox When adding new subject
		ResultSet rs = Course.getCourseList();
		try {
		while(rs.next())
		{
			comboBox.addItem(rs.getString(1));
		}
		
		}catch (Exception e3) {
			JOptionPane.showMessageDialog(null, e3);
		}
		comboBox_1.removeAllItems();
		for(int i=1; i<=Course.getCourseYears(Course.getCourseID(comboBox.getSelectedItem().toString()));i++) {
			comboBox_1.addItem(Integer.toString(i));
		}
		
		
		}
		//System.out.println(comboBox.getSelectedItem().toString());
		//lblCourseId.setText("Course ID : "+Course.getCourseID("C3"));
		
		
		
	}
}
