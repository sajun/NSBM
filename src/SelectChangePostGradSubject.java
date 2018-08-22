import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Color;
import java.awt.Font;

public class SelectChangePostGradSubject extends JFrame {

	private JPanel contentPane;
	private static SelectChangePostGradSubject frame;
	private JTextField textName;
	private JTextField textStdId;
	private JTextField textCourseName;
	private JTextField textCurrentYear;
	private JList list_CompulsorySem1;
	private DefaultListModel CompulsoryListSem1;
	private DefaultListModel CompulsoryListSem2;
	private JLabel lblCompulsorySubjects;
	private JList list_1_OptionalSem1;
	private JList list_2_SelectedSem1;
	private DefaultListModel ToBeSelectedList_Sem1;
	private DefaultListModel SelectedList_Sem1;
	private DefaultListModel ToBeSelectedList_Sem2;
	private DefaultListModel SelectedList_Sem2;
	private JButton buttonSem1remove;
	private JButton btnExit;
	private JButton btnSubmit;
	private JList list_3_OptionalSem2;
	private JList list_4_SelectedSem2;
	private JList list_CompulsorySem2;
	private JLabel lblTotalCredits_1;
	private int totalCredits=0;
	private int allSelectedCredits=0;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SelectChangePostGradSubject();
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
	public SelectChangePostGradSubject() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 613);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		textName = new JTextField();
		textName.setEditable(false);
		textName.setBounds(123, 63, 159, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblStdId = new JLabel("Student ID");
		lblStdId.setBounds(27, 32, 64, 14);
		contentPane.add(lblStdId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(27, 69, 46, 14);
		contentPane.add(lblName);
		
		textStdId = new JTextField();
		textStdId.setEditable(false);
		textStdId.setBounds(123, 26, 86, 20);
		contentPane.add(textStdId);
		textStdId.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(27, 114, 86, 14);
		contentPane.add(lblCourseName);
		
		textCourseName = new JTextField();
		textCourseName.setEditable(false);
		textCourseName.setBounds(123, 108, 159, 20);
		contentPane.add(textCourseName);
		textCourseName.setColumns(10);
		
		JLabel lblCurrentYear = new JLabel("Current Year");
		lblCurrentYear.setBounds(27, 158, 86, 14);
		contentPane.add(lblCurrentYear);
		
		textCurrentYear = new JTextField();
		textCurrentYear.setEditable(false);
		textCurrentYear.setBounds(123, 152, 86, 20);
		contentPane.add(textCurrentYear);
		textCurrentYear.setColumns(10);
		
		CompulsoryListSem1 = new DefaultListModel();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(292, 31, 145, 141);
		contentPane.add(scrollPane);
		list_CompulsorySem1 = new JList(CompulsoryListSem1);
		scrollPane.setViewportView(list_CompulsorySem1);
		list_CompulsorySem1.setEnabled(false);
		
		lblCompulsorySubjects = new JLabel("Semester 1: Compulsory Subjects");
		lblCompulsorySubjects.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCompulsorySubjects.setBounds(285, 11, 170, 14);
		contentPane.add(lblCompulsorySubjects);
		
		ToBeSelectedList_Sem1 = new DefaultListModel();
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(58, 205, 145, 141);
		contentPane.add(scrollPane_2);
		list_1_OptionalSem1 = new JList(ToBeSelectedList_Sem1);
		scrollPane_2.setViewportView(list_1_OptionalSem1);
		list_1_OptionalSem1.setEnabled(false);
		
		SelectedList_Sem1 = new DefaultListModel();
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(338, 205, 145, 141);
		contentPane.add(scrollPane_3);
		list_2_SelectedSem1 = new JList(SelectedList_Sem1);
		scrollPane_3.setViewportView(list_2_SelectedSem1);
		list_2_SelectedSem1.setEnabled(false);
		
		JButton buttonSem1add = new JButton(">>>>");
		buttonSem1add.setEnabled(false);
		buttonSem1add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list_1_OptionalSem1.getSelectedIndex();
				if (selectedIndex>=0) {
					if (allSelectedCredits + Subject.getSubjectCredits(Subject.getRelevantSubId(ToBeSelectedList_Sem1.getElementAt(selectedIndex).toString()))<=totalCredits)
					{
						SelectedList_Sem1.addElement(ToBeSelectedList_Sem1.getElementAt(selectedIndex));
						allSelectedCredits += Subject.getSubjectCredits(Subject.getRelevantSubId(ToBeSelectedList_Sem1.getElementAt(selectedIndex).toString()));
						lblTotalCredits_1.setText("Credits : "+Integer.toString(allSelectedCredits)+" / "+Integer.toString(totalCredits));
						ToBeSelectedList_Sem1.removeElementAt(selectedIndex);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Exceed the Maximum Credits Allowed by Course !!!");
					}
					
				}
				
			}
		});
		buttonSem1add.setBounds(223, 237, 89, 23);
		contentPane.add(buttonSem1add);
		
		buttonSem1remove = new JButton("<<<<");
		buttonSem1remove.setEnabled(false);
		buttonSem1remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2_SelectedSem1.getSelectedIndex();
				if (selectedIndex>=0) {
					ToBeSelectedList_Sem1.addElement(SelectedList_Sem1.getElementAt(selectedIndex));
					allSelectedCredits -= Subject.getSubjectCredits(Subject.getRelevantSubId(SelectedList_Sem1.getElementAt(selectedIndex).toString()));
					lblTotalCredits_1.setText("Credits : "+Integer.toString(allSelectedCredits)+" / "+Integer.toString(totalCredits));
					SelectedList_Sem1.removeElementAt(selectedIndex);
				}
			}
		});
		buttonSem1remove.setBounds(223, 284, 89, 23);
		contentPane.add(buttonSem1remove);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempStdSubNew=null;
				SharedData.tempStdSubChange=null;
				StdManDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(452, 551, 89, 23);
		contentPane.add(btnExit);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (totalCredits==allSelectedCredits)
				{
				ArrayList temp = new ArrayList();
				for(int i=0;i<SelectedList_Sem1.getSize();i++) {
					temp.add(Subject.getRelevantSubId(SelectedList_Sem1.getElementAt(i).toString()));
				}
				for(int i=0;i<SelectedList_Sem2.getSize();i++) {
					temp.add(Subject.getRelevantSubId(SelectedList_Sem2.getElementAt(i).toString()));
				}
				
				if (SharedData.tempStdSubNew!=null) 
				{
					SharedData.tempStdSubNew.setMyOptionalSubjects(temp);
					SharedData.tempStdSubNew.saveNewData();
					SharedData.tempStdSubNew=null;
					StdManDash.frame.enable();
					JOptionPane.showMessageDialog(null, "Records Added Succesfully");
					JPanel_SelectStdSub.table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retrivePostgradSelectSub()));
					frame.dispose();
				}
				
				else if (SharedData.tempStdSubChange!=null) 
				{
					//Delete all optinal subject Data
					SharedData.tempStdSubChange.setMyOptionalSubjects(temp);
					SharedData.tempStdSubChange.removeSubjects();
					SharedData.tempStdSubChange.saveData();
					SharedData.tempStdSubChange=null;
					StdManDash.frame.enable();
					JOptionPane.showMessageDialog(null, "Records Changed Succesfully");
					JPanel_SelectStdSub.table.setModel(DbUtils.resultSetToTableModel(SelectStdSub.retrivePostgradChangeSub()));
					frame.dispose();
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select Optional Courses to have Suficient amount of Credits");
				}
				
			}
		});
		btnSubmit.setBounds(33, 551, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnSelectFirstYear = new JButton("Select First Year First Semester Subjects");
		btnSelectFirstYear.setEnabled(false);
		btnSelectFirstYear.setBounds(172, 551, 242, 23);
		contentPane.add(btnSelectFirstYear);
		
		CompulsoryListSem2 = new DefaultListModel();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(476, 32, 145, 141);
		contentPane.add(scrollPane_1);
		list_CompulsorySem2 = new JList(CompulsoryListSem2);
		scrollPane_1.setViewportView(list_CompulsorySem2);
		list_CompulsorySem2.setEnabled(false);
		
		JLabel lblSemesterCompulsory = new JLabel("Semester 2: Compulsory Subjects");
		lblSemesterCompulsory.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSemesterCompulsory.setBounds(469, 11, 170, 14);
		contentPane.add(lblSemesterCompulsory);
		
		ToBeSelectedList_Sem2 = new DefaultListModel();
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(58, 375, 145, 141);
		contentPane.add(scrollPane_4);
		list_3_OptionalSem2 = new JList(ToBeSelectedList_Sem2);
		scrollPane_4.setViewportView(list_3_OptionalSem2);
		list_3_OptionalSem2.setEnabled(false);
		
		SelectedList_Sem2 = new DefaultListModel();
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(338, 375, 145, 141);
		contentPane.add(scrollPane_5);
		list_4_SelectedSem2 = new JList(SelectedList_Sem2);
		scrollPane_5.setViewportView(list_4_SelectedSem2);
		list_4_SelectedSem2.setEnabled(false);
		
		JButton buttonSem2add = new JButton(">>>>");
		buttonSem2add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list_3_OptionalSem2.getSelectedIndex();
				if (selectedIndex>=0) {
					if (allSelectedCredits + Subject.getSubjectCredits(Subject.getRelevantSubId(ToBeSelectedList_Sem2.getElementAt(selectedIndex).toString()))<=totalCredits)
					{
						SelectedList_Sem2.addElement(ToBeSelectedList_Sem2.getElementAt(selectedIndex));
						allSelectedCredits += Subject.getSubjectCredits(Subject.getRelevantSubId(ToBeSelectedList_Sem2.getElementAt(selectedIndex).toString()));
						lblTotalCredits_1.setText("Credits : "+Integer.toString(allSelectedCredits)+" / "+Integer.toString(totalCredits));
						ToBeSelectedList_Sem2.removeElementAt(selectedIndex);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Exceed the Maximum Credits Allowed by Course !!!");
					}
				}
			}
		});
		buttonSem2add.setEnabled(false);
		buttonSem2add.setBounds(223, 406, 89, 23);
		contentPane.add(buttonSem2add);
		
		JButton buttonSem2remove = new JButton("<<<<");
		buttonSem2remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_4_SelectedSem2.getSelectedIndex();
				if (selectedIndex>=0) {
					ToBeSelectedList_Sem2.addElement(SelectedList_Sem2.getElementAt(selectedIndex));
					allSelectedCredits -= Subject.getSubjectCredits(Subject.getRelevantSubId(SelectedList_Sem2.getElementAt(selectedIndex).toString()));
					lblTotalCredits_1.setText("Credits : "+Integer.toString(allSelectedCredits)+" / "+Integer.toString(totalCredits));
					SelectedList_Sem2.removeElementAt(selectedIndex);
				}
			}
		});
		buttonSem2remove.setEnabled(false);
		buttonSem2remove.setBounds(223, 453, 89, 23);
		contentPane.add(buttonSem2remove);
		
		lblTotalCredits_1 = new JLabel("Credits : ");
		lblTotalCredits_1.setBounds(512, 355, 145, 14);
		contentPane.add(lblTotalCredits_1);
		
		
		
		if (SharedData.getCurrentSem()==1 && SharedData.getCurrentSemIsOpen()==1)
		{
			list_1_OptionalSem1.setEnabled(true);
			list_2_SelectedSem1.setEnabled(true);
			buttonSem1add.setEnabled(true);
			buttonSem1remove.setEnabled(true);
			list_CompulsorySem1.setEnabled(true);
			
			list_3_OptionalSem2.setEnabled(true);
			list_4_SelectedSem2.setEnabled(true);
			buttonSem2add.setEnabled(true);
			buttonSem2remove.setEnabled(true);
			list_CompulsorySem2.setEnabled(true);
			
		}
		else if (SharedData.getCurrentSem()==1 && SharedData.getCurrentSemIsOpen()==0)
		{
			list_3_OptionalSem2.setEnabled(true);
			list_4_SelectedSem2.setEnabled(true);
			buttonSem2add.setEnabled(true);
			buttonSem2remove.setEnabled(true);
			list_CompulsorySem2.setEnabled(true);
		}
		else if (SharedData.getCurrentSem()==2 && SharedData.getCurrentSemIsOpen()==1)
		{
			list_3_OptionalSem2.setEnabled(true);
			list_4_SelectedSem2.setEnabled(true);
			buttonSem2add.setEnabled(true);
			buttonSem2remove.setEnabled(true);
			list_CompulsorySem2.setEnabled(true);
		}
		
		
		if (SharedData.tempStdSubNew!=null)
		{
			totalCredits=Course.getCourseCredits(SharedData.tempStdSubNew.getCourseId());
			allSelectedCredits =SelectStdSub.getStudentSubjectCredits(SharedData.tempStdSubNew.getStdId(), SharedData.tempStdSubNew.getCurrentYear());
			
			for(int i=0;i<SharedData.tempStdSubNew.getMyCompulsorySubjects().size();i++) {
				allSelectedCredits += Subject.getSubjectCredits(SharedData.tempStdSubNew.getMyCompulsorySubjects().get(i).toString());
			}
			
			textStdId.setText(SharedData.tempStdSubNew.getStdId());
			textName.setText(SharedData.tempStdSubNew.getName());
			textCourseName.setText(Course.getRelevantCourseName(SharedData.tempStdSubNew.getCourseId()));
			textCurrentYear.setText(Integer.toString(SharedData.tempStdSubNew.getCurrentYear()));
			
			//Fill Lists
			for(int i=0;i<SharedData.tempStdSubNew.getMyCompulsorySubjects().size();i++) {
				if(Subject.getRelevantSemester(SharedData.tempStdSubNew.getMyCompulsorySubjects().get(i).toString())==1)
				{
					CompulsoryListSem1.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubNew.getMyCompulsorySubjects().get(i).toString()));
				}
				else
				{
					CompulsoryListSem2.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubNew.getMyCompulsorySubjects().get(i).toString()));
				}
				
			}
			for(int i=0;i<SharedData.tempStdSubNew.getMyOptionalSubjects().size();i++) {
				if(Subject.getRelevantSemester(SharedData.tempStdSubNew.getMyOptionalSubjects().get(i).toString())==1)
				{
					ToBeSelectedList_Sem1.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubNew.getMyOptionalSubjects().get(i).toString()));
				}
				else
				{
					ToBeSelectedList_Sem2.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubNew.getMyOptionalSubjects().get(i).toString()));
				}
			}
		}
		else if (SharedData.tempStdSubChange!=null)
		{
			totalCredits=Course.getCourseCredits(SharedData.tempStdSubChange.getCourseId());
			allSelectedCredits =SelectStdSub.getStudentSubjectCredits(SharedData.tempStdSubChange.getStdId(), SharedData.tempStdSubChange.getCurrentYear());
			
			
			textStdId.setText(SharedData.tempStdSubChange.getStdId());
			textName.setText(SharedData.tempStdSubChange.getName());
			textCourseName.setText(Course.getRelevantCourseName(SharedData.tempStdSubChange.getCourseId()));
			textCurrentYear.setText(Integer.toString(SharedData.tempStdSubChange.getCurrentYear()));
			
			//Filling compulsory List
			for(int i=0;i<SharedData.tempStdSubChange.getMyCompulsorySubjects().size();i++) {
				if(Subject.getRelevantSemester(SharedData.tempStdSubChange.getMyCompulsorySubjects().get(i).toString())==1) 
				{
					CompulsoryListSem1.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubChange.getMyCompulsorySubjects().get(i).toString()));
				}
				else
				{
					CompulsoryListSem2.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubChange.getMyCompulsorySubjects().get(i).toString()));
				}
				
			}
			
			//Filling Selected Optional List
			ArrayList SelectedSubjects = new ArrayList();
			SelectedSubjects = SelectStdSub.getAlreadySelectedOptionalSubjects(SharedData.tempStdSubChange.getStdId(), SharedData.tempStdSubChange.getCurrentYear());
			for(int i=0;i<SelectedSubjects.size();i++) {
				if(Subject.getRelevantSemester(SelectedSubjects.get(i).toString())==1)
				{
					SelectedList_Sem1.addElement(Subject.getRelevantSubjectName(SelectedSubjects.get(i).toString()));
				}
				else
				{
					SelectedList_Sem2.addElement(Subject.getRelevantSubjectName(SelectedSubjects.get(i).toString()));
				}
			}
			//Filling remaining optional Subjects
			
			for(int i=0;i<SharedData.tempStdSubChange.getMyOptionalSubjects().size();i++) {
				if( ! (SelectedSubjects.contains(SharedData.tempStdSubChange.getMyOptionalSubjects().get(i)))) {
					if(Subject.getRelevantSemester(SharedData.tempStdSubChange.getMyOptionalSubjects().get(i).toString())==1) {
						ToBeSelectedList_Sem1.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubChange.getMyOptionalSubjects().get(i).toString()));
					}
					else
					{
						ToBeSelectedList_Sem2.addElement(Subject.getRelevantSubjectName(SharedData.tempStdSubChange.getMyOptionalSubjects().get(i).toString()));
					}
				}
			}
			
		}
		
		lblTotalCredits_1.setText("Credits : "+Integer.toString(allSelectedCredits)+" / "+Integer.toString(totalCredits));		
		
		
	}

}
