package businessLogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderCatalog {

	private ObservableList<Order> orders;
	private String currentOrderID;
	
	public OrderCatalog() {

		/*orders = FXCollections.observableArrayList();
		
		//Sample entries
		Medicine m1 = new Medicine("ABC", "GSK", 10, 15.0, 2022, 11, 26, 2023, 5, 2);
		Order o1 = new Order(m1);
		orders.add(o1);
		
		Medicine m2 = new Medicine("XYZ", "Abbott", 27, 22.0, 2022, 11, 20, 2023, 7, 3);
		Order o2 = new Order(m2);
		orders.add(o2);*/
	}
	
	public void setOrders(ObservableList<Order> os) {
		
		orders = os;
	}
	
	public String makeNewOrder(String n, String b, String q, String p, String aY, String aM, String aD, String eY, String eM, String eD) {
		
		Medicine m = new Medicine(n, b, Integer.parseInt(q), Double.parseDouble(p), Integer.parseInt(aY), Integer.parseInt(aM), 
				Integer.parseInt(aD), Integer.parseInt(eY), Integer.parseInt(eM), Integer.parseInt(eD));
		Order o = new Order(m);
		orders.add(o);
		
		currentOrderID = o.getOrderID();
		
		return o.getOrderID();
	}
	
	public ObservableList<Order> getOrders() {
		
		return orders;
	}
	
	public Order getCurrentOrder() {
		
		Order o = null;

		for (int i = 0; i < orders.size(); i++) {
			
			o = orders.get(i);
			
			if (currentOrderID.equals(o.getOrderID())) {
				
				break;
			}
		}
		
		return o;
	}
}
