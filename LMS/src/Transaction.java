import java.sql.Date;

//Source file: D:\\lms\\Transaction.java


public class Transaction 
{
   private String transId;
   private String libId;
   private String cliId;
   private Date transDate;
   private String action; //book return or issue
   
   /**
    * @roseuid 53162285020E
    */
   public Transaction() 
   {
    
   }
   
   public String getTransId(){
	   return this.transId;
   }
   
   public String getLibId(){
	   return this.libId;
   }
   
   public String getCliId(){
	   return this.cliId;
   }
   
   public Date getTransDate(){
	   return this.transDate;
   }
   
   public void setTransId(String transId){
	   this.transId = transId;
   }
   
   public void setLibId(String libId){
	   this.libId = libId;
   }
   
   public void setCliId(String cliId){
	   this.cliId = cliId;
   }
   
   
   public void setTransDate(Date transDate){
	   this.transDate = transDate;
   }
   
   /**
 * @return the action
 */
public String getAction() {
	return action;
}

/**
 * @param action the action to set
 */
public void setAction(String action) {
	this.action = action;
}

/**
    * @param transaction
    * @roseuid 53161D7B011E
    */
   public boolean createTransaction(String transaction) 
   {
	   //call db function to store transaction
	   return true;
    
   }
   
   /**
    * @param params
    * @roseuid 53161D8B023D
    */
   public Transaction getTransaction(String params) 
   {
	   //db function to get transaction
	   return this;
   }
}
