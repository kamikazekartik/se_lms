import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Source file: D:\\lms\\DBHandler.java


public class DBHandler
{
	private static Connection sqlConn = null; 
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
	 * Opens a new connection if old one is closed, else returns the existing connection.
	 * Basically make sure you have only 1 connection open at a time.
	 * @throws SQLException 
	 * @roseuid 53161C7F03C8
	 */
	public Connection getConnection() throws Exception 
	{
		try{
			if(sqlConn == null || sqlConn.isClosed()){
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
						"due_date, available, borrowe_id) values(";
				query += "'" + book.getAuthor() + "', ";
				query += "'" + book.getTitle() + "', ";
				query += "'" + book.getPrice() + "', ";
				if(book.getIssueDate() != null){
					query += " , issue_date='" + df.format(book.getIssueDate()) + "'";
					query += " , due_date='" + df.format(book.getDueDate()) + "'";
				}else{
					query += " , issue_date='" + book.getIssueDate() + "'";
					query += " , due_date='" + book.getDueDate() + "'";
				}
				query += "'" + book.getAvailable() + "', ";
				query += "'" + book.getBorrowerId() + "'";
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
				query += " , title='" + book.getTitle() + "'";
				query += " , price='" + book.getPrice() + "'";
				query += " , available='" + book.getAvailable() + "'";
				if(book.getIssueDate() != null){
					query += " , issue_date='" + df.format(book.getIssueDate()) + "'";
					query += " , due_date='" + df.format(book.getDueDate()) + "'";
				}else{
					query += " , issue_date=NULL";
					query += " , due_date=NULL";
				}

				if(book.getBorrowerId() != null){
					query += " , borrower_id='" + book.getBorrowerId() + "'";
				}else{
					query += " , borrower_id=NULL";
				}
				query += " where book_id=" + book.getId();

				LogWriter.logWrite("Query : " + query);
				Statement statement = sqlConn.createStatement();
				statement.executeQuery(query);
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
				if(client.getExpiryDate() != null){
					query += "'" + df.format(client.getExpiryDate()) + "'";
				}else{
					query += "NULL";
				}
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
				query += "name='" + client.getName();
				query += ", member_type='" + client.getMemberType();
				query += ", address='" + client.getAddress() + "'";
				query += ", contact_number='" + client.getContactNo();
				query += ", dob='" + df.format(client.getDateOfBirth());
				query += ", pending_fine='" + client.getPendingFines();
				query += ", expiry_date='" + df.format(client.getExpiryDate()) + "' ";
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
				client.setPendingFines(Double.valueOf((rs.getString(7))));
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

	/**
	 * @param params
	 * @roseuid 53191CD70223
	 */
	public ResultSet dbClientTableRetrieve(String userId) 
	{
		try {
			String query = "select * from member where member_id='";
			query += userId + "'";

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			return rs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	public Books dbGetBookFromResultSet(ResultSet rs){
		try{
			if(rs.next()){
				Books book = new Books();
				book.setBookId(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setTitle(rs.getString(3));
				book.setPrice(rs.getString(4));
				book.setIssueDate(rs.getDate(5));
				book.setDueDate(rs.getDate(6));
				book.setAvailable(rs.getString(7));
				book.setBorrowerId(rs.getString(8));

				return book;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return null; //if no such book exists
	}
	
	public Orders dbGetOrderFromResultSet(ResultSet rs){
		try{
			if(rs.next()){
				Orders order = new Orders();
				order.setBookId(rs.getString(1));
				order.setRequestedBy(rs.getString(2));
				order.setPlacedBy(rs.getString(3));
				order.setRequestDate(rs.getDate(4));
				order.setOrderDate(rs.getDate(5));
				order.setNumberOfCopies(rs.getInt(6));

				return order;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return null; //if no such book exists
	}

	/**
	 * Creates or updates an order based on the value of the boolean create
	 * @param order
	 * @param create
	 * @return
	 */
	public boolean dbOrderUpdate(Orders order, boolean create){
		if(create){
			try {
				String query = "insert into orders values(";
				query += order.getBookId() + ", ";
				query += "'" + order.getRequestedBy() + "', ";
				if(order.getPlacedBy() != null){
					query += "'" + order.getPlacedBy() + "', ";
				}else{
					query += "NULL, ";
				}
				query += "'" + df.format(order.getRequestDate()) + "', ";
				if(order.getOrderDate() != null){
					query += "'" + df.format(order.getOrderDate()) + "', ";
				}else{
					query += "NULL, ";
				}
				query += order.getNumberOfCopies();
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

			LogWriter.logWrite("Order Creation failed");
			return false;	//if it fails

		}else{
			try {
				String query = "Update orders set ";
				query += "requested_by='" + order.getRequestedBy() + "', ";
				query += "placed_by='" + order.getPlacedBy() + "'";
				query += ", date_of_request='" + df.format(order.getRequestDate()) + "'";
				if(order.getOrderDate() != null){
					query += ", date_of_order='" + df.format(order.getOrderDate()) + "'";
				}else{
					query += ", date_of_order=NULL";
				}
				query += ", number_of_copies=" + order.getNumberOfCopies();
				query += " where book_id=" + order.getBookId();

				Statement statement = sqlConn.createStatement();
				LogWriter.logWrite("Query : " + query);
				statement.executeQuery(query);
				
				return true;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LogWriter.logWrite("Order Update failed");
			return false;	//if it fails
		}
	}

	/**
	 * @param params
	 * @roseuid 53161CE6020B
	 */
	public boolean dbTransactionStore(Transaction transaction) 
	{
		DBHandler db = new DBHandler();
		try {
			db.getConnection();
			String query = "insert into transactions(librarian_id, client_id, trans_date" +
					", action) values(";
			query += "'" + transaction.getCliId() + "', ";
			query += "'" + transaction.getLibId() + "', ";
			query += "'" + df.format(transaction.getTransDate()) + "', ";
			query += "'" + transaction.getAction() + "'";
			query += ")";

			Statement statement = sqlConn.createStatement();
			LogWriter.logWrite("Query : " + query);
			statement.executeQuery(query);
			return true;			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false; //if it fails
	}

	/**
	 * @param params
	 * @roseuid 53161CF90259
	 */
	public ResultSet dbTransactionRetrieve(String transId, String librarianId, String clientId) 
	{
		int paramFlag = 0; //triggers when either parameter is set
		try {
			String query = "Select * from transactions";

			if(!transId.isEmpty()){
				query += " where trans_id= '" + transId + "'";
				paramFlag = 1;
			}

			if(!librarianId.isEmpty() && paramFlag==0){
				query += " where librarian_id= '" + librarianId + "'";
				paramFlag = 1;
			}else if(!clientId.isEmpty() && paramFlag == 1){
				query += " and librarian_id= '" + librarianId + "'";
			}

			if(!clientId.isEmpty() && paramFlag == 0){
				query += " where client_id= '" + clientId + "'";
			}else if(!clientId.isEmpty() && paramFlag == 1){
				query += " and client_id= '" + clientId + "'";
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
	
	public ResultSet dbOrdersRetrieve(String bookId, String requestedBy, String placedBy) 
	{
		int paramFlag = 0; //triggers when either parameter is set
		try {
			String query = "Select * from orders";

			if(!bookId.isEmpty()){
				query += " where book_id= '" + bookId + "'";
				paramFlag = 1;
			}

			if(!requestedBy.isEmpty() && paramFlag==0){
				query += " where requested_by= '" + requestedBy + "'";
				paramFlag = 1;
			}else if(!placedBy.isEmpty() && paramFlag == 1){
				query += " and requested_by= '" + requestedBy + "'";
			}

			if(!placedBy.isEmpty() && paramFlag == 0){
				query += " where placed_by= '" + placedBy + "'";
			}else if(!placedBy.isEmpty() && paramFlag == 1){
				query += " and placed_by= '" + placedBy + "'";
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
}

