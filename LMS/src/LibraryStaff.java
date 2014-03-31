import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Source file: D:\\lms\\LibraryStaff.java


public class LibraryStaff extends User 
{
	private int headLibrarianFlag;	//if it is 1 he is headlibrarian
	private String username;
	private String password;

	/**
	 * @roseuid 53162285005D
	 */
	public LibraryStaff() 
	{
		super();

	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public int getHeadLibrarianFlag(){
		return this.headLibrarianFlag;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setHeadLibrarianFlag(int headLibrarianFlag){
		this.headLibrarianFlag = headLibrarianFlag;
	}

	/**
	 * @param params
	 * @roseuid 53161D8E01DE
	 */
	public boolean issueBook(LibraryStaff ls, Books book, String borrowerId) 
	{
		//call some fake function that makes a note in the database
		try{
			DBHandler db = new DBHandler();
			db.getConnection();
			Client client = db.dbClientRetrieve(borrowerId);
			if(client == null){
				return false; //no such client exists
			}
			
			if(book.getAvailable().equals("YES")){ //if book is available
				java.util.Date utilDate = new java.util.Date();
				Date currentDate = new Date(utilDate.getTime());
				book.setIssueDate(currentDate);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, +1);
				utilDate = cal.getTime();
				Date oneMonthLater = new Date(utilDate.getTime());
				book.setDueDate(oneMonthLater);
				
				book.setAvailable("NO");
				
				//Set user details
				book.setBorrowerId(borrowerId);
				
				db.dbBookUpdate(book, false);
				return true;
			}else{
				LogWriter.logWrite("Book is not available");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false; //if it fails

	}

	/**
	 * @roseuid 53161B9C034E
	 */
	public boolean returnBook(LibraryStaff ls, Books book, String borrowerId) 
	{
		//call some fake function that makes a note in the database
				try{
					DBHandler db = new DBHandler();
					db.getConnection();
					Client client = db.dbClientRetrieve(borrowerId);
					if(client == null){
						return false; //no such client exists
					}
					
					if(book.getAvailable().equals("NO")){ //if book has indeed been borrowed
						java.util.Date utilDate = new java.util.Date();
						Date currentDate = new Date(utilDate.getTime());
						book.setIssueDate(null);
						
						//check if book is past due date
						if(currentDate.after(book.getDueDate())){
							System.out.println("Its past due date");
						}else{
							System.out.println("Its NOT past due date!!!");
						}
						
						DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
						System.out.println(df.format(currentDate));
						
						book.setAvailable("NO");
						
						//Set user details
						book.setBorrowerId(borrowerId);
						
						//db.dbBookUpdate(book, false);
						return true;
					}else{
						LogWriter.logWrite("Book is not available");
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return false; //if it failsl

	}
	
}
