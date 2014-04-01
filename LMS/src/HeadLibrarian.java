import java.sql.Date;
import java.sql.ResultSet;

//Source file: D:\\lms\\HeadLibrarian.java


public class HeadLibrarian extends LibraryStaff 
{
	private int headLibrarianFlag;

	/**
	 * @roseuid 53162285005D
	 */
	public HeadLibrarian() 
	{
		super();

	}
	

	/**
	 * @param params
	 * @roseuid 53161B8E01DE
	 */
	public boolean placeOrder(String bookId, String userId) 
	{
		System.out.println("Place an order");
		try {
			DBHandler db = new DBHandler();
			db.getConnection();

			java.util.Date utilDate = new java.util.Date();
			Date currentDate = new Date(utilDate.getTime());

			if(bookId.isEmpty() || userId.isEmpty()){
				LogWriter.logWrite("Order place failed due to insuffecient parameters");
				return false;
			}
			ResultSet rs = db.dbOrdersRetrieve(bookId, "", "");
			
			Orders order = db.dbGetOrderFromResultSet(rs);
			if(order == null){
				LibraryStaff ls = db.dbUserRetrieve(userId, "", "");
				ls.requestOrder(bookId, userId);
			}
			order.setOrderDate(currentDate);

			//Make sure only single order request per book
				db.dbOrderUpdate(order, false);
				return true; //order was successful

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LogWriter.logWrite("Order request failed");
		return false; //order request failed

	}

	/**
	 * @roseuid 53161B9C034E
	 */
	public boolean getOrderRequests() 
	{
		//call db fucntion to retrieve all requests
		return true; //if successful

	}
}
