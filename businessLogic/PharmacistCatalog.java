package businessLogic;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public class PharmacistCatalog {

	private ArrayList<Pharmacist> pharmacists;

	private String currentUser;
	
	PharmacistCatalog() {

		/*pharmacists = new ArrayList<Pharmacist>();
		
		//Sample entries
		Pharmacist p1 = new Pharmacist("ali", "1234", "Ali Ahmed");
		pharmacists.add(p1);

		Pharmacist p2 = new Pharmacist("sheikh", "1234", "Sheikh Ahmed");
		pharmacists.add(p2);
		
		Pharmacist p3 = new Pharmacist("zain", "1234", "Zain Ahmed");
		pharmacists.add(p3);*/
	}
	
	public void setPharmacists(ArrayList<Pharmacist> phs) {
		
		pharmacists = phs;
	}
	
	public void registerPharmacist(String user, String pass, String name) {
		
		Pharmacist p = new Pharmacist(user, pass, name);
		pharmacists.add(p);
	}
	
	public Pharmacist getCurrentPharmacist() {
		
		Pharmacist p = null;

		for (int i = 0; i < pharmacists.size(); i++) {
			
			p = pharmacists.get(i);
			
			if (p.getUsername() == currentUser) {
				
				break;
			}
		}
		
		return p;
	}
	
	public boolean validatePharmacist(String u, String pass) {
		
		Pharmacist p = null;
		
		for (int i = 0; i < pharmacists.size(); i++) {
			
			p = pharmacists.get(i);
			
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
}
