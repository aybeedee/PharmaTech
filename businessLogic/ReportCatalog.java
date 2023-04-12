package businessLogic;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public class ReportCatalog {

	private ArrayList<Report> reports;

	private String currentReport;
	
	ReportCatalog() {

		reports = new ArrayList<Report>();
	}
	
	public void makeNewReport(ObservableList<Medicine> meds, ArrayList<Prescription> prescs) {
		
		Report r= new Report(meds, prescs);
		currentReport = r.getID();
		reports.add(r);
	}
	
	public Report getCurrentReport() {
		
		Report r = null;

		for (int i = 0; i < reports.size(); i++) {
			
			r = reports.get(i);
			
			if (r.getID() == currentReport) {
				
				break;
			}
		}
		
		return r;
	}
}
