import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Source file: D:\\lms\\DBHandler.java


public class DBHandler
{
   private int privateObjJdbcConnectivity; //fix this
   
   /**
    * @roseuid 531622850322
    */
   public DBHandler() 
   {
    
   }
   
   /**
    * @param params
    * @throws ClassNotFoundException 
    * @roseuid 53161C720295
    */
   public Connection openConnection(String params) throws ClassNotFoundException 
   {
       String host = "jdbc:oracle:thin:@localhost:1521:XE";
       String userName = "scott";
       String password = "tiger";
       try{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection(host,userName,password);
 	    return con;
       }
       catch (SQLException err){
           System.out.println(err.getMessage());
       }
       
       return null; //if it fails

    
   }
   
   /**
    * @param params
    * @roseuid 53161C7F03C8
    */
   public boolean getConnection(String params) //should probably return connection
   {
	   return true; //if successful
    
   }
   
   /**
    * @param params
    * @roseuid 53161C8F038F
    */
   public boolean dbBookUpdate(String params) 
   {
	   return true; //if successful
    
   }
   
   /**
    * @param params
    * @roseuid 53161CA900E6
    */
   public boolean dbBookRetrieve(String params) 
   {
	   return true; //if successful
    
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

