import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JTable;

public class ljubimci {

	public JFrame frameP;
	private JTextField petNameField;
	private JTextField petRaceField;
	private JTextField petAgeField;
	public JLabel lblname;
	public JLabel lblname2;
	public JLabel lblsname;
	public JLabel lblmail;
	public JLabel lblid;
	public JLabel idPet;
	public JDateChooser dateChooser;
	private JTable tablePet;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ljubimci window = new ljubimci();
					window.frameP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public static int petID;
	
	public ljubimci() {
		initialize();
		appendTable();
	}
	
	
	public boolean verifyDataP() {
		if(petNameField.getText().equals("") || petRaceField.getText().equals("") || petAgeField.getText().equals("") || dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Popunite sva polja!");
			return false;
		}
		
		return true;
	}
	
	public boolean verifyDataPDel() {
		if(petNameField.getText().equals("") || petRaceField.getText().equals("") || petAgeField.getText().equals("") || dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Izaberite ljubimca!");
			return false;
		}
		
		return true;
	}
	
	/*
	public boolean petExist(pet p) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `ljubimci` WHERE `ime` = ?, `vrsta` = ?, `imev` = ?, `prezimev` = ?");
			ps.setString(1, p.getName());
			ps.setString(2, p.getRace());
			ps.setString(3, p.getNameV());
			ps.setString(4, p.getSnameV());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Ljubimac postoji!");
				return false;
				
			}else {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return false;
	}
*/
	
	public void appendTable() {
		petQuery vq = new petQuery();
		ArrayList<pet> ptList = vq.petList(petID);
		String[] colNames = {"id", "ime", "rasa", "godine", "datum vakcinacije"};
		Object[][] rows = new Object[ptList.size()][5];
		
		for(int i=0; i<ptList.size(); i++) {
			rows[i][0] = ptList.get(i).getCid();
			rows[i][1] = ptList.get(i).getName();
			rows[i][2] = ptList.get(i).getRace();
			rows[i][3] = ptList.get(i).getAge();
			rows[i][4] = ptList.get(i).getDate();
			
			
		}
		petTable mmd = new petTable(rows, colNames);
		tablePet.setModel(mmd);
		tablePet.setRowHeight(40);
		
		tablePet.setShowGrid(true);
		tablePet.setGridColor(Color.YELLOW);
		tablePet.setSelectionBackground(Color.lightGray);
		
		
		JTableHeader th = tablePet.getTableHeader();
		th.setForeground(Color.BLUE);
		th.setFont(new Font("Tahoma", Font.BOLD, 16));
		
	}
	
	public void refreshTable() {
		tablePet.setModel(new DefaultTableModel());
		appendTable();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameP = new JFrame();
		frameP.setBounds(100, 100, 1006, 779);
		frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameP.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 992, 89);
		frameP.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblname2 = new JLabel("Ime");
		lblname2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblname2.setHorizontalAlignment(SwingConstants.LEFT);
		lblname2.setBounds(10, 10, 176, 31);
		panel.add(lblname2);
		
		lblsname = new JLabel("Prezime");
		lblsname.setForeground(Color.ORANGE);
		lblsname.setBackground(Color.ORANGE);
		lblsname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblsname.setBounds(528, 10, 176, 31);
		panel.add(lblsname);
		
		lblmail = new JLabel("email");
		lblmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblmail.setBounds(10, 51, 365, 31);
		panel.add(lblmail);
		
		lblid = new JLabel("id");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblid.setBounds(887, 10, 78, 43);
		panel.add(lblid);
		
		lblname = new JLabel("Ime ovde");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblname.setBackground(Color.ORANGE);
		lblname.setForeground(Color.ORANGE);
		lblname.setBounds(262, 23, 136, 37);
		panel.add(lblname);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 89, 992, 653);
		frameP.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 21, 190, 41);
		panel_1.add(lblNewLabel);
		
		JLabel lblRasa = new JLabel("Rasa:");
		lblRasa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRasa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRasa.setBounds(10, 77, 190, 41);
		panel_1.add(lblRasa);
		
		JLabel lblGodine = new JLabel("Godine:");
		lblGodine.setHorizontalAlignment(SwingConstants.CENTER);
		lblGodine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGodine.setBounds(10, 130, 190, 41);
		panel_1.add(lblGodine);
		
		JLabel lblPoslednjaVakcina = new JLabel("Poslednja vakcina:");
		lblPoslednjaVakcina.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoslednjaVakcina.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPoslednjaVakcina.setBounds(10, 185, 190, 41);
		panel_1.add(lblPoslednjaVakcina);
		
		petNameField = new JTextField();
		petNameField.setBounds(210, 21, 198, 41);
		panel_1.add(petNameField);
		petNameField.setColumns(10);
		
		petRaceField = new JTextField();
		petRaceField.setColumns(10);
		petRaceField.setBounds(210, 77, 198, 41);
		panel_1.add(petRaceField);
		
		petAgeField = new JTextField();
		petAgeField.setColumns(10);
		petAgeField.setBounds(210, 130, 198, 41);
		panel_1.add(petAgeField);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(210, 185, 198, 41);
		panel_1.add(dateChooser);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(495, 10, 17, 280);
		panel_1.add(separator);
		
		JButton btnNewButton = new JButton("Dodati ljubimca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyDataP()) {
					  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					  String name = petNameField.getText();
					  String race = petRaceField.getText();
					  int age = Integer.valueOf(petAgeField.getText());
					  String date = sdf.format(dateChooser.getDate());
					  String nameV = lblname.getText();
					  String snameV = lblsname.getText();
					  Job1.mailAddress = lblmail.getText();
					  Job1.petName = petNameField.getText();
					  Job1.race = petRaceField.getText();
					  pet p = new pet(null, name, race, nameV, snameV, age, date);
						  petQuery pq = new petQuery();
						  pq.insertPet(p, petID);
						  refreshTable();
						  emailApp.posaljiMejl();
					  
					}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(152, 249, 179, 46);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Urediti ljubimca");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyDataP()) {
					int id = Integer.valueOf(idPet.getText());
					String name = petNameField.getText();
					String race = petRaceField.getText();
					Integer age = Integer.valueOf(petAgeField.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(dateChooser.getDate());
					String nameV = lblname.getText();
					String snameV = lblsname.getText();
					pet p = new pet(id, name, race, nameV, snameV, age, date);
						petQuery cq = new petQuery();
						cq.updateP(p);
						refreshTable();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(635, 44, 198, 46);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Obrisati ljubimca");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyDataPDel()) {
					int id = Integer.valueOf(idPet.getText());
					String ime = petNameField.getText();
					String rasa = petRaceField.getText();
					int god = Integer.valueOf(petAgeField.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String datum = sdf.format(dateChooser.getDate());
					String nameV = lblname.getText();
					String snameV = lblsname.getText();
					pet p = new pet(id, ime, rasa, nameV, snameV, god, datum);
					petQuery cq = new petQuery();
					cq.deleteP(p);
					refreshTable();
			}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(635, 144, 198, 46);
		panel_1.add(btnNewButton_1_1);
		
		tablePet = new JTable();
		tablePet.setBounds(10, 332, 972, 311);
		tablePet.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tablePet.getSelectedRow();
				idPet.setText(tablePet.getValueAt(rowIndex, 0).toString());
				petNameField.setText(tablePet.getValueAt(rowIndex, 1).toString());
				petRaceField.setText(tablePet.getValueAt(rowIndex, 2).toString());
				petAgeField.setText(tablePet.getValueAt(rowIndex, 3).toString());
				
				try {
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tablePet.getValueAt(rowIndex, 4));
					dateChooser.setDate(date);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		tablePet.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablePet.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "ime", "rasa", "godine", "datum vakcinacije"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		panel_1.add(tablePet);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(843, 49, 59, 41);
		panel_1.add(lblNewLabel_1);
		
		idPet = new JLabel("");
		idPet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idPet.setBounds(884, 49, 59, 41);
		panel_1.add(idPet);
	}
}
