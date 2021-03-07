package Assignment10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


public class ContactService {
	
	//function to add contact list
	public void addContact(Contact c,ArrayList<Contact> contactList) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the contactId");
		int id=sc.nextInt();
		c.setContactID(id);
		sc.nextLine();
		System.out.println("Enter Contact Name");
		String contactName=sc.nextLine();
		c.setContactName(contactName);
		System.out.println("Enter Contact Email address");
		String email=sc.nextLine();
		c.setEmail(email);
		System.out.println("How many contact numbers do you want to add?");
		int n=sc.nextInt();
		sc.nextLine();
		ArrayList<String> contactNumbers=new ArrayList<String>();
		for(int i=1;i<=n;i++)
		{
			System.out.println("Enter Contact phonenumber:"+i);
			String contactNum=sc.nextLine();
			contactNumbers.add(contactNum);
		}
		c.setContactNumber(contactNumbers);
		contactList.add(c);
	}
	
	//function to remove the contact
	public void removeContact(Contact contact,ArrayList<Contact> contactList)throws ContactNotFoundException {
		int flag=0;
		Iterator<Contact> EachContact=contactList.iterator();
		while(EachContact.hasNext())
		{
			int cid=contact.getContactID();
			Contact c=EachContact.next();
			if(c.getContactID()==cid)
			{
				flag=1;
				EachContact.remove();
				break;
			}
			else
			{
				flag=0;
			}
		}
		try {
		if(flag==0)
		{
			throw new ContactNotFoundException("Contact Not Found!!");
		}
		else
		{
			System.out.println("Successfully removed from the list");
			System.out.println("The list now is:");
			if(contactList.size()==0) {
				System.out.println("List is empty");
			}
			display(contactList);		
		}
		}catch(ContactNotFoundException e) {
			System.out.println("no contact with this id");
		}
	}
	
	//function to search contact by name
	
	public ArrayList<Contact> searchContactByName(String name,ArrayList<Contact> contactList) throws ContactNotFoundException {
		int flag=0;
		Contact cn=new Contact();
		ArrayList<Contact> cntName=new ArrayList<Contact>();
		try {
		for(Contact c: contactList)
		{
			if(c.getContactName().equals(name))
			{
				flag=1;
				cn=c;
				cntName.add(cn);	
			}
		}
		if(flag==0)
		{
			throw new ContactNotFoundException("Sorry");
		}}catch(ContactNotFoundException e) {
			System.out.println("ContactName is not available");
		}
		return cntName;
	}
	
	//function to search contact by number
	public ArrayList<Contact> searchContactByNumber(String number,ArrayList<Contact> contactList){
		ArrayList<Contact> numberList=new ArrayList<Contact>();
		int flag=1;
		for(Contact c: contactList)
		{
			flag=0;
			ArrayList<String> nums=new ArrayList<String>();
			ArrayList<String> number1=c.getContactNumber();
			int cnt=0;
			for(String str: number1)
			{
				for(int i=0;i<str.length();i++)
				{
					if(str.substring(i).contains(number))
					{
						cnt++;
					}
				}
				if(cnt>1)
				{
					flag=1;
					break;
				}	
			}
			if(flag==1)
			{
				numberList.add(c);
			}
			cnt=0;
		}
		try {
		if(numberList.isEmpty())
		{
			throw new ContactNotFoundException("Sorry....");
		}}catch(ContactNotFoundException e) {
			System.out.println("No such contact found");
		}
	
		
		
		return numberList;
	}
	
	//function to add contact to existing contact
	public void addContactNumber(int id,String contact,ArrayList<Contact> contactList) {
		for(Contact c: contactList)
		{
			int cId=c.getContactID();
			if(cId==id)
			{
				ArrayList<String> cn=c.getContactNumber();
				cn.add(contact);
				c.setContactNumber(cn);
			}
		}
		System.out.println("Modified list");
		display(contactList);
		System.out.println();
	}
	
	//function to sort by name
	public void sortByName(ArrayList<Contact> contactList) {
		contactList.sort(new NameSorter());
		display(contactList);
		
	}
	
	//function to read from a file
	public void readFromFile(ArrayList<Contact> fileContact,String fileName)throws NullPointerException {
		File file=new File("C:\\Users\\HP\\eclipse-workspace\\Persistent_Assignments\\src\\Assignment10\\contact.txt");
	    String line;
	    BufferedReader reader = null;
		try {
			if(file.isFile())
			{
				FileReader fr=new FileReader(file);
				reader = new BufferedReader(fr);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    try {
			while ((line = reader.readLine()) != null)
			{
				Contact m=new Contact();
			    String[] parts = line.split(",");
			   int val=Integer.parseInt(parts[0]);
			   String name=parts[1];
			   String email=parts[2];
			   ArrayList<String> list=new ArrayList<String>();
			   for(int i=3;i<parts.length;i++)
			   {
				   list.add(parts[i]);
			   }
			   m.setContactID(val);
			   m.setContactName(name);
			   m.setEmail(email);
			   m.setContactNumber(list);
			   fileContact.add(m);
			}
	    } catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			System.out.println("Exception");
		}
	    try
	    {
	    	reader.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    display(fileContact);
	}
	
	//function to serailize
	public void serializeContactDetails(ArrayList<Contact> fileContact,String fileName) {
		try {
			ArrayList<String> num=new ArrayList<>();
			num.add("34994");
			Contact m = new Contact(101,"Aayan","Action@gmail.com",num);
			FileOutputStream file = new FileOutputStream(new File(fileName));
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(m);
			System.out.println("Serialization is done");
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//function to deserialize
	public ArrayList<Contact> deserializeContact(String fileName)
	{
		ArrayList<Contact> temp = new ArrayList<Contact>();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			ObjectInputStream in = new ObjectInputStream(file);
			Contact m=(Contact)in.readObject(); 
			System.out.println(m);
			System.out.println("Deserialization is done");
			in.close();
			file.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return temp;
	}
	
	//function to display contactList
	public void display(ArrayList<Contact> contactList) {
		for(Contact c:contactList) {
			System.out.println(c.getContactID()+" "+c.getContactName()+" "+c.getEmail()+" "+c.getContactNumber());
		}
	}
}
