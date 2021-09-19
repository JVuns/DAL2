package application;

import java.util.ArrayList;
import application.Individual.HaulingJob;
import application.Individual.TruckUnit;
import application.Misc.ListData;
import application.Misc.ObjectReader;
import application.Misc.ObjectWriter;
import application.People.Driver;
import application.Vehicle.Truck;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class DataController {
	
	/*
	 * Grouping pane
	 */
	@FXML private Pane entryPane;
	@FXML private Pane driverPane;
	@FXML private Pane vehiclePane;
	
	
	/*
	 * Injection for entryPane
	 */
	
	@FXML private TableView<Object> dataTable;
	@FXML private TableColumn<HaulingJob, String> job;
	@FXML private TableColumn<HaulingJob, String> tonage;
	@FXML private TableColumn<HaulingJob, String> route;
	@FXML private TableColumn<HaulingJob, String> driver;
	@FXML private TableColumn<HaulingJob, String> date;
	@FXML private TableColumn<HaulingJob, String> vehicle;
	@FXML private TableColumn<HaulingJob, String> locationCol;
	final ObservableList<Object> dataSet = FXCollections.observableArrayList();
	
	/*
	 * Injection for vehiclePane 
	 */
	@FXML private TableView<Object> vehicleTable;
	@FXML private TableColumn<Truck, String> model;
	@FXML private TableColumn<Truck, String> name;
	@FXML private TableColumn<Truck, String> spec;
	@FXML private TableColumn<Truck, String> status;
	@FXML private TableColumn<Truck, String> year;
	final ObservableList<Object> vehicleSet = FXCollections.observableArrayList();
	
	/*
	 * Injection for driverPane
	 */
	
	@FXML private TableView<Object> driverTable;
	@FXML private TableColumn<Driver, String> driverNameCol;
	@FXML private TableColumn<Driver, String> driverStatusCol;
	
	final ObservableList<Object> driverSet = FXCollections.observableArrayList();
	
	/*
	 * Injection for TextFields
	 */
	
	@FXML private TextField tonageField;
	@FXML private TextField routeField;
	@FXML private TextField driverField;
	@FXML private TextField locationField;
	
	@FXML private TextField nameField;
	@FXML private TextField idField;
	@FXML private TextField routesField;
	
	@FXML private TextField vehicleModelField;
	@FXML private TextField vehicleNameField;
	@FXML private TextField vehicleSpecField;
	@FXML private TextField vehicleYearField;
	
	@FXML private TextField driverName;
	@FXML private TextField driverStatus;

	/*
	 * Injection for CC
	 */

	@FXML private ComboBox<String> fileNameCC;
	@FXML private ComboBox<String> modeNameCC;
	@FXML private ComboBox<String> vehicleCC;
	@FXML private ComboBox<String> driverCC;
	ObservableList<String> mode = FXCollections.observableArrayList(
			"Log",
			"Driver",
			"Vehicle");

	/*
	 * Panes option
	 */
	
	@FXML
	private void ComboOption(ActionEvent event) {
		Pane[] panes = {entryPane, driverPane, vehiclePane}; 

		for(int i = 0; i<panes.length; i++) { //setting visibility of panes and tableview by evaluating value of combobox 
			if(panes[i].isVisible()) {
				panes[i].setVisible(false);
				dataTable.setVisible(false);
				vehicleTable.setVisible(false);
				driverTable.setVisible(false);
			}
			if(modeNameCC.getValue() == "Entry") {
				entryPane.setVisible(true);
				dataTable.setVisible(true);
			}
			if(modeNameCC.getValue() == "Driver") {
				driverPane.setVisible(true);
				driverTable.setVisible(true);
			}
			if(modeNameCC.getValue() == "Vehicle") {
				vehiclePane.setVisible(true);
				vehicleTable.setVisible(true);
			}
		}
	}
	ObservableList<String> options = FXCollections.				//Observable lists for combobox options
			observableArrayList(ListData.getDirData("Data"));
	ObservableList<String> vehicleOptions = FXCollections.
			observableArrayList();
	ObservableList<String> driverOptions = FXCollections.
			observableArrayList();
	
	@FXML
	public void initialize() throws Exception {
		
		EventHandler<KeyEvent> deleteKey = key -> { //Event handler for key bind
			if(key.getCode() == KeyCode.DELETE) { //Condition to make tables delete their respective
				if(vehicleTable.isVisible())	  //cell
				deleteVehicleRow();
				if(dataTable.isVisible())
				deleteDataRow();
				if(driverTable.isVisible())
				deleteDriverRow();
			}
		};
		
		vehicleTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey); 	//adding event handler so key bind is 
		dataTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey);		//only work on these specific tables		
		driverTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey);
		
		vehicleOptions.addAll(ListData.getVehicle()); //preloading vehicle data
		driverOptions.addAll(ListData.getDriver()); //preloading driver data
		
		modeNameCC.setItems(mode); //Setting all the combobox options
		fileNameCC.setItems(options);
		vehicleCC.setItems(vehicleOptions);
		driverCC.setItems(driverOptions);
		fileNameCC.setEditable(true); // Make it editable
		driverCC.setEditable(true);
		vehicleTable.setEditable(true);
		
		ArrayList<?> loadDriver = (ArrayList<?>) ObjectReader.readConst("People", "Driver"); //loading driver list to tableview
		try{
			for(int i = 0; i<loadDriver.size(); i++) {
			driverSet.add(loadDriver.get(i));
		}
		populateDriver(driverSet);
		}catch (Exception e) {
			System.out.println(e);
		}
		ArrayList<?> loadVehicle = (ArrayList<?>) ObjectReader.readConst("Vehicle", "Vehicle"); //loading vehicle list to tableview
		for(int i = 0; i<loadVehicle.size(); i++) {
			vehicleSet.add(loadVehicle.get(i));
		}
		populateVehicle(vehicleSet);
	}
	
	@FXML
	private void ComboAction() throws Exception { 
		options = FXCollections.
				observableArrayList(ListData.getDirData("Data"));

		fileNameCC.setItems(options); //updating combobox when new items were added
		
		System.out.println(fileNameCC.getValue());
		dataSet.clear();
		if(options.contains(fileNameCC.getValue())) //loading selected data set to the tableview
			populate(dataSet, "load");
	}
	
	@FXML
	private void ComboActionVehicle() throws Exception{ //updating combobox when new items were added
		vehicleOptions = FXCollections.
				observableArrayList(ListData.getVehicle());
		vehicleCC.setItems(vehicleOptions);
	}
	
	@FXML
	private void ComboActionDriver() throws Exception{
		System.out.println("TEST");
		driverOptions = FXCollections.
				observableArrayList(ListData.getDriver());
		driverCC.setItems(driverOptions);
	}
	
	/*
	 * toggle function to set the vehicle active/inactive
	 * TODO: add functionality to the vehicle status
	 */
	public void setActive() throws Exception {
		Truck vehicleSelected = (Truck) vehicleTable.getSelectionModel().getSelectedItem();
		vehicleSelected.setStatus("Active");
	}
	
	public void setInactive() throws Exception {
		Truck vehicleSelected = (Truck) vehicleTable.getSelectionModel().getSelectedItem();
		vehicleSelected.setStatus("Inactive");
	}
	
	/*
	 * save functions
	 */
	public void saveDF() throws Exception{
		ObservableList<Object> a = dataTable.getItems();
		System.out.println("Data entry saved");
		ObjectWriter.saveJob(new ArrayList<Object>(a), fileNameCC.getValue(), "Data");
	}
	
	public void saveDr() throws Exception{
		ObservableList<Object> a = driverTable.getItems();
		System.out.println("Driver data saved");
		ObjectWriter.saveJob(new ArrayList<Object>(a), "Driver", "People");
	}
	
	public void saveVe() throws Exception{
		ObservableList<Object> a = vehicleTable.getItems();
		System.out.println("Vehicle data updated");
		ObjectWriter.saveJob(new ArrayList<Object>(a), "Vehicle", "Vehicle");
	}
	
	/*
	 * Function to add individual data	
	 */
	public void addData() throws Exception {
		int vehicleIndex = 0;
		int driverIndex = 0;
		ArrayList<?> loadVehicle = (ArrayList<?>) ObjectReader.readConst("Vehicle", "Vehicle");
		ArrayList<?> loadDriver = (ArrayList<?>) ObjectReader.readConst("People", "Driver");
		HaulingJob jobObject = new HaulingJob(null, null, null); 
		jobObject.setTonage(Double.valueOf(tonageField.getText()));
		jobObject.addRoute(Integer.valueOf(routeField.getText())); //looking for object with the name selected in combobox
		for (int i = 0; i<loadDriver.size(); i++) {
			Driver item = (Driver) loadDriver.get(i);
			if(item.getName().equals(driverCC.getValue()))
				driverIndex = i;
		}
		jobObject.setDriver((Driver)loadDriver.get(driverIndex)); //looking for object with the name selected in combobox
		for (int i = 0; i<loadVehicle.size(); i++) {			  //maybe could've been easier if I use getName for combobox options
			TruckUnit item = (TruckUnit) loadVehicle.get(i);
			if(item.getName().equals(vehicleCC.getValue()))
				vehicleIndex = i;
		}
		jobObject.setVehicle((TruckUnit) loadVehicle.get(vehicleIndex));
		jobObject.setDate(application.Misc.Date.getDate());
		jobObject.setlocation(locationField.getText());
		dataSet.add(jobObject);
		System.out.println("Data added");
		if(!(fileNameCC.getValue() == null))
			if(!fileNameCC.getValue().isBlank())
			populate(dataSet, "add");
	}
	
	/*
	 * Function to add individual driver
	 */
	public void addDriver() throws Exception {
		
		Driver driver = new Driver(null, null, 0, null, null);
		driver.setName(driverName.getText());
		driver.setStatus(driverStatus.getText());
		driverSet.add(driver);
		if(!driverNameCol.getText().isBlank()
			&&!driverStatusCol.getText().isBlank()) 
			populateDriver(driverSet);
	}
	
	/*
	 * Function to add individual vehicle
	 */
	public void addVehicle() throws Exception {
		
		TruckUnit vehicle = new TruckUnit(null, null, null, null, null, 0);
		vehicle.setModel(vehicleModelField.getText());
		vehicle.setName(vehicleNameField.getText());
		vehicle.setSpec(vehicleSpecField.getText());
		vehicle.setStatus("Inactive");
		vehicle.setYear(Integer.valueOf(vehicleYearField.getText()));
		vehicleSet.add(vehicle);
		if(!vehicleModelField.getText().isBlank()
			&&!vehicleNameField.getText().isBlank()
			&&!vehicleSpecField.getText().isBlank()
			&&!vehicleYearField.getText().isBlank()) 
			populateVehicle(vehicleSet);
	}

	/*
	 * Function to populate data into TableView from save file 
	 */
	public void populate(ObservableList<Object> dataSet, String func) throws Exception {

		if(func.equals("load")) {
			ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob(fileNameCC.getValue());
			for(int i = 0; i<jobread.size(); i++) {
				dataSet.add(jobread.get(i));
			}
		}
			
		driver.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getDriver().getName()));
		tonage.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getTonage(null)));
		job.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getJob()));
		route.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getRoute()));
		date.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getDate()));
		vehicle.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getVehicle().getName()));
		locationCol.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getLocation()));
		System.out.println("new log added");
		dataTable.setItems(dataSet);
	}
	
	/*
	 * function to populate driverTable with data
	 */
	public void populateDriver(ObservableList<Object> driverSet) throws Exception{
		System.out.println("addDriveer");
		driverNameCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getName()));
		driverStatusCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getStatus()));
		driverTable.setItems(driverSet);
	}

	/*
	 * function to populate vehicleTable with data
	 */
	public void populateVehicle(ObservableList<Object> vehicleSet) throws Exception{
		System.out.println("addVehicle");
		model.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getModel()));
		name.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getName()));
		status.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getStatus()));
		spec.setCellValueFactory(cellData ->
	    new SimpleStringProperty(cellData.getValue().getSpec()));
		year.setCellValueFactory(cellData ->
	    new SimpleStringProperty(cellData.getValue().getYear("")));
		System.out.println("new vehicle added");
		vehicleTable.setItems(vehicleSet);
	}
	
	/*
	 * cell deletion function
	 */
	public void deleteVehicleRow() {
		vehicleTable.getItems().removeAll(vehicleTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}
	public void deleteDataRow() {
		dataTable.getItems().removeAll(dataTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}
	public void deleteDriverRow() {
		driverTable.getItems().removeAll(driverTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}
}
