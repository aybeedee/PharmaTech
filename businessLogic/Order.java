package businessLogic;

import java.time.LocalDate;
import java.util.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

	private SimpleStringProperty orderID;
	private SimpleObjectProperty<Medicine> medicineOrder;
	private SimpleBooleanProperty approval;
	
	public Order(String id, boolean a, String n, String b, int s, double p, LocalDate ar, LocalDate e) {
		
		orderID = new SimpleStringProperty(id);
		approval = new SimpleBooleanProperty(a);
		Medicine m = new Medicine(n, b, s, p, ar, e);
		medicineOrder = new SimpleObjectProperty<Medicine>(m);
	}
	
	public Order(Medicine m) {
		
		orderID = new SimpleStringProperty((UUID.randomUUID()).toString().substring(0, 8));
		medicineOrder = new SimpleObjectProperty<Medicine>(m);
		approval = new SimpleBooleanProperty(false);
	}
	
	public String getOrderID() {
		
		return orderID.get();
	}
	
	public Medicine getMedicine() {
		
		return medicineOrder.get();
	}

	public String getName() {
		
		return getMedicine().getName();
	}
	
	public String getBrand() {
		
		return getMedicine().getBrand();
	}
	
	public int getQty() {
		
		return getMedicine().getStockQty();
	}
	
	public double getPrice() {
		
		return getMedicine().getPrice();
	}
	
	public LocalDate getArrivalDate() {
		
		return getMedicine().getArrivalDate();
	}
	
	public LocalDate getExpiryDate() {
		
		return getMedicine().getExpiryDate();
	}
	
	public Boolean getApproval() {
		
		return approval.get();
	}
	
	public void setApproval(boolean x) {
		
		approval.set(x);
	}
}
