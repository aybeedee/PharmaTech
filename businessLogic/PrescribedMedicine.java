package businessLogic;

public class PrescribedMedicine {

	private String medicineName;
	private int quantity;
	private String comment;
	
	public PrescribedMedicine(String m, int q, String c) {
		
		medicineName = m;
		quantity = q;
		comment = c;
	}
	
	public String getMedicineName() {
		
		return medicineName;
	}
	
	public int getQuantity() {
		
		return quantity;
	}
	
	public String getComment() {
		
		return comment;
	}
}
