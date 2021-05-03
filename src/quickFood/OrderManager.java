package quickFood;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class OrderManager {
	
	public static Restaurant orderInfo () throws Exception
	{
		Restaurant mcd = new Restaurant();					//creates restaurant object
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
		
		//START
		//code below inputs user input
		System.out.print("Enter your full name: ");
		String name = input.readLine();
		
		System.out.print("Enter your phone number: ");
		String number = input.readLine();
		
		System.out.print("Enter your address: ");
		String address = input.readLine();
		
		System.out.print("Enter your location (city): ");
		String city = input.readLine();
		
		System.out.print("Enter your email address: ");
		String email = input.readLine();
		
		System.out.print("Enter the restaurant you ordered from: ");
		String rName = input.readLine();
		
		System.out.print("Enter location (city) of restaurant: ");
		String rLocation = input.readLine();
		
		System.out.print("Enter restaurant phone number: ");
		String rContact = input.readLine();
		
		System.out.print("Enter the meal you ordered: ");
		String meals = input.readLine();
			
		System.out.print("Enter any special instructions for preparation: ");
		String special = input.readLine();
		
		System.out.print("Enter the number of "+meals+"\'s ordered: ");
		int numOfOrders = Integer.parseInt(input.readLine());
		
		System.out.print("Enter price of meal: ");
		double mealPrice = Double.parseDouble(input.readLine());
		
		double totalMealPrice = numOfOrders * mealPrice;
		
		//STOP
		//code above prompts user for input
		
		Customer nc = new Customer (name, number, address, city, email);	//creates the customer object
		nc.setOrderNum(2124);												//sets the order number
		
		//sets the restaurant object variables
		mcd.setCustomerName(nc);
		mcd.setRName(rName);
		mcd.setRLocation(rLocation);
		mcd.setRContactNum(rContact);
		mcd.setMealList(meals);
		mcd.setNumberOfOrders(numOfOrders);
		mcd.setMealPrice(mealPrice);
		mcd.setSpecialPreps(special);
		
		int total = 0;
		total += totalMealPrice;
		mcd.setTotalPrice(total);
		//calculates the total price of the meal ordered
		
		return mcd;				//returns the restaurant object
		
	}
	
	public static void main (String []args)
	{
		try
		{
			File x = new File ("../drivers.txt");
			Scanner sc = new Scanner (x);
			String line = "";
			Restaurant rst = orderInfo();
			ArrayList <String> drivers = new ArrayList <String>();
			customerInfoFile(rst);
			sortByLocation(rst);
			
			while (sc.hasNext())					//conditions checks if the textfile has a line
			{
				line = sc.nextLine();
				String []parts = line.split(", ");
				String driverLocation = parts[1];
				if (driverLocation.equalsIgnoreCase(rst.getCustomer().getCity()) && rst.getCustomer().getCity().equalsIgnoreCase(rst.getRLocation()))
				//checks if the location of driver matches locations entered by user and of restaurant
				{
					drivers.add(line);								//adds the true statement 
				}
			}
			
			int numOfDeliveries = -3;
			String selectedDriver = "";
			for (String driver:drivers)				//turns values from arraylist into a string 'driver'
			{
				String []d = driver.split(", ");
				int loadNum = Integer.parseInt(d[2]);
				int k = d.length;					//initialises k into the length of elements of d in line 38
				if (numOfDeliveries < 0)			//condition checks if the driver has no delivery loads
				{
					if (k < 2)						//checks if the length of d is below 2
					{								//does this because some drivers has no loads whatsoever
						numOfDeliveries = 0;		//if 'if' statement is true the number of deliveries becomes 0
						selectedDriver = driver;	//selects this driver because they have no deliveries
						break;
					}
					else
					{
						numOfDeliveries = Integer.parseInt(d[2]);	//if 'if' statement is false it parses the loads number into an integet
						selectedDriver = driver;					//selects the driver
					}
				}
				else								//if number of deliveries is not below 0
				{
					if (k < 2)						//if the length of array is below 2 AGAIN
					{
						numOfDeliveries = 0;		//the number of deliveries is 0
						selectedDriver = driver;	//this driver is selectedd
						break;
					}
					else if (Integer.parseInt(d[2]) < numOfDeliveries)	//however if the driver with load below the numOfDeliveris
					{
						numOfDeliveries = Integer.parseInt(d[2]);
						selectedDriver = d[0];							//the driver is selected
						//loadNum += 1;
					}
				}
			}
			invoice (rst, selectedDriver);								//calls method to create invoice text file
			sc.close();
		}
		catch (Exception e) {System.err.println(e.getMessage());}
	}
	
	public static void invoice (Restaurant rst, String selectedDriver)
	{
		try
		{
			Formatter nf = new Formatter ("../invoice.txt");
			nf.format("%s\n", rst);
			nf.format("%s", selectedDriver+" is nearest to you, and will be delivering your order\n");
			nf.format("%s", rst.getCustomer().getAddress(),"\n");
			nf.format("%s", rst.getRestaurantNum());
			nf.close();
		}
		catch (Exception e) {System.err.println(e.getMessage());}
	}
	
	public static void customerInfoFile(Restaurant rst)
	{
		try
		{
			Formatter nf = new Formatter ("../customerSort.txt");			//creates new file
			List <Customer> cst = new ArrayList <Customer>();			//creates arraylist to store customer objects
			
			cst.add(rst.getCustomer());				//adds customer object
			Collections.sort(cst);					//sorts lexicographically the objects in arraylist
			
			for (Customer c:cst)					//for loop to loop through the arralist
			{
				nf.format("%s",c.getOrderNum() + "\t" + c.getName());	//writes to the textfile after sorting arraylist
			}
			nf.close();
		}
		catch (Exception e) {System.err.println(e.getMessage());}
	}
	
	public static void sortByLocation (Restaurant rst)
	{
		try
		{
			Formatter nf = new Formatter ("../locationSort.txt");			//creates new file
			List <Customer> cus = new ArrayList <>();			//creates new list
			cus.add(rst.getCustomer());							//adds object to list
			
			Map<String, List<Customer>> cusMap = new HashMap<String, List<Customer>>();		//creates hashmap and accepts a string a customer list
			
			for (Customer c: cus)
			{
				if (!cusMap.containsKey(c.getCity()))				//checks if map has the customers city
				{
					cusMap.put(c.getCity(), new ArrayList<>());		//adds the customer city with the list values
				}
				cusMap.get(c.getCity()).add(c);
			}
			
			cusMap = cus.stream().collect(Collectors.groupingBy(Customer::getCity));	//groups by the city of customer
			System.out.println(cusMap.toString());
			nf.format("%s", cusMap.toString());
			nf.close();
		}
		catch (Exception e) {System.err.println(e.getMessage());}
	}
}
