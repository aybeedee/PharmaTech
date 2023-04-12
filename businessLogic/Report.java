package businessLogic;

import java.util.ArrayList;
import java.util.UUID;

import javafx.collections.ObservableList;

public class Report {

	private String reportID;
	private ObservableList<Medicine> medicines;
	private ArrayList<Prescription> prescriptions;
	
	public Report(ObservableList<Medicine> meds, ArrayList<Prescription> prescs) {
		
		reportID = (UUID.randomUUID()).toString().substring(0, 8);
		medicines = meds;
		prescriptions = prescs;
	}
	
	public String getID() {
		
		return reportID;
	}
	
	public ObservableList<Medicine> getMedicines() {
		
		return medicines;
	}

	public ArrayList<Prescription> getPrescriptions() {
		
		return prescriptions;
	}
}
