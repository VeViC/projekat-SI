import java.sql.*;

public class connection {

	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/veterinarska_stanica", "root", "");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return con;
	}
	
}
