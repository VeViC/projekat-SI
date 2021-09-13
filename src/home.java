import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;

public class home {

	public JFrame frameH;
	public JLabel loggedUser;
	public JLabel loggedUser2;
	public JLabel loggedUser3;
	public JLabel idField;
	private JTextField nameVField;
	private JTextField snameVField;
	private JTextField phoneField;
	private JTextField mailField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
					window.frameH.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public home() {
		initialize();
		appendTable();
		
		
	}
	
	public boolean verifyDataV() {
		
		if(nameVField.getText().equals("") || snameVField.getText().equals("") || mailField.getText().equals("") || phoneField.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Popunite sva polja!");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean verifyDataVDel() {
		
		if(nameVField.getText().equals("") || snameVField.getText().equals("") || mailField.getText().equals("") || phoneField.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Izaberite Vlasnika!");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean vExist(vlasnici cont) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `vlasnici` WHERE `email` = ?");
			ps.setString(1, cont.getEmail());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Vlasnik sa datim e-mailom postoji!");
				return false;
				
			}else {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return false;
	}
	/*
	public boolean vExistUpdate(vlasnici cont) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `vlasnici` WHERE `email` = ? `id` < > ?");
			ps.setString(1, cont.getEmail());
			ps.setInt(2, cont.getCid());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Vlasnik sa datim e-mailom postoji!");
				return false;
				
			}else {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return false;
	}*/
	
	public void appendTable() {
		
		vlasniciQuery vq = new vlasniciQuery();
		ArrayList<vlasnici> vtList = vq.ownerList();
		String[] colNames = {"id", "ime", "prezime", "e-mail", "telefon"};
		Object[][] rows = new Object[vtList.size()][5];
		
		for(int i=0; i<vtList.size(); i++) {
			rows[i][0] = vtList.get(i).getCid();
			rows[i][1] = vtList.get(i).getIme();
			rows[i][2] = vtList.get(i).getPrezime();
			rows[i][3] = vtList.get(i).getEmail();
			rows[i][4] = vtList.get(i).getTel();
			
		}
		owner_table mmd = new owner_table(rows, colNames);
		table.setModel(mmd);
		table.setRowHeight(40);
		
		table.setShowGrid(true);
		table.setGridColor(Color.YELLOW);
		table.setSelectionBackground(Color.lightGray);
		
		JTableHeader th = table.getTableHeader();
		th.setForeground(Color.BLUE);
		th.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
	}
	
	public void refreshTable() {
		table.setModel(new DefaultTableModel());
		appendTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameH = new JFrame();
		frameH.setBounds(100, 100, 1000, 700);
		frameH.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 78, 986, 585);
		panel_1.setBackground(Color.GRAY);
		frameH.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 80);
		panel.setBackground(Color.BLACK);
		
		loggedUser = new JLabel("ime zaposlenog");
		loggedUser.setBounds(10, 10, 360, 33);
		loggedUser.setForeground(Color.WHITE);
		loggedUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton logoutBtn = new JButton("Odjavite se");
		logoutBtn.setBounds(855, 19, 108, 44);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login lw = new Login();
				lw.frame.setVisible(true);
				lw.frame.pack();
				lw.frame.setLocationRelativeTo(null);
				lw.frame.setBounds(100, 100, 702, 474);
				lw.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				frameH.dispose();
			}
		});
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		loggedUser2 = new JLabel("prezime zaposlenog");
		loggedUser2.setBounds(10, 37, 360, 33);
		loggedUser2.setForeground(Color.WHITE);
		loggedUser2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		frameH.getContentPane().add(panel);
		
		loggedUser3 = new JLabel("Profil");
		loggedUser3.setBounds(744, 23, 137, 33);
		loggedUser3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loggedUser3.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("Profil:");
		lblNewLabel_1.setBounds(688, 20, 137, 39);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		panel.setLayout(null);
		panel.add(loggedUser2);
		panel.add(loggedUser);
		panel.add(logoutBtn);
		panel.add(loggedUser3);
		panel.add(lblNewLabel_1);
		frameH.getContentPane().add(panel_1);
		
		nameVField = new JTextField();
		nameVField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameVField.setBounds(187, 22, 200, 35);
		nameVField.setMinimumSize(new Dimension(16, 24));
		nameVField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ime vlasnika:");
		lblNewLabel.setBounds(10, 20, 159, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblPrezimeVlasnika = new JLabel("Prezime vlasnika:");
		lblPrezimeVlasnika.setBounds(10, 70, 159, 30);
		lblPrezimeVlasnika.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 120, 159, 30);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblGodineStarosti = new JLabel("Telefon:");
		lblGodineStarosti.setBounds(10, 170, 159, 30);
		lblGodineStarosti.setHorizontalAlignment(SwingConstants.CENTER);
		lblGodineStarosti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		snameVField = new JTextField();
		snameVField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		snameVField.setBounds(187, 72, 200, 35);
		snameVField.setMinimumSize(new Dimension(16, 24));
		snameVField.setColumns(10);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mailField.setBounds(187, 122, 200, 35);
		mailField.setMinimumSize(new Dimension(16, 24));
		mailField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneField.setBounds(187, 172, 200, 35);
		phoneField.setMinimumSize(new Dimension(16, 24));
		phoneField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(491, 10, 74, 244);
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JButton addBtn = new JButton("Dodati vlasnika");
		addBtn.setBounds(115, 221, 200, 43);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(verifyDataV()) {
				  String ime = nameVField.getText();
				  String prezime = snameVField.getText();
				  String email = mailField.getText();
				  String tel = phoneField.getText();
				  vlasnici v = new vlasnici(null, ime, prezime, email, tel);
				  if(vExist(v)) {
					  vlasniciQuery cq = new vlasniciQuery();
					  cq.insertV(v);
					  refreshTable();
				  }
				  
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton editVBtn = new JButton("Urediti vlasnika");
		editVBtn.setBounds(614, 41, 200, 50);
		editVBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyDataV()) {
					int id = Integer.valueOf(idField.getText());
					String ime = nameVField.getText();
					String prezime = snameVField.getText();
					String email = mailField.getText();
					String tel = phoneField.getText();
					vlasnici v = new vlasnici(id, ime, prezime, email, tel);
					//if(vExistUpdate(v)) {
						vlasniciQuery cq = new vlasniciQuery();
						cq.updateV(v);
						refreshTable();
					//}
				}
			}
		});
		editVBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton editPetBtn = new JButton("Urediti ljubimce");
		editPetBtn.setBounds(614, 110, 200, 50);
		editPetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Connection con = connection.getConnection();
					PreparedStatement ps;
					ResultSet rs;
					
					try {
						ps = con.prepareStatement("SELECT * FROM `vlasnici` WHERE `email` = ?");
						ps.setString(1, mailField.getText());
						rs = ps.executeQuery();
						
						if(rs.next()) {
							
							ljubimci.petID = rs.getInt("id");
							ljubimci pets = new ljubimci();
							
							pets.frameP.setVisible(true);
							pets.frameP.pack();
							pets.frameP.setLocationRelativeTo(null);
							pets.frameP.setBounds(100, 100, 1006, 779);
							pets.frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							
							pets.lblid.setText(rs.getString(1));
							pets.lblname2.setText(rs.getString(2) + " " + rs.getString(3));
							pets.lblname.setText(rs.getString(2));
							pets.lblsname.setText(rs.getString(3));
							pets.lblmail.setText(rs.getString(4));
							
						}else {
							JOptionPane.showMessageDialog(null, "Izabrati Vlasnika!");
						}
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
			}
		});
		editPetBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton deleteBtn = new JButton("Obrisati vlasnika");
		deleteBtn.setBounds(614, 180, 200, 50);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyDataVDel()) {
						int id = Integer.valueOf(idField.getText());
						String ime = nameVField.getText();
						String prezime = snameVField.getText();
						String email = mailField.getText();
						String tel = phoneField.getText();
						vlasnici v = new vlasnici(id, ime, prezime, email, tel);
						vlasniciQuery cq = new vlasniciQuery();
						cq.deleteV(v);
						refreshTable();
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		table = new JTable();
		table.setBounds(0, 283, 986, 292);
		
		// selektovanje tabele
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				idField.setText(table.getValueAt(rowIndex, 0).toString());
				nameVField.setText(table.getValueAt(rowIndex, 1).toString());
				snameVField.setText(table.getValueAt(rowIndex, 2).toString());
				mailField.setText(table.getValueAt(rowIndex, 3).toString());
				phoneField.setText(table.getValueAt(rowIndex, 4).toString());
				
			}
		});
		
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "ime", "prezime", "e-mail", "telefon"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(850, 55, 31, 27);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel);
		panel_1.add(nameVField);
		panel_1.add(lblPrezimeVlasnika);
		panel_1.add(snameVField);
		panel_1.add(lblEmail);
		panel_1.add(mailField);
		panel_1.add(lblGodineStarosti);
		panel_1.add(phoneField);
		panel_1.add(addBtn);
		panel_1.add(separator);
		panel_1.add(editVBtn);
		panel_1.add(editPetBtn);
		panel_1.add(deleteBtn);
		panel_1.add(lblNewLabel_2);
		panel_1.add(table);
		
		idField = new JLabel("");
		idField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idField.setBounds(890, 52, 55, 35);
		panel_1.add(idField);
		
	}
}
