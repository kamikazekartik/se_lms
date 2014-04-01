//Source file: D:\\lms\\LoginHandler.java


public class LoginHandler 
{
   private static String userId = null;
   
   /**
    * @roseuid 531622640234
    */
   public LoginHandler() 
   {
    
   }

   /**
    * @param params
    * @roseuid 53161A260158
    */
   public boolean login(String username, String password) 
   {
	   DBHandler db = new DBHandler();
	   try {
		db.getConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   LibraryStaff ls = db.dbUserRetrieve("", username, password);
	   if(ls == null){
		   return false; //could not log in user
	   }else{
		   this.userId = ls.getId();
		   return true;
	   }
	   
   }
   
   public boolean logout(){
	   if(this.userId == null){
		   return false; //no user currently logged in
	   }else{
		   this.userId = null;
		   return true;
	   }
   }
   
   /**
    * 
    * @param params
    * @roseuid 53161A31030B
    */
   public String getCurrentUser(){
	   return this.userId;
   }


}
