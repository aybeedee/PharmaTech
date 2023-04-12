package businessLogic;

import java.util.ArrayList;

import javafx.collections.*;

public class HospitalCatalog {

	private ObservableList<Hospital> hospitals;

	private String currentHospital;
	
	public HospitalCatalog() {

		/*hospitals = FXCollections.observableArrayList();
		
		//Sample entries
		Hospital h1 = new Hospital("shifa international hospital", "h-8/4 islamabad", "shifa", "12345");
		h1.setVerification(true);
		hospitals.add(h1);
		
		Hospital h2 = new Hospital("kulsoom international hospital", "blue area islamabad", "klsm", "123");
		hospitals.add(h2);
		
		Hospital h3 = new Hospital("Ali Medical", "f8 islamabad", "amc", "123");
		hospitals.add(h3);
		
		Hospital h4 = new Hospital("shifa international hospital", "h-8/4 islamabad", "shifa", "12345");
		hospitals.add(h4);
		
		Hospital h5 = new Hospital("kulsoom international hospital", "blue area islamabad", "klsm", "123");
		hospitals.add(h5);
		
		Hospital h6 = new Hospital("Ali Medical", "f8 islamabad", "amc", "123");
		hospitals.add(h6);
		
		Hospital h7 = new Hospital("shifa international hospital", "h-8/4 islamabad", "shifa", "12345");
		hospitals.add(h7);
		
		Hospital h8 = new Hospital("kulsoom international hospital", "blue area islamabad", "klsm", "123");
		hospitals.add(h8);
		
		Hospital h9 = new Hospital("Ali Medical", "f8 islamabad", "amc", "123");
		hospitals.add(h9);
		
		Hospital h10 = new Hospital("shifa international hospital", "h-8/4 islamabad", "shifa", "12345");
		hospitals.add(h10);
		
		Hospital h11 = new Hospital("kulsoom international hospital", "blue area islamabad", "klsm", "123");
		hospitals.add(h11);
		
		Hospital h12 = new Hospital("Ali Medical", "f8 islamabad", "amc", "123");
		hospitals.add(h12);*/
	}
	
	public void setHospitals(ObservableList<Hospital> hs) {
		
		hospitals = hs;
	}
	
	public void registerHospital(String n, String add, String user, String pass) {
		
		Hospital h = new Hospital(n, add, user, pass);
		currentHospital = user;
		hospitals.add(h);
	}
	
	public boolean checkHospitalVerification(String id) {
		
		Hospital h = null;
		
		for (int i = 0; i < hospitals.size(); i++) {
			
			h = hospitals.get(i);
			
			if (id.equals(h.getID())) {
				
				if (h.getVerification()) {

					return true;
				}
				
				else {
					
					return false;
				}
			}
		}
		
		return false;
	}

	public boolean validateHospital(String u, String pass) {
		
		Hospital h = null;
		
		for (int i = 0; i < hospitals.size(); i++) {
			
			h = hospitals.get(i);
			
			if (u.equals(h.getUsername())) {
				
				if (pass.equals(h.getPassword())) {
					
					currentHospital = h.getUsername();
					return true;
				}
				
				else {
					
					return false;
				}
			}
		}
		
		return false;
	}
	
	public Hospital getCurrentHospital() {
		
		Hospital h = null;

		for (int i = 0; i < hospitals.size(); i++) {
			
			h = hospitals.get(i);
			
			if (h.getUsername() == currentHospital) {
				
				break;
			}
		}
		
		return h;
	}
	
	public void displayHospitals() {
		
		Hospital h = null;

		for (int i = 0; i < hospitals.size(); i++) {
			
			h = hospitals.get(i);
			
			System.out.println(h.getUsername() + h.getPassword());
		}
	}
	
	public ObservableList<Hospital> getHospitals() {
		
		return hospitals;
	}
}
