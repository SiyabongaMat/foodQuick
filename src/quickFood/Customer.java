package quickFood;

import java.util.Comparator;

public class Customer implements Comparable<Customer> {				//implements the comparable interface
	//initialize values for the customer class
	int orderNum;
	String fullname;
	String number;
	String address;
	String city;
	String email;
	
	public Customer() {}
	
	//default constructor of the customer object
	public Customer (String fullname, String number, String address, String city, String email)
	{
		this.fullname = fullname;
		this.number = number;
		this.address = address;
		this.city = city;
		this.email = email;
	}
	
	public int compareTo (Customer c)			//compare to method
	{
		return this.fullname.compareToIgnoreCase(c.fullname);		//compares the fullname values and returns an integer if found (or not)
	}
	
	//code below sets the order number for the customer
	void setOrderNum (int orderNum)
	{
		this.orderNum = orderNum;
	}
	
	//gets the customers address
	String getAddress ()
	{
		return address;
	}
	
	String getCity ()
	{
		return city;
	}
	
	String getName ()
	{
		return fullname;
	}
	
	int getOrderNum ()
	{
		return orderNum;
	}
	
	public String toString ()			//tostring to get all values of the variables of the customer class
	{
		String fullOrder = "Order number "+orderNum+"\nCustomer: "+fullname+"\nEmail: "+email+"\nPhone number: "+number+"\nLocation: "+city+"\n";
		return fullOrder;
	}
}
