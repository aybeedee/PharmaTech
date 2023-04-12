package businessLogic;

import java.util.UUID;

public class Manager {

	String name;
	String cnic;
	String username;
	String password;
	String empID;
	String phoneNo;
	
	public Manager(String n, String user, String pass) {
		
		name = n;
		username = user;
		password = pass;
		cnic = null;
		empID = (UUID.randomUUID()).toString().substring(0, 8);
		phoneNo = null;
	}
	
	public Manager() {
		
		name = null;
	}
	
	public void setValues(String n, String c, String user, String pass, String phone) {
		
		name = n;
		cnic = c;
		username = user;
		password = pass;
		phoneNo = phone;
		
		empID = (UUID.randomUUID()).toString().substring(0, 8);
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getCNIC() {
		
		return cnic;
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public String getID() {
		
		return empID;
	}
	
	public String getPhoneNo() {
		
		return phoneNo;
	}
	
	public boolean validate(String user, String pass) {
		
		if ((username.equals(user)) && (password.equals(pass))) {
			
			return true;
		}
		
		return false;
	}
}
