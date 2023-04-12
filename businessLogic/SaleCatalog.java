package businessLogic;
import java.util.*;

public class SaleCatalog {

	private ArrayList<Sale> sales;
	
	private String currentSale;
	
	public SaleCatalog() {
		
		sales = new ArrayList<Sale>();
	}
	
	public void makeNewSale() {
		
		Sale s = new Sale();
		currentSale = s.getSaleID();
		sales.add(s);
	}
	
	public Sale getCurrentSale() {
		
		Sale s = null;

		for (int i = 0; i < sales.size(); i++) {
			
			s = sales.get(i);
			
			if (s.getSaleID() == currentSale) {
				
				break;
			}
		}

		return s;
	}
	
	public void addSoldMedicine(String name, int qty, double price) {
		
		PrescribedMedicine pm = new PrescribedMedicine(name, qty, "");
		Sale s = getCurrentSale();
		s.addSoldMedicine(pm, price);
	}
}
