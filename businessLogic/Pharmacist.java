package businessLogic;

import java.util.UUID;

public class Pharmacist {

	private String username;
	private String password;
	
	private String fullName;
	private String empID;

	public Pharmacist(String user, String pass, String name, String id) {
		
		username = user;
		password = pass;
		fullName = name;
		empID = id;
	}
	
	public Pharmacist(String user, String pass, String name) {
		
		username = user;
		password = pass;
		fullName = name;
		empID = (UUID.randomUUID()).toString().substring(0, 8);
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public String getFullName() {
		
		return fullName;
	}
	public String getID() {
		
		return empID;
	}
}
