package businessLogic;

import java.sql.*;
import java.util.ArrayList;

import database.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Factory {

	private static Factory register = null;
	
	private PatientCatalog pCatalog;
	private HospitalCatalog hCatalog;
	private PharmacistCatalog phCatalog;
	private OrderCatalog oCatalog;
	private PrescriptionCatalog prCatalog;
	private ReportCatalog rCatalog;
	private Inventory inventory;
	private SaleCatalog sales;
	private Manager manager;
	
	private Connection conn;
	private DBHandler db;
	
	private Factory() throws SQLException {
	
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ABD","abdullah","1234"); 
		}
		
		catch(ClassNotFoundException e) {
			
			System.out.println("Driver Not Loaded");
		}
		
		catch(SQLException e) {
			
			System.out.println("Connection is not Established!");
		}
		
		db = new DBHandler();
		
		manager = db.getManager(conn);
		
		ArrayList<Patient> patients = new ArrayList<Patient>();
		db.getPatients(patients, conn);
		pCatalog = new PatientCatalog();
		pCatalog.setPatients(patients);
		
		ObservableList<Hospital> hospitals = FXCollections.observableArrayList();
		db.getHopsitals(hospitals, conn);
		hCatalog = new HospitalCatalog();
		hCatalog.setHospitals(hospitals);
		
		ArrayList<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		db.getPharmacists(pharmacists, conn);
		phCatalog = new PharmacistCatalog();
		phCatalog.setPharmacists(pharmacists);

		ObservableList<Medicine> medicines = FXCollections.observableArrayList();
		db.getMedicines(medicines, conn);
		inventory = new Inventory();
		inventory.setMedicines(medicines);
		
		ObservableList<Order> orders = FXCollections.observableArrayList();
		db.getOrders(orders, conn);
		oCatalog = new OrderCatalog();
		oCatalog.setOrders(orders);
		
		ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
		db.getPrescriptions(patients, prescriptions, conn);
		prCatalog = new PrescriptionCatalog();
		prCatalog.setPrescriptions(prescriptions);
		
		rCatalog = new ReportCatalog();
		sales = new SaleCatalog();
		
	}
	
	public static Factory getRegister() throws SQLException {
		
		if (register == null) {
			
			register = new Factory();
		}
		
		return register;
	}
	
	public PatientCatalog getPatientCatalog() {
		
		return pCatalog;
	}
	
	public HospitalCatalog getHospitalCatalog() {
		
		return hCatalog;
	}
	
	public PharmacistCatalog getPharmacistCatalog() {
		
		return phCatalog;
	}
	
	public OrderCatalog getOrderCatalog() {
		
		return oCatalog;
	}
	
	public PrescriptionCatalog getPrescriptionCatalog() {
		
		return prCatalog;
	}
	
	public ReportCatalog getReportCatalog() {
		
		return rCatalog;
	}
	
	public Inventory getInventory() {
		
		return inventory;
	}
	
	public SaleCatalog getSaleCatalog() {
		
		return sales;
	}
	
	public Manager getManager() {
		
		return manager;
	}
	
	public Connection getConnection() {
		
		return conn;
	}
	
	public DBHandler getDBHandler() {
		
		return db;
	}
}