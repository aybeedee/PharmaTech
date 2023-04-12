package businessLogic;
import java.time.LocalDate;
import java.util.*;

public class Prescription {

	private String prescriptionID;
	private String doctorName;
	private ArrayList<PrescribedMedicine> prescribedMedicines;
	private boolean handled;
	private LocalDate prescriptionDate;
	
	public Prescription(String d) {
		
		prescriptionID = (UUID.randomUUID()).toString().substring(0, 8);
		doctorName = d;
		prescribedMedicines = new ArrayList<PrescribedMedicine>();
		handled = false;
		prescriptionDate = LocalDate.now();
	}
	
	public Prescription(String id, String d, boolean h, LocalDate date) {
		
		prescriptionID = id;
		doctorName = d;
		handled = h;
		prescriptionDate = date;
		prescribedMedicines = new ArrayList<PrescribedMedicine>();
	}
	
	public void addMedicine(String m, int q, String c) {
		
		PrescribedMedicine pm = new PrescribedMedicine(m, q, c);
		prescribedMedicines.add(pm);
	}
	
	public String getPrescriptionID() {
		
		return prescriptionID;
	}
	
	public String getDoctorName() {
		
		return doctorName;
	}
	
	public Boolean getHandled() {
		
		return handled;
	}
	
	public void setHandled(boolean x) {
		
		handled = x;
	}
	
	public LocalDate getPrescriptionDate() {
		
		return prescriptionDate;
	}
	public ArrayList<PrescribedMedicine> getPrescribedMedicines() {
		
		return prescribedMedicines;
	}
}
