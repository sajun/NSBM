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

public class AddEditHall extends JFrame {

	private JPanel contentPane;
	private static AddEditHall frame;
	private JTextField textHallId;
	private JTextField textHallCapacity;
	private JTextField textLocation;
	private JLabel lblLastInsertHall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddEditHall();
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
	public AddEditHall() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 352);
		
		setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddeditHall = new JLabel("Add/Edit Hall");
		lblAddeditHall.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAddeditHall.setBounds(10, 11, 113, 23);
		contentPane.add(lblAddeditHall);
		
		lblLastInsertHall = new JLabel("Last Insert Hall ID : "+Hall.getLastHallID());
		lblLastInsertHall.setBounds(302, 39, 168, 14);
		contentPane.add(lblLastInsertHall);
		
		JLabel lblHallId = new JLabel("Hall ID");
		lblHallId.setBounds(10, 80, 46, 14);
		contentPane.add(lblHallId);
		
		JLabel lblHallCapacity = new JLabel("Hall Capacity");
		lblHallCapacity.setBounds(10, 115, 97, 14);
		contentPane.add(lblHallCapacity);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 150, 64, 14);
		contentPane.add(lblLocation);
		
		textHallId = new JTextField();
		textHallId.setBounds(117, 77, 102, 20);
		contentPane.add(textHallId);
		textHallId.setColumns(10);
		
		textHallCapacity = new JTextField();
		textHallCapacity.setBounds(117, 112, 103, 20);
		contentPane.add(textHallCapacity);
		textHallCapacity.setColumns(10);
		
		textLocation = new JTextField();
		textLocation.setBounds(117, 147, 128, 20);
		contentPane.add(textLocation);
		textLocation.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textHallCapacity.getText().equals(""))
				{
				if (SharedData.tempHall==null)
				{
					Hall l1 = new Hall();
					l1.addData(textHallId.getText(), Integer.parseInt(textHallCapacity.getText()), textLocation.getText());
					l1.saveNewData();
					JPanel_ManageHall.table.setModel(DbUtils.resultSetToTableModel(Hall.retriveHalls()));
					JOptionPane.showMessageDialog(null, "Hall Added Succesfully");
					lblLastInsertHall.setText("Last Inserted Hall ID : "+Hall.getLastHallID());
					textHallId.setText("");
					textHallCapacity.setText("");
					textLocation.setText("");
				}
				else
				{
					Hall l2 = new Hall();
					l2.changeData(textHallId.getText(), Integer.parseInt(textHallCapacity.getText()), textLocation.getText());
					l2.saveData();
					JOptionPane.showMessageDialog(null, "Hall Saved Succesfully");
					JPanel_ManageHall.table.setModel(DbUtils.resultSetToTableModel(Hall.retriveHalls()));
					SharedData.tempHall=null;
					AdminDash.frame.enable();
					frame.dispose();
				}
			}
			}
			
		});
		btnSubmit.setBounds(117, 196, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharedData.tempHall=null;
				AdminDash.frame.enable();
				frame.dispose();
			}
		});
		btnExit.setBounds(249, 196, 89, 23);
		contentPane.add(btnExit);
		
		//Fill Hall Data in case of edit
				if (SharedData.tempHall!=null)
				{
					lblLastInsertHall.setText("Editing Hall : "+SharedData.tempHall.getHallId());
					textHallId.setText(SharedData.tempHall.getHallId());
					textHallId.setEditable(false);
					textHallCapacity.setText(Integer.toString(SharedData.tempHall.getCapacity()));
					textLocation.setText(SharedData.tempHall.getLocation());
				}
	}

}
