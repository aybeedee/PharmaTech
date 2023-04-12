package businessLogic;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Medicine {

	private SimpleStringProperty name;
	private SimpleStringProperty brand;
	private SimpleIntegerProperty stockQty;
	private SimpleDoubleProperty price;
	private SimpleObjectProperty<LocalDate> arrivalDate;
	private SimpleObjectProperty<LocalDate> expiryDate;
	
	public Medicine(String n, String b, int s, double p, LocalDate a, LocalDate e) {
		
		name = new SimpleStringProperty(n);
		brand = new SimpleStringProperty(b);
		stockQty = new SimpleIntegerProperty(s);
		price = new SimpleDoubleProperty(p);
		arrivalDate = new SimpleObjectProperty<LocalDate>(a);
		expiryDate = new SimpleObjectProperty<LocalDate>(e);
	}
	
	public Medicine(String n, String b, int q, double p, int aY, int aM, int aD, int eY, int eM, int eD) {
		
		name = new SimpleStringProperty(n);
		brand = new SimpleStringProperty(b);
		stockQty = new SimpleIntegerProperty(q);
		price = new SimpleDoubleProperty(p);
		arrivalDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(aY, aM, aD));
		expiryDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(eY, eM, eD));
	}
	
	public String getName() {
		
		return name.get();
	}
	
	public String getBrand() {
		
		return brand.get();
	}
	
	public int getStockQty() {
		
		return stockQty.get();
	}
	
	public void setStockQty(int newStockQty) {
		
		stockQty.set(newStockQty);
	}
	
	public void useStock(int x) {
		
		stockQty.set(stockQty.get()-x);
	}
	
	public double getPrice() {
		
		return price.get();
	}
	
	public void setPrice(double newUnitPrice) {
		
		price.set(newUnitPrice);
	}
	
	public LocalDate getArrivalDate() {
		
		return arrivalDate.get();
	}
	
	public LocalDate getExpiryDate() {
		
		return expiryDate.get();
	}
}
