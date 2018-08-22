import java.awt.EventQueue;
import java.util.*;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textUserName;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		conn=dbConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 153));
		frame.setBounds(100, 100, 766, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setForeground(Color.RED);
		lblLogo.setBackground(Color.YELLOW);
		Image imglogo = new ImageIcon(this.getClass().getResource("/h2.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		lblLogo.setBounds(10, 11, 730, 96);
		frame.getContentPane().add(lblLogo);
		
		JLabel lblUserpic = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/syslog.png")).getImage();
		Image img_rz = img.getScaledInstance(128, -1, Image.SCALE_DEFAULT);
		lblUserpic.setIcon(new ImageIcon(img_rz));
		lblUserpic.setBounds(51, 102, 129, 202);
		frame.getContentPane().add(lblUserpic);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(203, 145, 129, 28);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(203, 203, 129, 28);
		frame.getContentPane().add(lblPassword);
		
		textUserName = new JTextField();
		textUserName.setBounds(376, 149, 157, 24);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(376, 207, 157, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="SELECT * FROM User WHERE username=? AND password=? AND isAdmin=1 AND FacId=?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, textUserName.getText());
					ps.setString(2, SharedData.md5(passwordField.getText()));
					ps.setString(3, SharedData.getFacId());
					//ps.setString(3, "1");
					ResultSet rs = ps.executeQuery();
					
					int count=0;
					while(rs.next())
					{
						count++;
					}
					
					if (count==0) {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					else if (count==1) {
						SharedData.setLoggedUser(textUserName.getText());
						frame.dispose();
						Admin admin = new Admin();
						admin.login();
					}
					else {
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					
					rs.close();
					ps.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAdminLogin.setBounds(204, 282, 114, 23);
		frame.getContentPane().add(btnAdminLogin);
		
		JButton btnStdManLogin = new JButton("Student Manager Login");
		btnStdManLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="SELECT * FROM User WHERE username=? AND password=? AND isStdMan=1 AND FacId=?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, textUserName.getText());
					ps.setString(2, SharedData.md5(passwordField.getText()));
					ps.setString(3, SharedData.getFacId());
					//ps.setString(3, "1");
					ResultSet rs = ps.executeQuery();
					
					int count=0;
					while(rs.next())
					{
						count++;
					}
					
					if (count==0) {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					else if (count==1) {
						SharedData.setLoggedUser(textUserName.getText());
						frame.dispose();
						StdMan sm = new StdMan();
						sm.login();
					}
					else {
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					
					rs.close();
					ps.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnStdManLogin.setBounds(359, 282, 174, 23);
		frame.getContentPane().add(btnStdManLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(376, 328, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnExamManLogin = new JButton("Exam Manager Login");
		btnExamManLogin.setEnabled(false);
		btnExamManLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="SELECT * FROM User WHERE username=? AND password=? AND isExamMan=1 AND FacId=?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, textUserName.getText());
					ps.setString(2, SharedData.md5(passwordField.getText()));
					ps.setString(3, SharedData.getFacId());
					//ps.setString(3, "1");
					ResultSet rs = ps.executeQuery();
					
					int count=0;
					while(rs.next())
					{
						count++;
					}
					
					if (count==0) {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					else if (count==1) {
						SharedData.setLoggedUser(textUserName.getText());
						frame.dispose();
						ExamMan em = new ExamMan();
						em.login();
					}
					else {
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						textUserName.setText("");
						passwordField.setText("");
						textUserName.requestFocus(true);
					}
					
					rs.close();
					ps.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnExamManLogin.setBounds(566, 282, 157, 23);
		frame.getContentPane().add(btnExamManLogin);
	}
}
