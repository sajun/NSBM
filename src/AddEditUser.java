import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;


public class AddEditUser extends JFrame {

	private JPanel contentPane;
	private JTextField textUserID;
	private JTextField textUsername;
	private static AddEditUser frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditUser();
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
	
	
	public AddEditUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 431);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false); 
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddEdit = new JLabel("Add / Edit User");
		lblAddEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddEdit.setBounds(10, 11, 139, 23);
		contentPane.add(lblAddEdit);
		
		JLabel lblUserId = new JLabel("User ID :");
		lblUserId.setBounds(10, 72, 73, 14);
		contentPane.add(lblUserId);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(10, 123, 73, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 172, 73, 14);
		contentPane.add(lblPassword);
		
		JCheckBox chckbxIsadmin = new JCheckBox("IsAdmin");
		chckbxIsadmin.setBounds(97, 213, 97, 23);
		contentPane.add(chckbxIsadmin);
		
		JCheckBox chckbxIsstudentManager = new JCheckBox("IsStudent Manager");
		chckbxIsstudentManager.setBounds(97, 261, 132, 23);
		contentPane.add(chckbxIsstudentManager);
		
		JCheckBox chckbxIsexamManager = new JCheckBox("IsExam Manager");
		chckbxIsexamManager.setBounds(97, 309, 132, 23);
		contentPane.add(chckbxIsexamManager);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (SharedData.tempUser==null) {
				User u1 = new User();
				u1.addData(textUsername.getText(), passwordField.getText(), chckbxIsadmin.isSelected() ? 1:0, chckbxIsstudentManager.isSelected() ? 1:0, chckbxIsexamManager.isSelected() ? 1:0);
				u1.saveNewData();
				JPanel_ManageUser.table.setModel(DbUtils.resultSetToTableModel(User.retriveUsers()));
				JOptionPane.showMessageDialog(null, "User Added Succesfully");
				textUserID.setText(Integer.toString(Integer.parseInt(textUserID.getText())+1));
				textUsername.setText("");
				passwordField.setText("");
				chckbxIsadmin.setSelected(false);
				chckbxIsstudentManager.setSelected(false);
				chckbxIsexamManager.setSelected(false);
				}
				else
				{
					User u2 = new User();
					u2.changeData(Integer.parseInt(textUserID.getText()), textUsername.getText(), passwordField.getText(), chckbxIsadmin.isSelected() ? 1:0, chckbxIsstudentManager.isSelected() ? 1:0, chckbxIsexamManager.isSelected() ? 1:0);
					if (!("".equals(passwordField.getText())))
					{
					u2.saveData();
					JOptionPane.showMessageDialog(null, "User Saved Succesfully");
					JPanel_ManageUser.table.setModel(DbUtils.resultSetToTableModel(User.retriveUsers()));
					SharedData.tempUser=null;
					AdminDash.frame.enable();
					frame.dispose();
			
					}
					else
					{
					u2.saveDataNoPass();
					JOptionPane.showMessageDialog(null, "User Saved Succesfully");
					JPanel_ManageUser.table.setModel(DbUtils.resultSetToTableModel(User.retriveUsers()));
					SharedData.tempUser=null;
					AdminDash.frame.enable();
					frame.dispose();
				
					}
				}
			}
		});
		btnSubmit.setBounds(97, 357, 89, 23);
		contentPane.add(btnSubmit);
		
		textUserID = new JTextField();
		textUserID.setBounds(97, 69, 86, 20);
		contentPane.add(textUserID);
		textUserID.setColumns(10);
		
		textUsername = new JTextField();
		textUsername.setBounds(97, 120, 132, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		//SetNext UserID (Adding User situation)
		textUserID.setText(Integer.toString(User.getLastUserID()+1));
		textUserID.setEditable(false);
		
		
		//Fill UserData in case of EditUser
		if (SharedData.tempUser !=null)
		{
			
			textUserID.setText(Integer.toString(SharedData.tempUser.getUserID()));
			textUsername.setText(SharedData.tempUser.getUsername());
			//passwordField.setText("******");
			chckbxIsadmin.setSelected(SharedData.tempUser.getIsAdmin()==1?true:false);
			chckbxIsstudentManager.setSelected(SharedData.tempUser.getIsStdMan()==1?true:false);
			chckbxIsexamManager.setSelected(SharedData.tempUser.getIsExamMan()==1?true:false);
			
		}
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempUser=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(229, 357, 89, 23);
		contentPane.add(btnExit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 169, 132, 20);
		contentPane.add(passwordField);
	}
}
