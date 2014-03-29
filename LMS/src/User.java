import java.sql.Date;

//Source file: D:\\lms\\User.java


public class User 
{
   private String userID;
   private String name;
   private String address;
   private String contactNo;
   private Date dateOfBirth;
   
   /**
    * @roseuid 531622850166
    */
   public User() 
   {
    
   }
   
   /**
    * @param getId
    * @roseuid 53161A9C00F7
    */
   public String getId(){
	   return this.userID;
   }
   
   public String getName(){
	   return this.name;
   }
   
   public String getAddress(){
	   return this.address;
   }
   
   /**
    * This function return the user object's contact number
    * @author KamikaZe
    * @return String
    */
   public String getContactNo(){
	   return this.contactNo;
   }
   
  
   
   public Date getDateOfBirth(){
	   return this.dateOfBirth;
   }
   
   
   /**
    * @param userId
    * @roseuid 53161AD7028C
    */
   public void setId(String userId){
	   this.userID = userId;
   }
   
   public void setName(String name){
	   this.name = name;
   }
   
   public void setAddress(String address){
	   this.address = address;
   }
   
   public void setContactNo(String contactNo){
	   this.contactNo = contactNo;
   }
   
   
   public void setDateOfBirth(Date dateOfBirth){
	   this.dateOfBirth = dateOfBirth;
   }
   
   /**
    * @param params
    * @roseuid 53161A9B00F7
    */
   public User getUser(String userId) 
   {
	   //Call db function to retrieve user
	   return this;
   }
   
   /**
    * @param user
    * @roseuid 53161ABD015C
    */
   public boolean setUser(User user) 
   {
	   //Call db function to store user
	   return true; //if successful
    
   }
   
   /**
    * @param params
    * @roseuid 53161AD6028C
    */
   public void privateListSearchBooks(int params) 
   {
    
   }
}
