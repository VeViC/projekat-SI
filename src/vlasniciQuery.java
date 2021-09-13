import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class vlasniciQuery {
	public void insertV(vlasnici cont) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("INSERT INTO `vlasnici`(`ime`, `prezime`, `email`, `tel`) VALUES (?, ?, ?, ?)");
			ps.setString(1, cont.getIme());
			ps.setString(2, cont.getPrezime());
			ps.setString(3, cont.getEmail());
			ps.setString(4, cont.getTel());
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Vlasnik dodat!");
			}else {
					JOptionPane.showMessageDialog(null, "Neuspešno dodavanje!");
			}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
		}
	}
	
	public void deleteV(vlasnici cont) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("DELETE FROM `vlasnici` WHERE `id` = ?");
			ps.setInt(1, cont.getCid());
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Vlasnik uspešno uklonjen!");
			}else {
				JOptionPane.showMessageDialog(null, "Izaberite vlasnika!");
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
	}
		
	}
	
	
	
	public void updateV(vlasnici cont) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE `vlasnici` SET `ime` = ?, `prezime` = ?, `email` = ?, `tel` = ? WHERE `id` = ?");
			ps.setString(1, cont.getIme());
			ps.setString(2, cont.getPrezime());
			ps.setString(3, cont.getEmail());
			ps.setString(4, cont.getTel());
			ps.setInt(5, cont.getCid());
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Vlasnik uspešno uredjen!");
			}else {
					JOptionPane.showMessageDialog(null, "Izaberite vlasnika!");
			}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<vlasnici> ownerList(){
		ArrayList<vlasnici> olist = new ArrayList<vlasnici>();
		
		Connection con = connection.getConnection();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT `id`, `ime`, `prezime`, `email`, `tel` FROM `vlasnici`");
			
			vlasnici vt;
			
			while(rs.next()) {
				vt = new vlasnici(rs.getInt("id"),
									rs.getString("ime"),
									rs.getString("prezime"),
									rs.getString("email"),
									rs.getString("tel"));
				olist.add(vt);
			}
		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return olist;
	}
	
}
