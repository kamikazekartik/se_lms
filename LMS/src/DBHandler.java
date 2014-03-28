import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//Source file: D:\\lms\\DBHandler.java


public class DBHandler
{
   private static Connection sqlConn; 
   
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

   //TODO: need to create this api properly
   /**
    * Needs lot of work
    * @param params
    * @roseuid 53161C8F038F
    */
   public boolean dbBookUpdate(Book book) 
   {
	   try {
			String query = "Select * from books";

			Statement statement = sqlConn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				System.out.println("Hey");
				int i=1;
				while(i <= rsmd.getColumnCount()){
					System.out.print(rs.getString(i) + "--");
					i++;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   return true;
    
   }
   
   /**
    * @param bookId, title, author
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
			ResultSetMetaData rsmd = rs.getMetaData();
			
			System.out.println(query);
			
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
   public boolean dbUserUpdate(String params) 
   {
	   return true; //if successful
    
   }
   
   /**
    * @param params
    * @roseuid 53161CD70223
    */
   public boolean dbUserRetrieve(String params) 
   {
	   return true; //if successful
    
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

