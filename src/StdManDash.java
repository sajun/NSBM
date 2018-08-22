import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class StdManDash extends JFrame {

	private JPanel contentPane;
	public static StdManDash frame;
	private JPanel_ManageStudent ManageStudentPanel = new JPanel_ManageStudent();
	private JPanel_SelectStdSub SelectStdSubPanel = new JPanel_SelectStdSub();
	private JPanel_ManagePayment ManagePayment = new JPanel_ManagePayment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StdManDash();
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
	public StdManDash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 665);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imglogo = new ImageIcon(this.getClass().getResource("/h2.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		lblLogo.setBounds(267, 11, 654, 96);
		contentPane.add(lblLogo);
		
		JLabel lblWelcome = new JLabel("Welcome "+SharedData.getLoggedUser());
		lblWelcome.setBounds(1130, 26, 104, 14);
		contentPane.add(lblWelcome);
		
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
		
		JPanel middle_panel = new JPanel();
		middle_panel.setBackground(new Color(204, 255, 153));
		middle_panel.setBounds(152, 118, 1082, 500);
		contentPane.add(middle_panel);
		middle_panel.setLayout(new BorderLayout(0, 0));
		middle_panel.add(ManageStudentPanel);
		middle_panel.validate();
		middle_panel.repaint();
		
		JButton btnManageStudents = new JButton("Manage Students");
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(ManageStudentPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManageStudents.setBounds(3, 174, 145, 23);
		contentPane.add(btnManageStudents);
		
		JButton btnManagePayments = new JButton("Manage Payments");
		btnManagePayments.setEnabled(false);
		btnManagePayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(ManagePayment);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnManagePayments.setBounds(3, 282, 145, 23);
		contentPane.add(btnManagePayments);
		
		JButton btnSubjectSelection = new JButton("Subject Selection");
		btnSubjectSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle_panel.removeAll();
				middle_panel.add(SelectStdSubPanel);
				middle_panel.validate();
				middle_panel.repaint();
			}
		});
		btnSubjectSelection.setBounds(3, 228, 145, 23);
		contentPane.add(btnSubjectSelection);
		
		JLabel labelstdman = new JLabel("");
		labelstdman.setHorizontalAlignment(SwingConstants.CENTER);
		Image imglogo2 = new ImageIcon(this.getClass().getResource("/stdman2.png")).getImage();
		labelstdman.setIcon(new ImageIcon(imglogo2));
		labelstdman.setBounds(27, 11, 115, 108);
		contentPane.add(labelstdman);
		
	}
}
