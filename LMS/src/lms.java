import java.sql.*;

/**
 * 
 */

/**
 * @author KamikaZe
 *
 */
public class lms {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("hello, world");
		/*User u = new User();
		u.getContactNo();*/
		DBHandler d = new DBHandler();
		try {
			Connection con = d.openConnection(null);
			Statement stmt = null;
			String query = "Select * from books";

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				System.out.println("Hey");
				int i=1;
				while(i <= rsmd.getColumnCount()){
					System.out.print(rs.getString(i) + "--");
					i++;
				}
				
/*				String str = rs.getString(1);
				System.out.println(str);*/
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
