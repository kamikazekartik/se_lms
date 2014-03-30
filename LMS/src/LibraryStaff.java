import java.sql.Date;
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
			System.out.println(borrowerId);
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
	public boolean returnBook() 
	{
		//call db fucntion to retrieve all requests
		return true; //if successful

	}
	
}
