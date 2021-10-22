package application;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import application.Individual.HaulingJob;
import application.Misc.ListData;
import application.Misc.ObjectReader;
import application.Misc.PricingGroup;
import application.People.Driver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalaryController {
	
	/*
	 * Injection 
	 */
	@FXML private ComboBox<String> pricingCC;
	@FXML private ComboBox<String> filenameCC;
	@FXML private ComboBox<String> employeeCC;
	@FXML private javafx.scene.control.DatePicker fromDate;
	@FXML private javafx.scene.control.DatePicker toDate;
	@FXML private TableView<Object> logTable;
	@FXML private TableColumn<HaulingJob, String> dateCol;
	@FXML private TableColumn<HaulingJob, String> vehicleCol;
	@FXML private TableColumn<HaulingJob, String> locationCol;
	@FXML private TableColumn<HaulingJob, String> retCol;
	@FXML private TextField salaryTextField;
	
	ObservableList<String> options = FXCollections.observableArrayList();
	ObservableList<String> pricing = FXCollections.observableArrayList();
	ObservableList<String> driver = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() throws Exception {
		options = FXCollections.
				observableArrayList(ListData.getDirData("Data"));
		filenameCC.setItems(options);
		
		pricing = FXCollections.
				observableArrayList(ListData.getDirData("Pricing"));
		pricingCC.setItems(pricing);
		
		driver = FXCollections.
				observableArrayList(ListData.getDriver());
		employeeCC.setItems(driver);
	}
	
	@FXML
	private void ComboAction() throws Exception {
		options = FXCollections.
				observableArrayList(ListData.getDirData("Data"));
		System.out.println("For file");
		filenameCC.setItems(options);
	}
	
	@FXML
	private void ComboAction2() throws Exception {
		pricing = FXCollections.
				observableArrayList(ListData.getDirData("Pricing"));
		System.out.println("For pricing");
		pricingCC.setItems(pricing);
	}
	
	@FXML
	private void ComboAction3() throws Exception {
		driver = FXCollections.
				observableArrayList(ListData.getDriver());
		System.out.println("For driver");
		employeeCC.setItems(driver);
	}
	
	@FXML
	public void actionFind() throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<HaulingJob> loadLog = (ArrayList<HaulingJob>) ObjectReader.readConst("Data", filenameCC.getValue());
		ObservableList<Object> filtered = FXCollections.observableArrayList();
		String from = null;
		String to = null;
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");	//changing date format from MM-dd-yyyy 
																					//because we don't use weird date format
		LocalDate rtDate = toDate.getValue(); //assigning value from the date picker
		LocalDate rfDate = fromDate.getValue();
		
		if (rtDate != null && rfDate != null) {
			from = formatter.format(rtDate);
			to = formatter.format(rfDate);
		}
		
		Date fDate = format.parse(from); //converting localDate to date
		Date tDate = format.parse(to);
		
		for(int i = 0; i<loadLog.size(); i++) {
//			System.out.print(loadLog.get(i).getDate());
			String a = loadLog.get(i).getDate();
			Date cDate = format.parse(a);
	
			if(!tDate.after(cDate) && !fDate.before(cDate)&&loadLog.get(i).getDriver().getName().equals(employeeCC.getValue())) {
				filtered.add(loadLog.get(i));
				System.out.println(loadLog.get(i).getLocation());
			}
		}
		populateVehicle(filtered, "something");
	}
	public void populateVehicle(ObservableList<Object> vehicleSet, String func) throws Exception{
		System.out.println("adding log");
		logTable.setItems(vehicleSet);
		dateCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getDate()));
		vehicleCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getVehicle().getModel()));
		locationCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getLocation()));
		retCol.setCellValueFactory(cellData ->
	    new SimpleStringProperty(String.valueOf(cellData.getValue().getRoute())));
		System.out.println("new log has been added");
	}

	@FXML
	public void calc(ActionEvent event) throws Exception {
		ObservableList<Object> a = logTable.getItems();
		@SuppressWarnings("unchecked")
		ArrayList<PricingGroup> pricing = (ArrayList<PricingGroup>) ObjectReader.readConst("Pricing", pricingCC.getValue());
		Driver emp = ((HaulingJob) a.get(0)).getDriver();
//		Integer.valueOf(((HaulingJob) a.get(i)).getRoute())
		for(int i = 0; i<a.size(); i++) { //math for calculating salary
			for(int j = 0; j<pricing.size(); j++) { //looping through all activity log
				for(int k = 0; k<Integer.valueOf(((HaulingJob) a.get(i)).getRoute())+1; k++){ //looping through streaks
					if(((HaulingJob) a.get(i)).getVehicle().getSpec().equals(pricing.get(j).getVehicle()) //if vehicles and location matches
							&& ((HaulingJob) a.get(i)).getLocation().equals(pricing.get(j).getLocation())) {
						if(pricing.get(j).getRoute().contains("-")){ //if it is range arg
							String rangePrice = pricing.get(j).getRoute();
							int maxRange = Integer.valueOf(rangePrice.substring(rangePrice.lastIndexOf("-")+1));
							int minRange = Integer.valueOf(rangePrice.substring(0, rangePrice.indexOf("-")));
//							System.out.println(rangePrice +" : "+ minRange);
//							System.out.println(rangePrice +" : "+  maxRange);
//							System.out.println(minRange + "<=" + Integer.valueOf(((HaulingJob) a.get(i)).getRoute()) +
//							"<=" + maxRange);
							if(minRange<=k && k<=maxRange) {
								emp.addSalary(pricing.get(j).getPrice());
							}
						}
						else if(pricing.get(j).getRoute().contains(">")){ //if it is greater arg
							String rangePrice = pricing.get(j).getRoute();
							int greaterRange = Integer.valueOf(rangePrice.substring(0, rangePrice.indexOf(">")));
							if(greaterRange<=k) {
								emp.addSalary(pricing.get(j).getPrice());
							}
						}
						else {											//pricing for exact number
							int exactNum = Integer.valueOf(pricing.get(j).getRoute());
							if(exactNum == k) {
								emp.addSalary(pricing.get(j).getPrice());
							}
						}
					}
				}
			}
		}
		System.out.println(emp.getSalary());
		NumberFormat addcomma = NumberFormat.getInstance();
		salaryTextField.setPromptText("Rp " + addcomma.format(emp.getSalary()));
		emp.setSalary(0);
		System.out.printf("Calculated %s's salary", emp.getName());
		
    }
}