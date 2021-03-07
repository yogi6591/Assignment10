package Assignment10;
import java.util.ArrayList;
import java.util.Scanner;

import assignment10.Contact;
import assignment10.ContactService;


public class MainClass {
	static ArrayList<Contact> contactList=new ArrayList<>();
	public static void main(String[] args) throws ContactNotFoundException {
		ContactService cs=new ContactService();
		Contact c=new Contact();
		//adding contact to the list
		System.out.println("*********************************************************************");
		System.out.println("Add contacts to list");
		cs.addContact(c, contactList);
		cs.display(contactList);
		System.out.println("********************************************************************");
		System.out.println("Remove contacts from list");
        System.out.println("Enter the contact Id for the contact you wish to remove: ");
		Scanner sc=new Scanner(System.in);
		Contact c1=new Contact();
		int id=sc.nextInt();
		for(Contact contact: contactList)
		{
			if(contact.getContactID()==id)
			{
				c1=contact;
			}
		}
		if(c1!=null)
		{
			cs.removeContact(c1,contactList);
		}
		System.out.println("********************************************************************");
		System.out.println("Search contact By Name");
		System.out.println("Enter the name whose contact you want to search: ");
		String name=sc.next();
		ArrayList<Contact> nameList=cs.searchContactByName(name, contactList);
		cs.display(nameList);
		System.out.println("********************************************************************");
		System.out.println("Search contact By Number");
		System.out.println("Enter the number whose contact you want to search: ");
		String number=sc.next();
		ArrayList<Contact> numberList=cs.searchContactByNumber(number, contactList);
		cs.display(numberList);
		System.out.println("********************************************************************");
		System.out.println("Add contact to the existing list: ");
		System.out.println("Enter the id for which you want to add contact:");
		int idtoChange=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the number you want to add: ");
		String contact=sc.next();
		cs.addContactNumber(idtoChange,contact,contactList);
		System.out.println("********************************************************************");
		System.out.println("Sort contactList by name:");
		cs.sortByName(contactList);
		System.out.println("********************************************************************");
		System.out.println("Read from file");
		//ArrayList<Contact> fileContact=new ArrayList<>();
		cs.readFromFile(contactList,contact);
		System.out.println("********************************************************************");
		System.out.println("Serialization");
		cs.serializeContactDetails(contactList,contact);
		System.out.println("********************************************************************");
		System.out.println("Deserialization");
		ArrayList<Contact> deserialization=new ArrayList<>();
		deserialization=cs.deserializeContact(contact);
		cs.display(deserialization);
		System.out.println("********************************************************************");
		System.out.println("Program Ends");
	}

}
