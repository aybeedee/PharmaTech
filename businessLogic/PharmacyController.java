package businessLogic;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PharmacyController implements Initializable {
	
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
	
	@FXML
	private BorderPane idToGetStage;
	
	@FXML
	private Button patientBtn, pharmacistBtn, physicianBtn, managerBtn, loginBtn, registerBtn, 
	registerAccBtn, enterPInfoBtn, requestDeliveryBtn, loginAccBtn, patientLoginBackBtn, 
	registerManagerBtn, registerManagerAccBtn, hospitalLoginBtn, hospitalRegisterBtn, 
	registerHospitalBtn, makePrescriptionBtn, verifyHospitalBtn, verifyHospitalTCBtn, 
	manageInventoryBtn, handlePrescriptionBtn, processSaleBtn, addMedicineBtn, findMedicineBtn, 
	updateMedicineBtn, removeMedicineBtn, searchMedicineBtn, logoutBtn, searchPatientBtn, 
	enterDoctorNameBtn, requestOrderBtn, approveOrderBtn, approveOrderTCBtn, updateMedicineTCBtn, 
	deleteMedicineTCBtn, addPrescriptionMedicineBtn, confirmPrescriptionBtn, enterIDForDeliveryBtn, 
	addSaleItemBtn, proceedSaleBtn, checkoutSaleBtn, enterIDForHandleBtn, enterPatientForHandleBtn, 
	generateReportBtn, prescriptionsReportBtn, inventoryReportBtn, loginManagerBtn, loginManagerAccBtn;
	
	@FXML
	private TextField patientUser, patientPhone, patientUserLogin, managerName, managerCNIC, 
	managerPhone, managerUser, hospitalName, hospitalAddress, hospitalUser, hospitalUserLogin, 
	pharmacistUserLogin, medicineSearchPrompt, patientSearchPrompt, doctorNamePrompt, orderMedicineName, 
	orderMedicineBrand, orderMedicineQty, orderMedicinePrice, orderMedicineArrivalDateY, orderMedicineArrivalDateM, 
	orderMedicineArrivalDateD, orderMedicineExpiryDateY, orderMedicineExpiryDateM, orderMedicineExpiryDateD, 
	newStockQty, newUnitPrice, prescriptionMedicineName, prescriptionMedicineQty, deliveryPrescriptionID, 
	medicineNameSaleItem, medicineQtySaleItem, saleCustomerName, saleCustomerCNIC, salePaidAmount, reportStartDateY, 
	reportStartDateM, reportStartDateD, reportEndDateY, reportEndDateM, reportEndDateD, managerUserLogin, managerPassLogin;
	
	@FXML
	private PasswordField patientPass, patientPassLogin, managerPass, hospitalPass, hospitalPassLogin, 
	pharmacistPassLogin;
	
	@FXML
	private TextArea patientHist, prescriptionMedicineComment;
	
	@FXML
	private RadioButton patientbLA, patientbLB, patientbLAB, patientbLO, patientbSP, patientbSN, serviceType1, 
	serviceType2, serviceType3, serviceType4;
	
	@FXML
	private Text username, id, phonenumber, bloodtype, medicalhistory, loginErrorTxt, mgrname, mgrid, 
	hospitalname, hospitalid, hospitalverification, hospitalVerificationErrTxt,pharmacistname, 
	pharmacistid, noMedicineFoundTxt, medicineFoundName, medicineFoundBrand, medicineFoundStockQty, 
	medicineFoundPrice, medicineFoundArrivalDate, medicineFoundExpiryDate, noPatientFoundTxt, prescriptionID,
	prescriptionDoctorName, prescriptionHandledStatus, prescriptionDate, prescriptionDetails, prescriptionDelivery, 
	stockInsufficientTxt, saleTotalTxt, receiptCheckout, checkoutSaleID, checkoutCustomerName, checkoutCustomerCNIC, 
	checkoutTotal, checkoutPaidAmount, checkoutChange, medicineReportIDTxt, prescriptionReportIDTxt, managerloginErrorTxt;
	
	@FXML
	private VBox medicineFoundDetails, PrescriptionAppendVBox, DeliveryPrescriptionAppendVBox, checkoutItemsVBox, 
	PrescriptionReportVBox;
	
	@FXML
	private TableView<Hospital> hospitalsTableView;
	
	@FXML
	private TableColumn<Hospital, String> hospitalNameTC;
	
	@FXML
	private TableColumn<Hospital, String> hospitalAddressTC;
	
	@FXML
	private TableColumn<Hospital, Boolean> hospitalVerificationTC;
	
	@FXML
	private TableView<Order> ordersTableView;
	
	@FXML
	private TableColumn<Order, String> orderIDTC;
	
	@FXML
	private TableColumn<Order, String> orderMedicineNameTC;
	
	@FXML
	private TableColumn<Order, String> orderMedicineBrandTC;
	
	@FXML
	private TableColumn<Order, Integer> orderMedicineQtyTC;
	
	@FXML
	private TableColumn<Order, Double> orderMedicinePriceTC;
	
	@FXML
	private TableColumn<Order, LocalDate> orderMedicineArrivalDateTC;
	
	@FXML
	private TableColumn<Order, LocalDate> orderMedicineExpiryDateTC;
	
	@FXML
	private TableColumn<Order, Boolean> orderApprovalTC;
	
	@FXML
	private TableView<Medicine> updateMedicineTableView, deleteMedicineTableView, medicineReportTableView;
	
	@FXML
	private TableColumn<Medicine, String> updateMedicineNameTC, deleteMedicineNameTC, medicineReportNameTC;
	
	@FXML
	private TableColumn<Medicine, String> updateMedicineBrandTC, deleteMedicineBrandTC, medicineReportBrandTC;
	
	@FXML
	private TableColumn<Medicine, Integer> updateMedicineQtyTC, deleteMedicineQtyTC, medicineReportQtyTC;
	
	@FXML
	private TableColumn<Medicine, Double> updateMedicinePriceTC, deleteMedicinePriceTC, medicineReportPriceTC;
	
	@FXML
	private TableColumn<Medicine, LocalDate> updateMedicineArrivalDateTC, deleteMedicineArrivalDateTC, medicineReportArrivalDateTC;
	
	@FXML
	private TableColumn<Medicine, LocalDate> updateMedicineExpiryDateTC, deleteMedicineExpiryDateTC, medicineReportExpiryDateTC;
	
	public PharmacyController() throws SQLException {
		
		pCatalog = Factory.getRegister().getPatientCatalog();
		hCatalog = Factory.getRegister().getHospitalCatalog();
		phCatalog = Factory.getRegister().getPharmacistCatalog();
		oCatalog = Factory.getRegister().getOrderCatalog();
		prCatalog = Factory.getRegister().getPrescriptionCatalog();
		rCatalog = Factory.getRegister().getReportCatalog();
		inventory = Factory.getRegister().getInventory();
		sales = Factory.getRegister().getSaleCatalog();
		manager = Factory.getRegister().getManager();
		
		conn = Factory.getRegister().getConnection();
		
		db = Factory.getRegister().getDBHandler();
	}
	
	@FXML
	public void handleButtons(ActionEvent mouseEvent) throws SQLException {
		
		if (mouseEvent.getSource() == patientBtn) {
			
			System.out.println("Patient Button Pressed!\n");
			setView("PatientLogin.fxml");
			
		}
		
		else if (mouseEvent.getSource() == pharmacistBtn) {
			
			System.out.println("Pharmacist Button Pressed!\n");
			setView("PharmacistEnterLogin.fxml");

		}

		else if (mouseEvent.getSource() == physicianBtn) {

			System.out.println("Physician Button Pressed!\n");
			setView("HospitalLogin.fxml");
		}
		
		else if (mouseEvent.getSource() == managerBtn) {
			
			System.out.println("Manager Button Pressed!\n");
			setView("ManagerLogin.fxml");
		}
		
		else if (mouseEvent.getSource() == patientLoginBackBtn) {
			
			System.out.println("Patient Login Back Button Pressed!\n");
			setView("Home.fxml");
		}
		
		else if (mouseEvent.getSource() == loginBtn) {
			
			System.out.println("Login Button Pressed!\n");
			setView("PatientEnterLogin.fxml");
		}
		
		else if (mouseEvent.getSource() == registerBtn) {
			
			System.out.println("Register Button Pressed!\n");
			setView("PatientRegister.fxml");

		}
		
		else if (mouseEvent.getSource() == registerAccBtn) {
			
			System.out.println("Register Account Button Pressed!\n");
			makeNewPatient(patientUser.getText(), patientPass.getText());
			setView("PatientEnterDetails.fxml");
			alert("Account successfully created for " + patientUser.getText());

		}
		
		else if (mouseEvent.getSource() == enterPInfoBtn) {
			
			String bLetter = "", bSign = "";
			
			if (patientbLA.isSelected()) {
				
				bLetter = patientbLA.getText();
			}
			
			else if (patientbLB.isSelected()) {
				
				bLetter = patientbLB.getText();
			}
			
			else if (patientbLAB.isSelected()) {
				
				bLetter = patientbLAB.getText();
			}
			
			else if (patientbLO.isSelected()) {
				
				bLetter = patientbLO.getText();
			}
			
			if (patientbSP.isSelected()) {
				
				bSign = patientbSP.getText();
			}
			
			else if (patientbSN.isSelected()) {
				
				bSign = patientbSN.getText();
			}
			
			System.out.println("Enter Patient Info Button Pressed!\n");
			String bType = bLetter + " " + bSign; 
			enterInformation(patientPhone.getText(), bType, patientHist.getText());
			
			Patient p = pCatalog.getCurrentPatient();
			db.insertPatient(p.getUsername(), p.getPassword(), p.getID(), p.getPhoneNo(), p.getBloodType(), p.getMedHistory(), conn);
			
			setView("PatientAccount.fxml");
		}
		
		else if (mouseEvent.getSource() == loginAccBtn) {
			
			if (patientUserLogin != null) {	//login verification for patient
				
				if (pCatalog.validatePatient(patientUserLogin.getText(), patientPassLogin.getText())) {
					
					setView("PatientAccount.fxml");
				}
				
				else {
					
					loginErrorTxt.setVisible(true);
				}	
			}
			
			else if (hospitalUserLogin != null) {	//login verification for hospital
				
				if (hCatalog.validateHospital(hospitalUserLogin.getText(), hospitalPassLogin.getText())) {
					
					setView("HospitalAccount.fxml");
				}
				
				else {
					
					loginErrorTxt.setVisible(true);
				}	
			}
			
			else if (pharmacistUserLogin != null) {	//login verification for pharmacist
				
				if (phCatalog.validatePharmacist(pharmacistUserLogin.getText(), pharmacistPassLogin.getText())) {
					
					setView("PharmacistAccount.fxml");
				}
				
				else {
					
					loginErrorTxt.setVisible(true);
				}	
			}
		}
		
		else if (mouseEvent.getSource() == requestDeliveryBtn) {
			
			setView("RequestDelivery.fxml");
		}
		
		else if (mouseEvent.getSource() == enterIDForDeliveryBtn) {
			
			double total = 0.0;
			String serviceSelection = "";
			
			if (serviceType1.isSelected()) {
				
				serviceSelection = serviceType1.getText();
			}
			
			else if (serviceType2.isSelected()) {
				
				serviceSelection = serviceType2.getText();
			}
			
			else if (serviceType3.isSelected()) {
				
				serviceSelection = serviceType3.getText();
			}
			
			else if (serviceType4.isSelected()) {
				
				serviceSelection = serviceType4.getText();
			}
			
			Prescription p = prCatalog.findPrescription(deliveryPrescriptionID.getText());
			
			p.setHandled(true);
			
			ArrayList<PrescribedMedicine> pms = p.getPrescribedMedicines();
			
			for (int i = 0; i < pms.size(); i++) {
				
				PrescribedMedicine pm = pms.get(i);
				
				total += inventory.deliverMedicine(pm.getMedicineName(), pm.getQuantity());
			}
			
			alert("Prescription#" + p.getPrescriptionID() + " is on its way through " + serviceSelection + "'s service. Total = Rs. " + Double.toString(total));
			
			setView("PatientAccount.fxml");
		}
		
		else if (mouseEvent.getSource() == registerManagerBtn) {
			
			setView("ManagerRegister.fxml");
		}
		
		else if (mouseEvent.getSource() == loginManagerBtn) {
			
			setView("ManagerEnterLogin.fxml");
		}
		
		else if (mouseEvent.getSource() == loginManagerAccBtn) {
			
			if (manager.validate(managerUserLogin.getText(), managerPassLogin.getText())) {
				
				setView("ManagerAccount.fxml");
			}
			
			else {
				
				managerloginErrorTxt.setVisible(true);
			}
		}
		
		else if (mouseEvent.getSource() == registerManagerAccBtn) {
			
			manager.setValues(managerName.getText(), managerCNIC.getText(), managerUser.getText(), managerPass.getText(), managerPhone.getText());
			setView("ManagerAccount.fxml");
			alert("Account successfully created for Manager " + managerName.getText());
		}
		
		else if (mouseEvent.getSource() == hospitalLoginBtn) {
			
			setView("HospitalEnterLogin.fxml");
		}
		
		else if (mouseEvent.getSource() == hospitalRegisterBtn) {
			
			setView("HospitalRegister.fxml");
		}
		
		else if (mouseEvent.getSource() == registerHospitalBtn) {
			
			registerHospital(hospitalName.getText(), hospitalAddress.getText(), hospitalUser.getText(), hospitalPass.getText());
			Hospital h = hCatalog.getCurrentHospital();
			db.insertHospital(h.getName(), h.getAddress(), h.getUsername(), h.getPassword(), h.getID(), h.getVerification(), conn);
			setView("HospitalAccount.fxml");
			alert("Hospital details noted. Manager verficiation pending.");
		}
		
		else if (mouseEvent.getSource() == makePrescriptionBtn) {
			
			if (hCatalog.checkHospitalVerification(hospitalid.getText())) {
				
				System.out.println("The hospital selected is verified.\n");
				
				setView("MakePrescription.fxml");
			}
			
			else {
				
				hospitalVerificationErrTxt.setVisible(true);
			}
		}
		
		else if (mouseEvent.getSource() == searchPatientBtn) {
			
			if (pCatalog.findPatient(patientSearchPrompt.getText())) {
				
				setView("DoctorName.fxml");
			}
			
			else {
				
				noPatientFoundTxt.setVisible(true);
			}
		}
		
		else if (mouseEvent.getSource() == enterDoctorNameBtn) {

			prCatalog.makePrescription(doctorNamePrompt.getText());
			setView("AddPresciptionMedicine.fxml");
		}
		
		else if (mouseEvent.getSource() == addPrescriptionMedicineBtn) {
			
			Prescription p = prCatalog.getCurrentPrescription();
			p.addMedicine(prescriptionMedicineName.getText(), Integer.parseInt(prescriptionMedicineQty.getText()), prescriptionMedicineComment.getText());
		}
		
		else if (mouseEvent.getSource() == confirmPrescriptionBtn) {
			
			Prescription pr = prCatalog.getCurrentPrescription();
			Patient p = pCatalog.getCurrentPatient();
			p.addPrescription(pr);
			
			setView("ConfirmedPrescription.fxml");
		}
		
		else if (mouseEvent.getSource() == verifyHospitalBtn) {
			
			setView("VerifyHospitals.fxml");
		}
		
		else if (mouseEvent.getSource() == verifyHospitalTCBtn) {
			
			hospitalsTableView.getSelectionModel().getSelectedItem().setVerification(true);
			db.updateHospital(hospitalsTableView.getSelectionModel().getSelectedItem().getUsername(), conn);
			hospitalsTableView.refresh();
		}
		
		else if (mouseEvent.getSource() == approveOrderBtn) {
			
			setView("ApproveOrders.fxml");
		}
		
		else if (mouseEvent.getSource() == approveOrderTCBtn) {
			
			ordersTableView.getSelectionModel().getSelectedItem().setApproval(true);
			Medicine m = ordersTableView.getSelectionModel().getSelectedItem().getMedicine();
			db.insertInventory(m.getName(), m.getBrand(), m.getStockQty(), m.getPrice(), m.getArrivalDate(), m.getExpiryDate(), conn);
			inventory.fetchOrder(ordersTableView.getSelectionModel().getSelectedItem().getMedicine());
			ordersTableView.refresh();
		}
		
		else if (mouseEvent.getSource() == generateReportBtn) {
			
			setView("ReportType.fxml");
		}
		
		else if (mouseEvent.getSource() == prescriptionsReportBtn) {
			
			LocalDate startDate = LocalDate.of(Integer.parseInt(reportStartDateY.getText()), Integer.parseInt(reportStartDateM.getText()), Integer.parseInt(reportStartDateD.getText()));
			LocalDate endDate = LocalDate.of(Integer.parseInt(reportEndDateY.getText()), Integer.parseInt(reportEndDateM.getText()), Integer.parseInt(reportEndDateD.getText()));
			
			ArrayList<Prescription> reportPrescriptions = new ArrayList<Prescription>();
			ArrayList<Prescription> allPrescriptions = prCatalog.getPrescriptions();
			
			for (int i = 0; i < allPrescriptions.size(); i++) {
				
				Prescription pr = allPrescriptions.get(i);
				
				if ((pr.getPrescriptionDate().isAfter(startDate)) && (pr.getPrescriptionDate().isBefore(endDate))) {
					
					reportPrescriptions.add(pr);
				}
			}
			
			rCatalog.makeNewReport(null, reportPrescriptions);
			
			setView("PrescriptionReport.fxml");
		}
		
		else if (mouseEvent.getSource() == inventoryReportBtn) {
			
			LocalDate startDate = LocalDate.of(Integer.parseInt(reportStartDateY.getText()), Integer.parseInt(reportStartDateM.getText()), Integer.parseInt(reportStartDateD.getText()));
			LocalDate endDate = LocalDate.of(Integer.parseInt(reportEndDateY.getText()), Integer.parseInt(reportEndDateM.getText()), Integer.parseInt(reportEndDateD.getText()));
			
			ObservableList<Medicine> reportMedicines = FXCollections.observableArrayList();
			ObservableList<Medicine> allMedicines = inventory.getMedicines();
			
			for (int i = 0; i < allMedicines.size(); i++) {
				
				Medicine m = allMedicines.get(i);
				
				if ((m.getArrivalDate().isAfter(startDate)) && (m.getArrivalDate().isBefore(endDate))) {
					
					reportMedicines.add(m);
				}
			}
			
			rCatalog.makeNewReport(reportMedicines, null);
			
			setView("MedicineReport.fxml");
		}
		
		else if (mouseEvent.getSource() == manageInventoryBtn) {
			
			setView("ManageInventory.fxml");
		}

		else if (mouseEvent.getSource() == handlePrescriptionBtn) {
			
			setView("HandlePrescription.fxml");
		}
		
		else if (mouseEvent.getSource() == enterPatientForHandleBtn) {
			
			if (pCatalog.findPatient(patientSearchPrompt.getText())) {
				
				setView("SelectPrescriptionHandle.fxml");
			}
			
			else {
				
				noPatientFoundTxt.setVisible(true);
			}
		}
		
		else if (mouseEvent.getSource() == enterIDForHandleBtn) {
			
			Prescription p = prCatalog.findPrescription(deliveryPrescriptionID.getText());
			
			p.setHandled(true);
			
			alert("Prescription#" + p.getPrescriptionID() + "'s handle status has been successfully updated.");
			
			setView("Home.fxml");
		}
	
		else if (mouseEvent.getSource() == processSaleBtn) {

			sales.makeNewSale();
			setView("SaleItems.fxml");
		}
		
		else if (mouseEvent.getSource() == addSaleItemBtn) {
			
			if (inventory.checkAvailability(medicineNameSaleItem.getText(), Integer.parseInt(medicineQtySaleItem.getText()))) {
				
				stockInsufficientTxt.setVisible(false);
				double unitPrice = inventory.sellMedicine(medicineNameSaleItem.getText(), Integer.parseInt(medicineQtySaleItem.getText()));
				sales.addSoldMedicine(medicineNameSaleItem.getText(), Integer.parseInt(medicineQtySaleItem.getText()), unitPrice);
			}
			
			else {
				
				stockInsufficientTxt.setVisible(true);
			}
		}
		
		else if (mouseEvent.getSource() == proceedSaleBtn) {
			
			setView("SaleDetails.fxml");
		}
		
		else if (mouseEvent.getSource() == checkoutSaleBtn) {
			
			Sale s = sales.getCurrentSale();
			s.checkoutDetails(saleCustomerName.getText(), saleCustomerCNIC.getText(), Double.parseDouble(salePaidAmount.getText()));
			setView("Checkout.fxml");
		}
		
		else if (mouseEvent.getSource() == addMedicineBtn) {
			
			setView("AddMedicine.fxml");
		}
		
		else if (mouseEvent.getSource() == requestOrderBtn) {
			
			String orderID = oCatalog.makeNewOrder(orderMedicineName.getText(), orderMedicineBrand.getText(), orderMedicineQty.getText(),
					orderMedicinePrice.getText(), orderMedicineArrivalDateY.getText(), orderMedicineArrivalDateM.getText(),
					orderMedicineArrivalDateD.getText(), orderMedicineExpiryDateY.getText(), orderMedicineExpiryDateM.getText(),
					orderMedicineExpiryDateD.getText());
			Order oTemp = oCatalog.getCurrentOrder();
			db.insertOrder(oTemp.getOrderID(), oTemp.getApproval(), oTemp.getName(), oTemp.getBrand(), oTemp.getQty(), (int)oTemp.getPrice(), oTemp.getArrivalDate(), oTemp.getExpiryDate(), manager.getUsername(), conn);
			setView("PharmacistAccount.fxml");
			alert("Order #" + orderID + " requested. Waiting for manager approval.");
		}
		
		else if (mouseEvent.getSource() == findMedicineBtn) {
			
			setView("FindMedicine.fxml");
		}
		
		else if (mouseEvent.getSource() == searchMedicineBtn) {
			
			Medicine m = inventory.searchMedicine(medicineSearchPrompt.getText());
			
			if (m == null) {
				
				noMedicineFoundTxt.setVisible(true);
			}
			
			else {
				
				medicineFoundDetails.setVisible(true);
				noMedicineFoundTxt.setVisible(false);
				medicineFoundName.setText(m.getName());
				medicineFoundBrand.setText(m.getBrand());
				medicineFoundStockQty.setText(Integer.toString(m.getStockQty()));
				medicineFoundPrice.setText(Double.toString(m.getPrice()));
				medicineFoundArrivalDate.setText(m.getArrivalDate().toString());
				medicineFoundExpiryDate.setText(m.getExpiryDate().toString());
				
			}
		}
		
		else if (mouseEvent.getSource() == updateMedicineBtn) {
			
			setView("UpdateMedicine.fxml");
		}
		
		else if (mouseEvent.getSource() == updateMedicineTCBtn) {
			
			Medicine temp = updateMedicineTableView.getSelectionModel().getSelectedItem();
			temp.setStockQty(Integer.parseInt(newStockQty.getText()));
			temp.setPrice(Double.parseDouble(newUnitPrice.getText()));
			updateMedicineTableView.refresh();
		}
		
		else if (mouseEvent.getSource() == removeMedicineBtn) {
			
			setView("RemoveMedicine.fxml");
		}
		
		else if (mouseEvent.getSource() == deleteMedicineTCBtn) {
			
			deleteMedicineTableView.getItems().remove(deleteMedicineTableView.getSelectionModel().getSelectedItem());
			//updateMedicineTableView.refresh();
		}
		
		else if (mouseEvent.getSource() == logoutBtn) {
			
			setView("Home.fxml");
		}
	}
	
	public void makeNewPatient(String pUser, String pPass) {
		
		pCatalog.makeNewPatient(pUser, pPass);
	}
	
	public void enterInformation(String phone, String btype, String medhist) {
		
		alert("Account information entered for Patient ID " + pCatalog.enterInformation(phone, btype, medhist));
	}
	
	public void registerHospital(String n, String add, String user, String pass) {
		
		hCatalog.registerHospital(n, add, user, pass);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (loginManagerBtn != null) {	//ManagerLogin view is active
			
			if (manager.getName() == null) {
				
				loginManagerBtn.setVisible(false);
				registerManagerBtn.setVisible(true);
			}
			
			else {
				
				registerManagerBtn.setVisible(false);
				loginManagerBtn.setVisible(true);
			}
		}
		
		if (username != null) {	//PatientAccount view is active
			
			Patient p = pCatalog.getCurrentPatient();
			username.setText(p.getUsername());
			id.setText(p.getID());
			phonenumber.setText(p.getPhoneNo());
			bloodtype.setText(p.getBloodType());
			medicalhistory.setText(p.getMedHistory());
		}
		
		if (loginErrorTxt != null) {	//PatientEnterLogin view is active
			
			loginErrorTxt.setVisible(false);
		}
		
		if (managerloginErrorTxt != null) {	//ManagerEnterLogin view is active
			
			managerloginErrorTxt.setVisible(false);
		}
		
		if (hospitalname != null) {	//HospitalAccount view is active
			
			Hospital h = hCatalog.getCurrentHospital();
			hospitalname.setText(h.getName());
			hospitalid.setText(h.getID());
			hospitalverification.setText(Boolean.toString(h.getVerification()));
			
			hospitalVerificationErrTxt.setVisible(false);
		}
		
		if (prescriptionDetails != null) {	//ConfirmedPrescription view is active
			
			Prescription p = prCatalog.getCurrentPrescription();
			prescriptionID.setText(p.getPrescriptionID());
			prescriptionDoctorName.setText(p.getDoctorName());
			prescriptionHandledStatus.setText(Boolean.toString(p.getHandled()));
			prescriptionDate.setText(p.getPrescriptionDate().toString());
			
			ArrayList<PrescribedMedicine> pms = p.getPrescribedMedicines();
			
			for (int i = 0; i < pms.size(); i++) {
				
				PrescribedMedicine pm = pms.get(i);
				
				Text n = new Text(pm.getMedicineName());
				n.setId("selectRolesRegularTxt");
				n.setFill(Color.WHITE);
				
				Text q = new Text(Integer.toString(pm.getQuantity()));
				q.setId("selectRolesRegularTxt");
				q.setFill(Color.WHITE);
				
				Text c = new Text(pm.getComment());
				c.setId("selectRolesRegularTxt");
				c.setFill(Color.WHITE);
				
				HBox medDetails = new HBox(n, q, c);
				medDetails.setSpacing(40);
				
				PrescriptionAppendVBox.getChildren().add(medDetails);
			}
		}
		
		if (prescriptionReportIDTxt != null) {	//PrescriptionReport view is active
			
			Report r = rCatalog.getCurrentReport();
			
			prescriptionReportIDTxt.setText(r.getID());
			
			ArrayList<Prescription> prs = r.getPrescriptions();
			
			for (int i = 0; i < prs.size(); i++) {
				
				Prescription pr = prs.get(i);
				
				Text presIDLabel = new Text("PrescriptionID: ");
				presIDLabel.setId("selectRolesTxt");
				presIDLabel.setFill(Color.WHITE);
				
				Text presID = new Text(pr.getPrescriptionID());
				presID.setId("selectRolesRegularTxt");
				presID.setFill(Color.WHITE);
				
				HBox id = new HBox(presIDLabel, presID);
				
				Text doctorNameLabel = new Text("Doctor Name: ");
				doctorNameLabel.setId("selectRolesTxt");
				doctorNameLabel.setFill(Color.WHITE);
				
				Text doctorName = new Text(pr.getDoctorName());
				doctorName.setId("selectRolesRegularTxt");
				doctorName.setFill(Color.WHITE);
				
				HBox doctor = new HBox(doctorNameLabel, doctorName);
				
				Text handledStatusLabel = new Text("Handled Status: ");
				handledStatusLabel.setId("selectRolesTxt");
				handledStatusLabel.setFill(Color.WHITE);
				
				Text handledStatus = new Text(Boolean.toString(pr.getHandled()));
				handledStatus.setId("selectRolesRegularTxt");
				handledStatus.setFill(Color.WHITE);
				
				HBox handled = new HBox(handledStatusLabel, handledStatus);
				
				Text presDateLabel = new Text("Prescription Date: ");
				presDateLabel.setId("selectRolesTxt");
				presDateLabel.setFill(Color.WHITE);
				
				Text presDate = new Text(pr.getPrescriptionDate().toString());
				presDate.setId("selectRolesRegularTxt");
				presDate.setFill(Color.WHITE);
				
				HBox date = new HBox(doctorNameLabel, doctorName);
				
				VBox singularInfo = new VBox(id, doctor, handled, date);
				
				VBox allMeds = new VBox();
				
				ArrayList<PrescribedMedicine> pms = pr.getPrescribedMedicines();
				
				for (int j = 0; j < pms.size(); j++) {
					
					PrescribedMedicine pm = pms.get(j);
					
					Text n = new Text(pm.getMedicineName());
					n.setId("selectRolesRegularTxt");
					n.setFill(Color.WHITE);
					
					Text q = new Text(Integer.toString(pm.getQuantity()));
					q.setId("selectRolesRegularTxt");
					q.setFill(Color.WHITE);
					
					Text c = new Text(pm.getComment());
					c.setId("selectRolesRegularTxt");
					c.setFill(Color.WHITE);
					
					HBox medDetails = new HBox(n, q, c);
					medDetails.setSpacing(40);
					
					allMeds.getChildren().add(medDetails);
				}
				
				PrescriptionReportVBox.getChildren().add(singularInfo);
				PrescriptionReportVBox.getChildren().add(allMeds);
			}
		}
		
		if (prescriptionDelivery != null) {	//RequestDelivery or SelectPrescriptionHandle view is active
			
			Patient p = pCatalog.getCurrentPatient();
			ArrayList<Prescription> prs = p.getPatientPrescriptions();
			
			for (int i = 0; i < prs.size(); i++) {
				
				Prescription pr = prs.get(i);
				
				if (!pr.getHandled()) {
					
					VBox completePrescription = new VBox();
					
					Text presTxt = new Text("Prescription#");
					presTxt.setId("selectRolesTxt");
					presTxt.setFill(Color.WHITE);
					
					Text id = new Text(pr.getPrescriptionID());
					id.setId("selectRolesTxt");
					id.setFill(Color.WHITE);
					
					HBox title = new HBox(presTxt, id);
					
					completePrescription.getChildren().add(title);
					
					ArrayList<PrescribedMedicine> pms = pr.getPrescribedMedicines();
					
					for (int j = 0; j < pms.size(); j++) {
						
						PrescribedMedicine pm = pms.get(j);
						
						Text n = new Text(pm.getMedicineName());
						n.setId("selectRolesRegularTxt");
						n.setFill(Color.WHITE);
						
						Text q = new Text(Integer.toString(pm.getQuantity()));
						q.setId("selectRolesRegularTxt");
						q.setFill(Color.WHITE);
						
						Text c = new Text(pm.getComment());
						c.setId("selectRolesRegularTxt");
						c.setFill(Color.WHITE);
						
						HBox medDetails = new HBox(n, q, c);
						medDetails.setSpacing(40);
						
						completePrescription.getChildren().add(medDetails);
					}
					
					DeliveryPrescriptionAppendVBox.getChildren().add(completePrescription);	
				}
			}
		}

		if (pharmacistname != null) {	//PharmacistAccount view is active
			
			Pharmacist ph = phCatalog.getCurrentPharmacist();
			pharmacistname.setText(ph.getFullName());
			pharmacistid.setText(ph.getID());
		}
		
		if (mgrname != null) {	//ManagerAccount view is active
			
			mgrname.setText(manager.getName());
			mgrid.setText(manager.getID());
		}
		
		if (hospitalsTableView != null) {	//VerifyHospitals view is active
			
			hospitalNameTC.setCellValueFactory(new PropertyValueFactory<Hospital, String>("name"));
			hospitalAddressTC.setCellValueFactory(new PropertyValueFactory<Hospital, String>("address"));
			hospitalVerificationTC.setCellValueFactory(new PropertyValueFactory<Hospital, Boolean>("verification"));
			
			hospitalsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	//adds scrolling
			hospitalsTableView.setEditable(true);
			
			hospitalsTableView.setItems(hCatalog.getHospitals());
		}
		
		if (ordersTableView != null) {	//ApproveOrders view is active
			
			orderIDTC.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
			orderMedicineNameTC.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
			orderMedicineBrandTC.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));
			orderMedicineQtyTC.setCellValueFactory(new PropertyValueFactory<Order, Integer>("qty"));
			orderMedicinePriceTC.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
			orderMedicineArrivalDateTC.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("arrivalDate"));
			orderMedicineExpiryDateTC.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("expiryDate"));
			orderApprovalTC.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("approval"));
			
			ordersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	//adds scrolling
			ordersTableView.setEditable(true);
			
			ordersTableView.setItems(oCatalog.getOrders());
		}
		
		if (medicineReportIDTxt != null) {	//MedicineReport view is active
			
			Report r = rCatalog.getCurrentReport();
			
			medicineReportIDTxt.setText(r.getID());
			
			medicineReportNameTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
			medicineReportBrandTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("brand"));
			medicineReportQtyTC.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("stockQty"));
			medicineReportPriceTC.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
			medicineReportArrivalDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("arrivalDate"));
			medicineReportExpiryDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("expiryDate"));
			
			medicineReportTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	//adds scrolling
			medicineReportTableView.setEditable(true);
			
			medicineReportTableView.setItems(r.getMedicines());	
		}
		if (updateMedicineTableView != null) {	//UpdateMedicine view is active
			
			updateMedicineNameTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
			updateMedicineBrandTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("brand"));
			updateMedicineQtyTC.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("stockQty"));
			updateMedicinePriceTC.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
			updateMedicineArrivalDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("arrivalDate"));
			updateMedicineExpiryDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("expiryDate"));
			
			updateMedicineTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	//adds scrolling
			updateMedicineTableView.setEditable(true);
			
			updateMedicineTableView.setItems(inventory.getMedicines());
		}
		
		if (deleteMedicineTableView != null) {	//DeleteMedicine view is active
			
			deleteMedicineNameTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
			deleteMedicineBrandTC.setCellValueFactory(new PropertyValueFactory<Medicine, String>("brand"));
			deleteMedicineQtyTC.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("stockQty"));
			deleteMedicinePriceTC.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
			deleteMedicineArrivalDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("arrivalDate"));
			deleteMedicineExpiryDateTC.setCellValueFactory(new PropertyValueFactory<Medicine, LocalDate>("expiryDate"));
			
			deleteMedicineTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	//adds scrolling
			deleteMedicineTableView.setEditable(true);
			
			deleteMedicineTableView.setItems(inventory.getMedicines());
		}
		
		if (noMedicineFoundTxt != null) {	//FindMedicine view is active

			noMedicineFoundTxt.setVisible(false);
			medicineFoundDetails.setVisible(false);
		}
		
		if (stockInsufficientTxt != null) {	//SaleItems view is active
			
			stockInsufficientTxt.setVisible(false);
		}
		
		if (saleTotalTxt != null) {	//SaleDetails view is active
			
			Sale s = sales.getCurrentSale();
			saleTotalTxt.setText(Double.toString(s.getTotal()));
		}
		
		if (receiptCheckout != null) {	//Checkout view is active
			
			Sale s = sales.getCurrentSale();
			checkoutSaleID.setText(s.getSaleID());
			checkoutCustomerName.setText(s.getCustomerName());
			checkoutCustomerCNIC.setText(s.getCustomerCNIC());
			checkoutTotal.setText(Double.toString(s.getTotal()));
			checkoutPaidAmount.setText(Double.toString(s.getPaidAmount()));
			checkoutChange.setText(Double.toString(s.getChange()));
			
			ArrayList<PrescribedMedicine> sms = s.getSoldMedicines();
			
			for (int i = 0; i < sms.size(); i++) {
				
				PrescribedMedicine sm = sms.get(i);
				
				Text name = new Text(sm.getMedicineName());
				name.setId("selectRolesRegularTxt");
				name.setFill(Color.WHITE);
				
				Text qty = new Text(Integer.toString(sm.getQuantity()));
				qty.setId("selectRolesRegularTxt");
				qty.setFill(Color.WHITE);
				
				HBox soldMedicineHBox = new HBox(name, qty);
				soldMedicineHBox.setId("HBoxStuff");
				
				checkoutItemsVBox.getChildren().add(soldMedicineHBox);
			}
		}
		
		if (noPatientFoundTxt != null) {	//MakePrescription view is active
			
			noPatientFoundTxt.setVisible(false);
		}
	}
	
	public void setView(String fxmlView) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/userInterface/" + fxmlView));
			Stage stage = (Stage) idToGetStage.getScene().getWindow();
			Scene scene = new Scene(root, 1042, 673);
			scene.getStylesheets().add(getClass().getResource("/userInterface/application.css").toExternalForm());
			stage.setScene(scene);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void alert(String message) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.show();
	}
}


