package businessLogic;

import java.util.ArrayList;

public class PrescriptionCatalog {

	private ArrayList<Prescription> prescriptions;

	private String currentPrescription;
	
	PrescriptionCatalog() {

		//prescriptions = new ArrayList<Prescription>();
		
		/*Prescription p = new Prescription("Dr. Imran");
		p.addMedicine("Panadol", 14, "1 week dose");
		p.addMedicine("Calpol", 5, "just chug it");
		
		Prescription pTwo = new Prescription("Dr. Sheikh");
		pTwo.addMedicine("TelFast", 12, "2 week dose");
		pTwo.addMedicine("ABC", 5, "take it...please");
		
		prescriptions.add(pTwo);
		prescriptions.add(p);*/
	}
	
	public void setPrescriptions(ArrayList<Prescription> p) {
		
		prescriptions = p;
	}
	
	public void makePrescription(String d) {
		
		Prescription p = new Prescription(d);
		currentPrescription = p.getPrescriptionID();
		prescriptions.add(p);
	}
	
	public Prescription getCurrentPrescription() {
		
		Prescription p = null;

		for (int i = 0; i < prescriptions.size(); i++) {
			
			p = prescriptions.get(i);
			
			if (p.getPrescriptionID() == currentPrescription) {
				
				break;
			}
		}
		
		return p;
	}
	
	public Prescription findPrescription(String id) {
		
		Prescription p = null;
		
		for (int i = 0; i < prescriptions.size(); i++) {
			
			p = prescriptions.get(i);
			
			if (id.equals(p.getPrescriptionID())) {
				
				break;
			}
		}
		
		return p;
	}
	
	public ArrayList<Prescription> getPrescriptions() {
		
		return prescriptions;
	}
}