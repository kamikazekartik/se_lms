package Model;
import java.sql.Date;

//Source file: D:\\lms\\Books.java


public class Books 
{
   private String bookId;
   private String title;
   private String author;
   private String price;
   private Date issueDate;
   private Date dueDate;
   private String available;
   private String borrowerId;
   
   /**
    * @roseuid 531622850166
    */
   public Books() 
   {
    
   }
   
   /**
    * @param getId
    * @roseuid 53161A9C00F7
    */
   public String getId(){
	   return this.bookId;
   }
   
   public String getTitle(){
	   return this.title;
   }
   
   public String getAuthor(){
	   return this.author;
   }
   
   public Date getIssueDate(){
	   return this.issueDate;
   }
   
   public Date getDueDate(){
	   return this.dueDate;
   }
   
   public String getPrice(){
	   return this.price;
   }
   
   public String getAvailable(){
	   return this.available;
   }
   
   public String getBorrowerId(){
	   return this.borrowerId;
   }
   
   
   public void setBookId(String bookId){
	   this.bookId = bookId;
   }
   
   public void setTitle(String title){
	   this.title = title;
   }
   
   public void setPrice(String price){
	   this.price = price;
   }
   
   public void setAuthor(String author){
	   this.author = author;
   }
   
   public void setAvailable(String available){
	   this.available = available;
   }
   
   
   public void setDueDate(Date dueDate){
	   this.dueDate = dueDate;
   }
   
   public void setIssueDate(Date issueDate){
	   this.issueDate = issueDate;
   }
   
   public void setBorrowerId(String borrowerId){
	   this.borrowerId = borrowerId;
   }
   
   
   /**
    * @param params
    * @roseuid 53161A9B00F7
    */
   public Books getBook(String BookId) 
   {
	   //Call db function to retrieve Book
	   return this;
   }
   
   /**
    * @param Book
    * @roseuid 53161ABD015C
    */
   public boolean setBook(Books Book) 
   {
	   //Call db function to store Book
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
