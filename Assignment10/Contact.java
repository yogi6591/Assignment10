package Assignment10;

import java.util.ArrayList;
import java.util.Comparator;

public class Contact {
	private int contactID;
	private String contactName;
	private String email;
	private ArrayList<String> contactNumber;
	
	public Contact() {}
	
	public Contact(int contactID, String contactName, String email, ArrayList<String> contactNumber) {
		super();
		this.contactID = contactID;
		this.contactName = contactName;
		this.email = email;
		this.contactNumber = contactNumber;
	}
	public int getContactID() {
		return contactID;
	}
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<String> getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(ArrayList<String> contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
}
