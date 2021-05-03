package quickFood;

import java.util.*;

public class CustomerList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List <Customer> cust = new ArrayList <Customer>();
		cust.add(new Customer("vuy0", "083", "maddison sqyare", "cape town", "vm@gmailc.om"));
		cust.add(new Customer("Thabiso", "081", "main road", "durban", "tha@gmailc.om"));
		cust.add(new Customer("Siya", "089", "4th ave", "durban", "msa@gmailc.om"));
		cust.get(0).setOrderNum(112);
		cust.get(1).setOrderNum(234);
		cust.get(2).setOrderNum(11);
		Collections.sort(cust, new SortOrderNum());
		for (Customer c: cust)
		{
			System.out.println(c);
		}
	}

}
