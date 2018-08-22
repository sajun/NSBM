import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class AdminDash extends JFrame {

	private JPanel contentPane;
	public static AdminDash frame;
	private JPanel_ManageUser ManageUserPanel = new JPanel_ManageUser();
	private JPanel_ManageLecturer ManageLecturerPanel = new JPanel_ManageLecturer();
	private JPanel_ManageInstructor ManageInstructorPanel = new JPanel_ManageInstructor();
	private JPanel_ManageLab ManageLabPanel = new JPanel_ManageLab();
	private JPanel_ManageHall ManageHallPanel = new JPanel_ManageHall();
	private JPanel_ManageCourse ManageCoursePanel = new JPanel_ManageCourse();
	private JPanel_ManageSubject ManageSubjectPanel = new JPanel_ManageSubject();
	private JPanel_Configurations Configurations = new JPanel_Configurations();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminDash();
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
	public AdminDash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 665);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel middle_panel = new JPanel();
		middle_panel.setToolTipText("");
		middle_panel.setBackground(new Color(204, 255, 153));
		middle_panel.setBounds(152, 118, 1082, 500);
		contentPane.add(middle_panel);
		middle_panel.setLayout(new BorderLayout(0, 0));
		middle_panel.add(ManageUserPanel);
		middle_panel.validate();
		middle_panel.repaint();
		
		JLabel lblWelcome = new JLabel("Welcome "+SharedData.getLoggedUser());
		lblWelcome.setBounds(1130, 26, 104, 14);
		contentPane.add(lblWelcome);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imglogo = new ImageIcon(this.getClass().getResource("/h2.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		lblLogo.setBounds(267, 11, 654, 96);
		contentPane.add(lblLogo);
		
		JButton btnConfigurations = new JButton("Configurations");
		btnConfigurations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				middle_panel.removeAll();
				middle_panel.add(Configurations);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnConfigurations.setBounds(3, 469, 145, 23);
		getContentPane().add(btnConfigurations);
		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				middle_panel.removeAll();
				middle_panel.add(ManageUserPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageUsers.setBounds(3, 118, 145, 23);
		getContentPane().add(btnManageUsers);
		
		JButton btnManageLecturers = new JButton("Manage Lecturers");
		btnManageLecturers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				middle_panel.removeAll();
				middle_panel.add(ManageLecturerPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageLecturers.setBounds(3, 164, 145, 23);
		getContentPane().add(btnManageLecturers);
		
		JButton btnManageIstructors = new JButton("Manage Istructors");
		btnManageIstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				middle_panel.removeAll();
				middle_panel.add(ManageInstructorPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageIstructors.setBounds(3, 208, 145, 23);
		getContentPane().add(btnManageIstructors);
		
		JButton btnManageLabs = new JButton("Manage Labs");
		btnManageLabs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(ManageLabPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageLabs.setBounds(3, 252, 145, 23);
		getContentPane().add(btnManageLabs);
		
		JButton btnManageHalls = new JButton("Manage Halls");
		btnManageHalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(ManageHallPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageHalls.setBounds(3, 296, 145, 23);
		getContentPane().add(btnManageHalls);
		
		JButton btnManageCourses = new JButton("Manage Courses");
		btnManageCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(ManageCoursePanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageCourses.setBounds(3, 340, 145, 23);
		getContentPane().add(btnManageCourses);
		
		JButton btnManageSubjects = new JButton("Manage Subjects");
		btnManageSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				middle_panel.removeAll();
				middle_panel.add(ManageSubjectPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageSubjects.setBounds(3, 387, 145, 23);
		getContentPane().add(btnManageSubjects);
		
		JLabel lblLogout = new JLabel("(logout)");
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SharedData.setLoggedUser(null);
				frame.dispose();
				Login.main(null);
			}
		});
		lblLogout.setBounds(1150, 51, 46, 14);
		contentPane.add(lblLogout);
		
		JButton btnAllocations = new JButton("Allocations");
		btnAllocations.setEnabled(false);
		btnAllocations.setBounds(3, 435, 145, 23);
		contentPane.add(btnAllocations);
		
		JLabel labeladminman = new JLabel("");
		labeladminman.setHorizontalAlignment(SwingConstants.CENTER);
		Image imglogo2 = new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		labeladminman.setIcon(new ImageIcon(imglogo2));
		labeladminman.setBounds(27, 3, 115, 108);
		contentPane.add(labeladminman);
	}
}
