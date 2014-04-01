import java.sql.Date;

//Source file: D:\\lms\\Orders.java


public class Orders 
{
	private String bookId;
	private String requestedBy;
	private String placedBy;
	private Date requestDate;
	private Date orderDate;
	private int numberOfCopies;

	/**
	 * @roseuid 531622850166
	 */
	public Orders() 
	{
		

	}

	/**
	 * @param getId
	 * @roseuid 53161A9C00F7
	 */
	public String getBookId(){
		return this.bookId;
	}
	
	public void setBookId(String bookId){
		this.bookId = bookId;
	}

	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the placedBy
	 */
	public String getPlacedBy() {
		return placedBy;
	}

	/**
	 * @param placedBy the placedBy to set
	 */
	public void setPlacedBy(String placedBy) {
		this.placedBy = placedBy;
	}

	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the numberOfCopies
	 */
	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	/**
	 * @param numberOfCopies the numberOfCopies to set
	 */
	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	/**
	 * @param params
	 * @roseuid 53161AD6028C
	 */
	public void privateListSearchOrders(int params) 
	{

	}


}
