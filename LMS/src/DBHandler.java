import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Source file: D:\\lms\\DBHandler.java


public class DBHandler
{
	private static Connection sqlConn; 
	private DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	/**
	 * @roseuid 531622850322
	 */
	public DBHandler() 
	{

	}

	/**
	 * @throws ClassNotFoundException 
	 * @roseuid 53161C720295
	 */
	public boolean openConnection() throws ClassNotFoundException 
	{
		String host = "jdbc:oracle:thin:@localhost:1521:XE";
		String userName = "scott";
		String password = "tiger";
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			sqlConn = DriverManager.getConnection(host,userName,password);
			return true;
		}
		catch (SQLException err){
			System.out.println(err.getMessage());
		}

		LogWriter.logWrite("Unable to create sql connection");
		return false; //if it fails


	}

	/**
	 * @throws SQLException 
	 * @roseuid 53161C7F03C8
	 */
	public Connection getConnection() throws Exception //should probably return connection
	{
		try{
			if(sqlConn == null){
				openConnection();
				return sqlConn;
			}else{
				return sqlConn;
			}
		}catch(Exception e){
			throw e;
		}

	}

	public void closeConnection()
	{
		try{
			sqlConn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * If create flag = true, then new book will be created.
	 * @roseuid 53161C8F038F
	 */
	public boolean dbBookUpdate(Books book, boolean create) 
	{
		if(create){
			try {
				String query = "insert into books(author, title, price, issue_date, " +
						"due_date) values(";
				query += "'" + book.getAuthor() + "', ";
				query += "'" + book.getTitle() + "', ";
				query += "'" + book.getPrice() + "', ";
				query += "'" + df.format(book.getIssueDate()) + "', ";
				query += "'" + df.format(book.getDueDate()) + "'";
				query += ")";

				Statement statement = sqlConn.createStatement();
				statement.executeQuery(query);
				LogWriter.logWrite("Query : " + query);
				return true;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LogWriter.logWrite("Book Update failed");
			return false;	//if it fails

		}else{
			try {
				String query = "Update books set ";
				query += "author='" + book.getAuthor() + "'" ;
				query += " and title='" + book.getTitle() + "'";
				query += " and price='" + book.getPrice() + "'";
				query += " and issue_date='" + df.format(book.getIssueDate()) + "'";
				query += " and due_date='" + df.format(book.getDueDate()) + "'";
				query += " where book_id=" + book.getId();

				Statement statement = sqlConn.createStatement();
				statement.executeQuery(query);
				LogWriter.logWrite("Query : " + query);
				return true;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LogWriter.logWrite("Book Update failed");
			return false;	//if it fails
		}

	}

	/**
	 * @param
	 * @roseuid 53161CA900E6
	 */
	public ResultSet dbBookRetrieve(String bookId, String title, String author) 
	{
		int paramFlag = 0; //triggers when either parameter is set
		try {
			String query = "Select * from books";

			if(!bookId.isEmpty()){
				query += " where book_id= '" + bookId + "'";
				paramFlag = 1;
			}

			if(!title.isEmpty() && paramFlag==0){
				query += " where title= '" + title + "'";
				paramFlag = 1;
			}else if(!title.isEmpty() && paramFlag == 1){
				query += " and title= '" + title + "'";
			}

			if(!author.isEmpty() && paramFlag == 0){
				query += " where author= '" + author + "'";
			}else if(!author.isEmpty() && paramFlag == 1){
				query += " and author= '" + author + "'";
			}

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			LogWriter.logWrite("Query : " + query);

			return rs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null; //if it fails

	}

	/**
	 * @param params
	 * @roseuid 53161CBD00C6
	 */
	public boolean dbClientUpdate(Client client, boolean create) 
	{
		if(create){
			try {
				String query = "insert into member values(";
				query += "'" + client.getId() + "', ";
				query += "'" + client.getName() + "', ";
				query += "'" + client.getMemberType() + "', ";
				query += "'" + df.format(client.getDateOfBirth()) + "', ";
				query += "'" + client.getAddress() + "', ";
				query += "'" + client.getContactNo() + "', ";
				query += "'" + client.getPendingFines() + "', ";
				query += "'" + df.format(client.getExpiryDate()) + "'";
				query += ")";
				
				System.out.println(query);

				Statement statement = sqlConn.createStatement();
				statement.executeQuery(query);
				LogWriter.logWrite("Query : " + query);
				return true;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LogWriter.logWrite("Client Creation failed");
			return false;	//if it fails

		}else{
			try {
				String query = "Update member set ";
				query += "name='" + client.getName() + "', ";
				query += " and member_type='" + client.getMemberType() + "', ";
				query += " and address='" + client.getAddress() + "'";
				query += " and contact_number='" + client.getContactNo() + "', ";
				query += " and dob='" + df.format(client.getDateOfBirth()) + "'";
				query += " and pending_fine=" + client.getPendingFines() + "', ";
				query += " and expiry_date=" + df.format(client.getExpiryDate()) + "' ";
				query += " where member_id=" + client.getId();

				Statement statement = sqlConn.createStatement();
				statement.executeQuery(query);
				LogWriter.logWrite("Query : " + query);
				return true;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LogWriter.logWrite("Client Update failed");
			return false;	//if it fails
		}

	}

	/**
	 * @param params
	 * @roseuid 53161CD70223
	 */
	public Client dbClientRetrieve(String userId) 
	{
		try {
			String query = "select * from member where member_id='";
			query += userId + "'";

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if(rs.next()){	//Make sure there are elements in the result set

				Client client = new Client();
				client.setId(rs.getString(1));
				client.setName(rs.getString(2));
				client.setMemberType(rs.getString(3));
				client.setDateOfBirth(rs.getDate(4));
				client.setAddress(rs.getString(5));
				client.setContactNo(rs.getString(6));
				client.setPendingFines(Integer.parseInt(rs.getString(7)));
				client.setExpiryDate(rs.getDate(8));

				return client;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		LogWriter.logWrite("Invalid client details were passed");
		return null; //if it fails

	}

	public LibraryStaff dbUserRetrieve(String userId, String username, String password){
		try {
			String query = "select * from library_staff";

			if(!userId.isEmpty()){
				query += " where librarian_id='" + userId + "'";
			}else{
				query += " where username='" + username + "'" + " and password='" +
						password + "'";
			}

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			LogWriter.logWrite("Query : " + query);

			if(rs.next()){
				LibraryStaff staff = new LibraryStaff();
				staff.setId(rs.getString(1));
				staff.setUsername(rs.getString(2));
				staff.setPassword(rs.getString(3));
				staff.setName(rs.getString(4));
				staff.setAddress(rs.getString(5));
				staff.setDateOfBirth(rs.getDate(6));
				staff.setHeadLibrarianFlag(rs.getInt(7));


				return staff;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LogWriter.logWrite("Invalid login details were passed");
		return null; //No such user exists
	}

	public HeadLibrarian dbHeadLibrarianRetrieve(String userId, String username, String password){
		try {
			String query = "select * from library_staff";

			if(!userId.isEmpty()){
				query += " where librarian_id='" + userId + "'";
			}else{
				query += " where username='" + username + "'" + " and password='" +
						password + "'";
			}

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			LogWriter.logWrite("Query : " + query);

			if(rs.next()){
				HeadLibrarian hstaff = new HeadLibrarian();
				hstaff.setId(rs.getString(1));
				hstaff.setUsername(rs.getString(2));
				hstaff.setPassword(rs.getString(3));
				hstaff.setName(rs.getString(4));
				hstaff.setAddress(rs.getString(5));
				hstaff.setDateOfBirth(rs.getDate(6));
				hstaff.setHeadLibrarianFlag(rs.getInt(7));


				return hstaff;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LogWriter.logWrite("Invalid login details were passed");
		return null; //No such user exists
	}

	/**
	 * @param params
	 * @roseuid 53161CE6020B
	 */
	public boolean dbTransactionStore(String params) 
	{
		return true; //if successful
	}

	/**
	 * @param params
	 * @roseuid 53161CF90259
	 */
	public boolean dbTransactionRetrieve(String params) 
	{
		return true; //if successful

	}
}

