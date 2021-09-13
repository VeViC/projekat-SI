import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class petQuery {
	public void insertPet(pet p, int petID) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("INSERT INTO `ljubimci` (`ime`, `vrsta`, `imev`, `prezimev`, `god`, `datum`, `petID`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, p.getName());
			ps.setString(2, p.getRace());
			ps.setString(3, p.getNameV());
			ps.setString(4, p.getSnameV());
			ps.setInt(5, p.getAge());
			ps.setString(6, p.getDate());
			ps.setInt(7, petID);
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Ljubimac dodat!");
			}else {
					JOptionPane.showMessageDialog(null, "Neuspešno dodavanje!");
			}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
		}
	}
	
	public void deleteP(pet p) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("DELETE FROM `ljubimci` WHERE `id` = ?");
			ps.setInt(1, p.getCid());
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Ljubimac uspešno uklonjen!");
			}else {
				JOptionPane.showMessageDialog(null, "Izaberite ljubimca!");
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void updateP(pet p) {
		Connection con = connection.getConnection();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE `ljubimci` SET `ime`=?,`vrsta`=?,`god`=?,`datum`=? WHERE `id` = ?");
			ps.setString(1, p.getName());
			ps.setString(2, p.getRace());
			ps.setInt(3, p.getAge());
			ps.setString(4, p.getDate());
			ps.setInt(5, p.getCid());
			
			if(ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Vlasnik uspešno uredjen!");
			}else {
					JOptionPane.showMessageDialog(null, "Izaberite vlasnika!");
			}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<pet> petList(int petID){
		ArrayList<pet> plist = new ArrayList<pet>();
		
		Connection con = connection.getConnection();
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = con.prepareStatement("SELECT `id`, `ime`, `vrsta`, `imev`, `prezimev`, `god`, `datum` FROM `ljubimci` WHERE `petID`= "+petID);
			rs = st.executeQuery();
			
			pet vt;
			
			while(rs.next()) {
				vt = new pet(rs.getInt("id"),
								rs.getString("ime"),
								rs.getString("vrsta"),
								rs.getString("imev"),
								rs.getString("prezimev"),
								rs.getInt("god"),
								rs.getString("datum"));
				plist.add(vt);
			}
		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return plist;
	}

}
