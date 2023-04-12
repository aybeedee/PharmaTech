package businessLogic;
import java.util.*;

public class PatientCatalog {
	
	private ArrayList<Patient> patients;

	private String currentUser;
	
	PatientCatalog() {

//		patients = new ArrayList<Patient>();
//		
//		//Sample entries
//		Patient p1 = new Patient("abdullah", "1234");
//		p1.enterInformation("03331234567", "O Positive", "Insomniac");
//		
//		/*Prescription p = new Prescription("Dr. Imran");
//		p.addMedicine("Panadol", 14, "1 week dose");
//		p.addMedicine("Calpol", 5, "just chug it");
//		
//		Prescription pTwo = new Prescription("Dr. Sheikh");
//		pTwo.addMedicine("TelFast", 12, "2 week dose");
//		pTwo.addMedicine("ABC", 5, "take it...please");
//		
//		p1.addPrescription(p);
//		p1.addPrescription(pTwo);*/
//		
//		patients.add(p1);
//		
//		Patient p2 = new Patient("der", "5678");
//		p2.enterInformation("929292929", "AB Negative", "zeep");
//		patients.add(p2);
	}
	
	public void setPatients(ArrayList<Patient> ps) {
		
		patients = ps;
	}
	
	public ArrayList<Patient> getPatients() {
		
		return patients;
	}
	
	public void makeNewPatient(String pUser, String pPass) {
		
		Patient p = new Patient(pUser, pPass);
		currentUser = pUser;
		patients.add(p);
	}
	
	public String enterInformation(String phone, String btype, String medhist) {
		
		Patient p = null;

		for (int i = 0; i < patients.size(); i++) {
			
			p = patients.get(i);
			
			if (p.getUsername() == currentUser) {
				
				break;
			}
		}
		
		return p.enterInformation(phone, btype, medhist);

	}
	
	public Patient getCurrentPatient() {
		
		Patient p = null;

		for (int i = 0; i < patients.size(); i++) {
			
			p = patients.get(i);
			
			if (p.getUsername() == currentUser) {
				
				break;
			}
		}
		
		return p;
	}
	
	public boolean validatePatient(String u, String pass) {
		
		Patient p = null;
		
		for (int i = 0; i < patients.size(); i++) {
			
			p = patients.get(i);
			
			if (u.equals(p.getUsername())) {
				
				if (pass.equals(p.getPassword())) {
					
					currentUser = p.getUsername();
					return true;
				}
				
				else {
					
					return false;
				}
			}
		}
		
		return false;
	}
	
	public boolean findPatient(String u) {
		
		Patient p = null;
		
		for (int i = 0; i < patients.size(); i++) {
			
			p = patients.get(i);
			
			if (u.equals(p.getUsername())) {

				currentUser = p.getUsername();
				return true;
			}
		}
		
		return false;
	}
	
	public void displayPatients() {
		
		Patient p = null;

		for (int i = 0; i < patients.size(); i++) {
			
			p = patients.get(i);
			
			System.out.println(p.getUsername() + " " + p.getPassword());
		}
	}
}




