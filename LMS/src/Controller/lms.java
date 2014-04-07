package Controller;
import java.sql.SQLException;

import View.LMSMainMenu;

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
		
		//Launch main menu
		DBHandler db = new DBHandler();
		db.getConnection();
		new LMSMainMenu().setVisible(true);
		
	}

}