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
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello, world");

		DBHandler db = new DBHandler();
		try {
			
			db.getConnection();
			ResultSet rs = db.dbBookRetrieve("", "", "");
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				System.out.println("Hey");
				int i=1;
				while(i <= rsmd.getColumnCount()){
					System.out.print(rs.getString(i) + "--");
					i++;
				}
			}
			
			/*
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
				
				/*String str = rs.getString(1);
				System.out.println(str);
			}*/
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
