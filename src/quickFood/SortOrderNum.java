package quickFood;

import java.util.*;

public class SortOrderNum implements Comparator <Customer>{
	public int compare (Customer c,Customer c1)
	{
		return c1.getOrderNum() - c.getOrderNum();
	}
}
