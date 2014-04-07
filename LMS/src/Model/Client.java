package Model;
import java.sql.Date;

//Source file: D:\\lms\\Client.java


public class Client extends User 
{

	private double pendingFines;
	private String memberType;
	private Date expiryDate;

	/**
	 * @roseuid 5316228500F3
	 */
	public Client() 
	{

	}

	public double getPendingFines(){
		return this.pendingFines;
	}

	public String getMemberType(){
		return this.memberType;
	}

	public Date getExpiryDate(){
		return this.expiryDate;
	}

	public void setPendingFines(double pendingFines){
		this.pendingFines = pendingFines;
	}

	public void setMemberType(String memberType){
		this.memberType = memberType;
	}

	public void setExpiryDate(Date expiryDate){
		this.expiryDate = expiryDate;
	}

}
