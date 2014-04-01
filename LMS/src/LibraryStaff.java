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
				
				//Create and store transaction
				Transaction transaction = new Transaction();
				transaction.setCliId(borrowerId);
				transaction.setLibId(ls.getId());
				transaction.setTransDate(currentDate);
				transaction.setAction("ISSUE");
				
				db.dbTransactionStore(transaction);
				
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
						
						//check if book is past due date
						if(currentDate.after(book.getDueDate())){
							//System.out.println("Its past due date");
							long daysBetween = (currentDate.getTime() - book.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
							System.out.println(daysBetween);
							double fine = daysBetween * 0.5; //Fine is 50paise per per day
							System.out.println(String.valueOf(fine));
							client.setPendingFines(client.getPendingFines() + fine);
							db.dbClientUpdate(client, false);
							return true;
						}else{
							System.out.println("Its NOT past due date!!!");
							//no fines
						}
						
						
						book.setAvailable("YES");
						book.setIssueDate(null);
						book.setDueDate(null);
						
						//Set user details
						book.setBorrowerId(null);
						
						db.dbBookUpdate(book, false);
						
						//Create and store transaction
						Transaction transaction = new Transaction();
						transaction.setCliId(borrowerId);
						transaction.setLibId(ls.getId());
						transaction.setTransDate(currentDate);
						transaction.setAction("RETURN");
						
						db.dbTransactionStore(transaction);
						return true;
					}else{
						LogWriter.logWrite("Book is not available");
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return false; //if it fails

	}
	
}
