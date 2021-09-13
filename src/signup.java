// ako zatreba signup

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

public class signup {

	private JFrame frame;
	private JTextField unameField;
	private JTextField snameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup window = new signup();
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
	public signup() {
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

	public boolean isUsernameExist(String user) {
		
		boolean userExist = false;
		Connection con = connection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `zaposleni` WHERE `ime` = ? AND `prezime` = ?");
			ps.setString(1, unameField.getText());
			ps.setString(2, snameField.getText());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userExist = true;
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return userExist;
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
		unameField.setBackground(Color.WHITE);
		unameField.setBounds(125, 112, 370, 43);
		frame.getContentPane().add(unameField);
		unameField.setColumns(10);
		
		snameField = new JTextField();
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
					
					try {
						ps = con.prepareStatement("INSERT INTO `zaposleni`(`ime`, `prezime`, `profil`) VALUES (?, ?, ?)");
						ps.setString(1, unameField.getText());
						ps.setString(2, snameField.getText());
						ps.setString(3, "doktor");
						
						if(isUsernameExist(unameField.getText())) {
							JOptionPane.showMessageDialog(null, "Korisnik postoji!");
						}else {
							if(ps.executeUpdate() != 0) {
								JOptionPane.showMessageDialog(null, "Uspešno kreiranje!");
							}else {
								JOptionPane.showMessageDialog(null, "Neuspešno!");
							}
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
