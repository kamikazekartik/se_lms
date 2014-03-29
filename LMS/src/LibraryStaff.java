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
	public boolean issueBook(int params) 
	{
		//call some fake function that makes a note in the database
		return true; //if successful

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
