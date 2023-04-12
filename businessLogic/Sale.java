package businessLogic;
import java.util.*;

public class Sale {

	private String saleID;
	private String customerName;
	private String customerCNIC;
	private double total;
	private double paidAmount;
	private double change;
	
	private ArrayList<PrescribedMedicine> soldMedicines;
	
	public Sale() {
		
		saleID = (UUID.randomUUID()).toString().substring(0, 8);
		soldMedicines = new ArrayList<PrescribedMedicine>();
		total = 0;
	}
	
	public void addSoldMedicine(PrescribedMedicine pm, double price) {
		
		total += pm.getQuantity()*price;
		soldMedicines.add(pm);
	}
	
	public ArrayList<PrescribedMedicine> getSoldMedicines() {
		
		return soldMedicines;
	}
	
	public void setPaidAmount(double x) {
		
		paidAmount = x;
	}
	
	public String getSaleID() {
		
		return saleID;
	}

	public String getCustomerName() {
		
		return customerName;
	}
	
	public String getCustomerCNIC() {
		
		return customerCNIC;
	}
	
	public void checkoutDetails(String n, String c, double a) {
		
		customerName = n;
		customerCNIC = c;
		paidAmount = a;
		change = paidAmount - total;
	}
	
	public double getTotal() {
		
		return total;
	}
	
	public double getPaidAmount() {
		
		return paidAmount;
	}
	
	public double getChange() {
		
		return change;
	}
}
