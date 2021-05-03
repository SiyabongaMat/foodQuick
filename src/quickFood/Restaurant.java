package quickFood;

public class Restaurant {
	//initialization of the variables of the restaurant class
	String rName;
	String rLocation;
	String rContactNum;
	int numberOfOrders;
	String mealList;
	double mealPrice;
	String specialPreps;
	double totalPrice;
	Customer cus;					//calls the customer class and its variables
									//we are linking the customer to the restaurant they have ordered from
	
	void setCustomerName (Customer cus)			//sets the customer class variables after it was called
	{
		this.cus = cus;
	}
	
	Customer getCustomer ()
	{
		return cus;								//calls the tostring from the customer class
	}
	
	//the remaining of the code below sets the variables of the restaurant class
	String getRLocation ()
	{
		return rLocation;
	}
	
	void setRName (String rName)
	{
		this.rName = rName;
	}
	void setRLocation (String rLocation)
	{
		this.rLocation = rLocation;
	}
	void setRContactNum (String rContactNum)
	{
		this.rContactNum = rContactNum;
	}
	void setNumberOfOrders (int numOfOrders)
	{
		numberOfOrders = numOfOrders;
	}
	void setMealList (String mealList)
	{
		this.mealList = mealList;
	}
	void setMealPrice (double mealPrice)
	{
		this.mealPrice = mealPrice;
	}
	void setSpecialPreps (String specialPreps)
	{
		this.specialPreps = specialPreps;
	}
	void setTotalPrice (double totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	
	String getRestaurantNum ()			//code gets the restaurants phone number
	{
		return "\nIf you need to contact the restaurant, their number is " + rContactNum;
	}
	
	public String toString ()			//tostring calls the variables initialized in this class
	{
		String res = cus + "You have ordered the following from "+rName+" in "+rLocation+":\n"+numberOfOrders+" x "+mealList+"\t("+mealPrice+")"+"\nSpecial instructions: "+specialPreps+"\nTotal: "+totalPrice;
		return res;
	}
}
