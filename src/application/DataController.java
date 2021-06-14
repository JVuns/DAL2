package application;

import java.util.ArrayList;
import application.Individual.HaulingJob;
import application.Misc.ListData;
import application.Misc.ObjectReader;
import application.Misc.ObjectWriter;
import application.Misc.VehicleReader;
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
	final ObservableList<Object> dataSet = FXCollections.observableArrayList();
	
	/*
	 * Injection for driverPane 
	 */
	@FXML private TableView<Object> vehicleTable;
	@FXML private TableColumn<Truck, String> model;
	@FXML private TableColumn<Truck, String> name;
	@FXML private TableColumn<Truck, String> spec;
	@FXML private TableColumn<Truck, String> status;
	@FXML private TableColumn<Truck, String> year;
	final ObservableList<Object> vehicleSet = FXCollections.observableArrayList();
	/*
	 * Injection for TextFields
	 */
	
	@FXML private TextField tonageField;
	@FXML private TextField routeField;
	@FXML private TextField driverField;
	
	@FXML private TextField nameField;
	@FXML private TextField idField;
	@FXML private TextField routesField;
	
	@FXML private TextField vehicleModelField;
	@FXML private TextField vehicleNameField;
	@FXML private TextField vehicleSpecField;
	@FXML private TextField vehicleYearField;
	
	
	/*
	 * Injection for CC
	 */
	
	@FXML private ComboBox<String> fileNameCC;
	@FXML private ComboBox<String> modeNameCC;
	@FXML private ComboBox<String> vehicleCC;
	@FXML private ComboBox<String> driverCC;
	ObservableList<String> mode = FXCollections.observableArrayList(
			"Entry",
			"Driver",
			"Vehicle");
	
	@FXML
	private void ComboOption(ActionEvent event) {
		Pane[] panes = {entryPane, driverPane, vehiclePane};
	
		for(int i = 0; i<panes.length; i++) {
			if(panes[i].isVisible()) {
				panes[i].setVisible(false);
				dataTable.setVisible(false);
				vehicleTable.setVisible(false);
			}
			if(modeNameCC.getValue() == "Entry") {
				entryPane.setVisible(true);
				dataTable.setVisible(true);
			}
			if(modeNameCC.getValue() == "Driver") 
				driverPane.setVisible(true);
			if(modeNameCC.getValue() == "Vehicle") {
				vehiclePane.setVisible(true);
				vehicleTable.setVisible(true);
			}
		}
	}
	ObservableList<String> options = FXCollections.
			observableArrayList(ListData.getDirData());
	ObservableList<String> vehicleOptions = FXCollections.
			observableArrayList();
	
	@FXML
	public void initialize() throws Exception {
		
		EventHandler<KeyEvent> deleteKey = key -> { //Event handler for keybind
			if(key.getCode() == KeyCode.DELETE) {
				if(vehicleTable.isVisible())
				deleteVehicleRow();
				if(dataTable.isVisible())
				deleteDataRow();
			}
		};
		
		vehicleTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey); 	//adding event handler so keybind is 
		dataTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey);		//only work on specific object		
		
		vehicleOptions.addAll(ListData.getVehicle());
		
		modeNameCC.setItems(mode);
		fileNameCC.setItems(options);
		vehicleCC.setItems(vehicleOptions);
		fileNameCC.setEditable(true);
		vehicleTable.setEditable(true);
		ArrayList<?> loadVehicle = (ArrayList<?>) VehicleReader.readVehicle();

		for(int i = 0; i<loadVehicle.size(); i++) {
			vehicleSet.add(loadVehicle.get(i));
		}
		populateVehicle(vehicleSet);
	}
	
	@FXML
	private void ComboAction(ActionEvent event) throws Exception {
		options = FXCollections.
				observableArrayList(ListData.getDirData());
		vehicleOptions = FXCollections.
				observableArrayList(ListData.getDirData());
		vehicleCC.setItems(vehicleOptions);
		fileNameCC.setItems(options);
		fileNameCC.setItems(options);
		
		System.out.println(fileNameCC.getValue());
		dataSet.clear();
		if(!fileNameCC.getValue().isBlank())
			populate(dataSet, "load");
	}

	public void setActive() throws Exception {
		Truck vehicleSelected = (Truck) vehicleTable.getSelectionModel().getSelectedItem();
		vehicleSelected.setStatus("Active");
	}
	
	public void setInactive() throws Exception {
		Truck vehicleSelected = (Truck) vehicleTable.getSelectionModel().getSelectedItem();
		vehicleSelected.setStatus("Inactive");
	}
	
	/**
	 * save functions
	 * @throws Exception
	 */
	public void saveDF() throws Exception{
		ObservableList<Object> a = dataTable.getItems();
		System.out.println(((HaulingJob) a.get(0)).getRoute());
		ObjectWriter.saveJob(new ArrayList<Object>(a), fileNameCC.getValue(), "Data");
	}
	
	public void saveDr() throws Exception{
		ObservableList<Object> a = dataTable.getItems();
		System.out.println(((HaulingJob) a.get(0)).getRoute());
		ObjectWriter.saveJob(new ArrayList<Object>(a), fileNameCC.getValue(), "People");
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
		HaulingJob jobObject = new HaulingJob(null, null, null); 
		jobObject.setTonage(Double.valueOf(tonageField.getText()));
		jobObject.addRoute(Integer.valueOf(routeField.getText()));
		jobObject.setDriver(null);
		jobObject.setDate(application.Misc.Date.getDate());
		dataSet.add(jobObject);
		if(!fileNameCC.getValue().isBlank())
			populate(dataSet, "add");
	}
	
	/*
	 * Function to add individual vehicle
	 */
	public void addVehicle() throws Exception {
		
		Truck vehicle = new Truck(null, null, null, null, null, 0);
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

	/**
	 * Function to populate data into TableView from save file
	 * @param dataSet2 
	 * @throws Exception
	 */
	public void populate(ObservableList<Object> dataSet, String func) throws Exception {

		if(func.equals("load")) {
			ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob(fileNameCC.getValue());
			for(int i = 0; i<jobread.size(); i++) {
				dataSet.add(jobread.get(i));
			}
		}
			
		driver.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getRoute()));
		tonage.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getTonage(null)));
		job.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getJob()));
		route.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getRoute()));
		date.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getDate()));
		System.out.println("new log added");
		dataTable.setItems(dataSet);
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
		vehicleTable.getOnKeyPressed();
	}
	
	
	public void deleteVehicleRow() {
		vehicleTable.getItems().removeAll(vehicleTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}
	public void deleteDataRow() {
		dataTable.getItems().removeAll(dataTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}
}
