package Assignment10;

import java.util.Comparator;

public class NameSorter implements Comparator<Contact> 
	{
	    @Override
	    public int compare(Contact o1, Contact o2) {
	        return o2.getContactName().compareToIgnoreCase(o1.getContactName());
	    }
	   }

