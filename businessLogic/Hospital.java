package businessLogic;
import java.util.*;
import javafx.beans.property.*;

public class Hospital {

	private SimpleStringProperty name;
	private SimpleStringProperty address;
	private SimpleStringProperty username;
	private SimpleStringProperty password;
	private SimpleStringProperty hospitalID;
	private SimpleBooleanProperty verification;

	public Hospital(String n, String add, String user, String pass, String id, boolean v) {
		
		name = new SimpleStringProperty(n);
		address = new SimpleStringProperty(add);
		username = new SimpleStringProperty(user);
		password = new SimpleStringProperty(pass);
		hospitalID = new SimpleStringProperty(id);
		verification = new SimpleBooleanProperty(v);
	}
	
	public Hospital(String n, String add, String user, String pass) {
		
		name = new SimpleStringProperty(n);
		address = new SimpleStringProperty(add);
		username = new SimpleStringProperty(user);
		password = new SimpleStringProperty(pass);
		hospitalID = new SimpleStringProperty((UUID.randomUUID()).toString().substring(0, 8));
		verification = new SimpleBooleanProperty(false);
	}
	
	public String getName() {
		
		return name.get();
	}
	
	public String getAddress() {
		
		return address.get();
	}
	
	public String getUsername() {
		
		return username.get();
	}
	
	public String getPassword() {
		
		return password.get();
	}
	
	public String getID() {
		
		return hospitalID.get();
	}
	
	public boolean getVerification() {
		
		return verification.get();
	}
	
	public void setVerification(boolean x) {
		
		verification.set(x);
	}
}
