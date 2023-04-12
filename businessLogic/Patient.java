package businessLogic;
import java.util.*;

public class Patient {

	private String username;
	private String password;
	
	private String patientID;
	
	private String phoneNo;
	private String bloodType;
	private String medHistory;
	
	private ArrayList<Prescription> patientPrescriptions;
	
	public Patient(String user, String pass, String id, String phone, String btype, String medhist) {
		
		username = user;
		password = pass;
		patientID= id;
		phoneNo = phone;
		bloodType = btype;
		medHistory = medhist;
		patientPrescriptions = new ArrayList<Prescription>();
	}
	
	public Patient(String user, String pass) {
		
		username = user;
		password = pass;
		patientPrescriptions = new ArrayList<Prescription>();
	}
	
	public String enterInformation(String phone, String btype, String medhist) {
		
		phoneNo = phone;
		bloodType = btype;
		medHistory = medhist;
		patientID = (UUID.randomUUID()).toString().substring(0, 8);
		
		return patientID;
	}
	
	public void addPrescription(Prescription p) {
		
		patientPrescriptions.add(p);
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public String getID() {
		
		return patientID;
	}
	
	public String getPhoneNo() {
		
		return phoneNo;
	}
	
	public String getBloodType() {
		
		return bloodType;
	}
	
	public String getMedHistory() {
		
		return medHistory;
	}
	
	public ArrayList<Prescription> getPatientPrescriptions() {
		
		return patientPrescriptions;
	}
}
