package businessLogic;
import javafx.collections.*;

public class Inventory {

	private ObservableList<Medicine> medicines;
	
	public Inventory() {

		/*medicines = FXCollections.observableArrayList();
		
		//Sample entries
		Medicine m1 = new Medicine("Panadol", "GSK", 100, 125.0, 2022, 11, 26, 2023, 1, 12);
		medicines.add(m1);
		
		Medicine m2 = new Medicine("Brufen", "Abbott", 275, 225.0, 2022, 11, 29, 2023, 3, 23);
		medicines.add(m2);
		
		Medicine m3 = new Medicine("Calpol", "Brein", 540, 350.0, 2022, 11, 29, 2023, 4, 26);
		medicines.add(m3);*/
	}
	
	public void setMedicines(ObservableList<Medicine> ms) {
		
		medicines = ms;
	}
	
	public Medicine searchMedicine(String name) {
		
		Medicine m = null;
		
		for (int i = 0; i < medicines.size(); i++) {
			
			m = medicines.get(i);
			
			if (name.equals(m.getName())) {

				return m;
			}
		}
		
		return null;
	}
	
	public void fetchOrder(Medicine m) {
		
		medicines.add(m);
	}
	
	public double deliverMedicine(String name, int qty) {
		
		Medicine m = null;
		
		for (int i = 0; i < medicines.size(); i++) {
			
			m = medicines.get(i);
			
			if (name.equals(m.getName())) {

				break;
			}
		}
		
		m.useStock(qty);
		
		return qty*m.getPrice();
	}
	
	public boolean checkAvailability(String name, int qty) {
		
		Medicine m = null;
		
		for (int i = 0; i < medicines.size(); i++) {
			
			m = medicines.get(i);
			
			if (name.equals(m.getName())) {

				break;
			}
		}
		
		if (m.getStockQty() < qty) {
			
			return false;
		}
		
		return true;
	}
	
	public double sellMedicine(String name, int qty) {
		
		Medicine m = null;
		
		for (int i = 0; i < medicines.size(); i++) {
			
			m = medicines.get(i);
			
			if (name.equals(m.getName())) {

				break;
			}
		}
		
		m.useStock(qty);
		
		return m.getPrice();
	}
	
	public ObservableList<Medicine> getMedicines() {
		
		return medicines;
	}
}
