import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.*;

public class Login {

	public JFrame frame;
	private JTextField unameField;
	private JTextField snameField;

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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	public boolean verifyData() {
		
		if(unameField.getText().equals("") || snameField.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Popunite oba polja!");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(125, 56, 153, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(125, 165, 123, 51);
		frame.getContentPane().add(lblNewLabel_1);
		
		unameField = new JTextField();
		unameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		unameField.setBackground(Color.WHITE);
		unameField.setBounds(125, 112, 370, 43);
		frame.getContentPane().add(unameField);
		unameField.setColumns(10);
		
		snameField = new JTextField();
		snameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		snameField.setColumns(10);
		snameField.setBackground(Color.WHITE);
		snameField.setBounds(125, 213, 370, 43);
		frame.getContentPane().add(snameField);
		
		JButton loginbtn = new JButton("Prijava");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(verifyData()) {
					Connection con = connection.getConnection();
					PreparedStatement ps;
					ResultSet rs;
					
					try {
						ps = con.prepareStatement("SELECT * FROM `zaposleni` WHERE `ime` = ? AND `prezime` = ?");
						ps.setString(1, unameField.getText());
						ps.setString(2, snameField.getText());
						rs = ps.executeQuery();
						
						if(rs.next()) {
							
							home homef = new home();
							homef.frameH.setVisible(true);
							homef.frameH.pack();
							homef.frameH.setLocationRelativeTo(null);
							homef.frameH.setBounds(100, 100, 1000, 660);
							homef.frameH.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							
							homef.loggedUser.setText(rs.getString(2));
							homef.loggedUser2.setText(rs.getString(3));
							homef.loggedUser3.setText(rs.getString(4));
							
							frame.dispose();
							
						}else {
							JOptionPane.showMessageDialog(null, "Neispravni podaci!");
						}
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		loginbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginbtn.setBounds(231, 303, 209, 59);
		frame.getContentPane().add(loginbtn);
		
		
	}
}
