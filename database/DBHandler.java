package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Random;

import businessLogic.Manager;
import javafx.collections.ObservableList;
import businessLogic.*;

public class DBHandler {

	public Manager getManager(Connection conn) throws SQLException {
		
		Manager manager = null;
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from manager");
		
		String user, pass, name;
		
		while (rs.next()) {
			
			user = rs.getString("username");
			pass = rs.getString("m_password");
			name = rs.getString("m_name");
			
			manager = new Manager(name, user, pass);
		}
		
		return manager;
	}
	
	public void getPatients(ArrayList<Patient> patients, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from patient_3");
		
		String user, pass, id, phoneno, btype, medhistory;
		
		while (rs.next()) {
			
			user = rs.getString("username");
			pass = rs.getString("p_password");
			id = rs.getString("patientID");
			phoneno = rs.getString("phoneNo");
			btype = rs.getString("bloodType");
			medhistory = rs.getString("medHistory");
			
			Patient p = new Patient(user, pass, id, phoneno, btype, medhistory);
			patients.add(p);
		}
	}
	
	public void getHopsitals(ObservableList<Hospital> hospitals, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from hospital_1");
		
		String name, address, user, pass, id, verification;
		boolean v;
		
		while (rs.next()) {
			
			name = rs.getString("h_name");
			address = rs.getString("address");
			user = rs.getString("username");
			pass = rs.getString("h_password");
			id = rs.getString("hospitalID");
			verification = rs.getString("verification");
			
			if (verification.equals("Y")) {
				
				v = true;
			}
			
			else {
				
				v = false;
			}
			
			Hospital h = new Hospital(name, address, user, pass, id, v);
			hospitals.add(h);
		}
	}
	
	public void getPharmacists(ArrayList<Pharmacist> pharmacists, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from pharmacist_1");
		
		String name, user, pass, id;
		
		while (rs.next()) {
			
			name = rs.getString("m_name");
			user = rs.getString("username");
			pass = rs.getString("m_password");
			id = rs.getString("empID");
			
			Pharmacist ph = new Pharmacist(user, pass, name, id);
			pharmacists.add(ph);
		}
	}
	
	public void getMedicines(ObservableList<Medicine> medicines, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from inventory");
		
		String name, brand;
		int stock;
		double price;
		LocalDate arrival;
		LocalDate expiry;

		
		while (rs.next()) {
			
			name = rs.getString("i_name");
			brand = rs.getString("brand");
			stock = rs.getInt("Stock");
			price = rs.getDouble("price");
			arrival = rs.getDate("LocalDate").toLocalDate();
			expiry = rs.getDate("expiry").toLocalDate();
			
			Medicine m = new Medicine(name, brand, stock, price, arrival, expiry);
			medicines.add(m);
		}
	}
	
	public void getOrders(ObservableList<Order> orders, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from order1");
		
		String id, name, brand, approval;
		boolean a;
		int stock;
		double price;
		LocalDate arrival;
		LocalDate expiry;

		
		while (rs.next()) {
			
			id = rs.getString("orderID");
			approval = rs.getString("approval");
			name = rs.getString("o_name");
			brand = rs.getString("brand");
			stock = rs.getInt("stockQty");
			price = rs.getDouble("price");
			arrival = rs.getDate("arrivalDate").toLocalDate();
			expiry = rs.getDate("expiryDate").toLocalDate();
			
			if (approval.equals("Y")) {
				
				a = true;
			}
			
			else {
				
				a = false;
			}
			
			Order o = new Order(id, a, name, brand, stock, price, arrival, expiry);
			orders.add(o);
		}
	}
	
	public void getPrescriptions(ArrayList<Patient> patients, ArrayList<Prescription> prescriptions, Connection conn) throws SQLException {
		
		Statement statement;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select p.presID,m.username,p.p_status,p.doctor_name,p.LocalDate,mm.medicine,mm.m_comment,mm.quantity from prescription_3 p, patient_3 m, med_pre_4 mm where p.patient_user=m.username and mm.prescription_ID=p.presID");
		
		String id, user, status, docName, medName, comment;
		int qty;
		boolean s;
		LocalDate presDate;
		
		while (rs.next()) {
			
			id = rs.getString("presID");
			user = rs.getString("username");
			status = rs.getString("p_status");
			docName = rs.getString("doctor_name");
			presDate = rs.getDate("LocalDate").toLocalDate();
			medName = rs.getString("medicine");
			comment = rs.getString("m_comment");
			qty = rs.getInt("quantity");
			
			if (status.equals("Y")) {
				
				s = true;
			}
			
			else {
				
				s = false;
			}
			
			Prescription temp = null;
			
			for (int i = 0; i < prescriptions.size(); i++) {
				
				temp = prescriptions.get(i);
				
				if (id.equals(temp.getPrescriptionID())) {
					
					break;
				}
				
				else {
					
					temp = null;
				}
			}
			
			if (temp == null) {
				
				Prescription p = new Prescription(id, docName, s, presDate);
				p.addMedicine(medName, qty, comment);
				prescriptions.add(p);
				
				Patient pTemp = null;
				
				for (int i = 0; i < patients.size(); i++) {
					
					if (user.equals(patients.get(i).getUsername())) {
						
						pTemp = patients.get(i);
					}
				}
				
				pTemp.addPrescription(p);
			}
			
			else {
				
				temp.addMedicine(medName, qty, comment);
			}
		}
	}
	
	public void insertHospital(String s1,String s2,String s3,String s4,String s5,Boolean s6,Connection conn) throws SQLException  {
		
		String verification;
		
		if (s6) {
			
			verification = "Y";
		}
		
		else {
			
			verification = "N";
		}
		
		Statement stm;
		stm = conn.createStatement();
		String sql = "insert into hospital_1 values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+verification+"')";
		stm.executeUpdate(sql);
	}
	
	public void insertInventory(String name,String brand, int qty, double price, LocalDate arrD, LocalDate eDate, Connection conn) throws SQLException  {
		 
		int p = (int)price;
		
		Statement stm;
		stm = conn.createStatement();
		String sql = "insert into inventory values('"+name+"','"+brand+"',"+qty+","+p+",'"+convertToOracleDate(arrD)+"','"+convertToOracleDate(eDate)+"')";
		stm.executeUpdate(sql);
	}
	
	public void insertOrder(String x,Boolean s1,String s2,String s3,int x1,int x2,LocalDate x3,LocalDate x4,String s4,Connection conn) throws SQLException {
		
		String approval;
		
		Random random = new Random();
		int orderID = random.nextInt(100);
		
		if (s1) {
			
			approval = "Y";
		}
		
		else {
			
			approval = "N";
		}
		
		Statement stm;
		stm = conn.createStatement();
		String sql = "insert into order1 values("+orderID+",'"+approval+"','"+s2+"','"+s3+"',"+x1+","+x2+",'"+convertToOracleDate(x3)+"','"+convertToOracleDate(x4)+"','"+s4+"')";
		stm.executeUpdate(sql);
	}
	
	public void insertPatient(String u, String p, String id, String phone, String btype, String medhist,Connection conn) throws SQLException {
		
		Statement stm;
		stm = conn.createStatement();
		String sql = "insert into patient_3 values('"+u+"','"+p+"','"+id+"','"+phone+"','"+btype+"','"+medhist+"')";
		stm.executeUpdate(sql);
	}
	
	public void updateHospital(String username, Connection conn) throws SQLException {
		
		Statement stm;
		stm = conn.createStatement();
		String sql = "update hospital_1 set verification = 'Y' where username = '"+username+"'";
		stm.executeUpdate(sql);
	}
	
	public String convertToOracleDate(LocalDate javaDate) {
		
		
		String javaDateString = javaDate.toString();
		String year = "";
		String month = "";
		String day = "";
		
		for (int i = 0; i < 4; i++) {
			
			year += javaDateString.charAt(i);
		}
		
		for (int i = 5; i < 7; i++) {
			
			month += javaDateString.charAt(i);
		}
		
		for (int i = 8; i < 10; i++) {
			
			day += javaDateString.charAt(i);
		}
		
		int m = Integer.parseInt(month);
		
		String oracleMonth;
		
		if (m == 1) {
			
			oracleMonth = "JAN";
		}
		
		else if (m == 2) {
			
			oracleMonth = "FEB";
		}
		
		else if (m == 3) {
			
			oracleMonth = "MAR";
		}
		
		else if (m == 4) {
			
			oracleMonth = "APR";
		}
		
		else if (m == 5) {
			
			oracleMonth = "MAY";
		}
		
		else if (m == 6) {
			
			oracleMonth = "JUN";
		}
		
		else if (m == 7) {
			
			oracleMonth = "JUL";
		}
		
		else if (m == 8) {
			
			oracleMonth = "AUG";
		}
		
		else if (m == 9) {
			
			oracleMonth = "SEP";
		}
		
		else if (m == 10) {
			
			oracleMonth = "OCT";
		}
		
		else if (m == 11) {
			
			oracleMonth = "NOV";
		}
		
		else {
			
			oracleMonth = "DEC";
		}
		
		String oracleDate = day + "-" + oracleMonth + "-" + year;
		
		return oracleDate;
	}
}

















