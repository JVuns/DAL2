package application;

import java.util.ArrayList;

import application.Individual.HaulingJob;
import application.Misc.ListData;
import application.Misc.ObjectReader;
import application.Misc.ObjectWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	 * Injection for TextFields
	 */
	
	@FXML private TextField tonageField;
	@FXML private TextField routeField;
	@FXML private TextField driverField;
	
	@FXML private TextField nameField;
	@FXML private TextField idField;
	@FXML private TextField routesField;
	
	
	
	/*
	 * Injection for CC
	 */
	
	@FXML private ComboBox<String> fileNameCC;
	@FXML private ComboBox<String> modeNameCC;
	
	@FXML
	private void ComboOption(ActionEvent event) {
		Pane[] panes = {entryPane, driverPane, vehiclePane};
	
		for(int i = 0; i<panes.length; i++) {
			if(panes[i].isVisible()) {
				panes[i].setVisible(false);
				dataTable.setVisible(false);
			}
			if(modeNameCC.getValue() == "Entry") 
				entryPane.setVisible(true);
			if(modeNameCC.getValue() == "Driver") 
				driverPane.setVisible(true);
			if(modeNameCC.getValue() == "Vehicle") {
				vehiclePane.setVisible(true);
				System.out.print("PH");
			}
		}
	}
	ObservableList<String> options = FXCollections.
			observableArrayList(ListData.getDirData());
	@FXML
	public void initialize() {
		ObservableList<String> mode = FXCollections.observableArrayList(
				"Entry",
				"Driver",
				"Vehicle");
		modeNameCC.setItems(mode);
		fileNameCC.setItems(options);
		fileNameCC.setEditable(true);
	}
	
	@FXML
	private void ComboAction(ActionEvent event) throws Exception {
		options = FXCollections.
				observableArrayList(ListData.getDirData());
		fileNameCC.setItems(options);
		System.out.println(fileNameCC.getValue());
		dataSet.clear();
		if(!fileNameCC.getValue().isBlank())
			populate(dataSet, "load");
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
		ObservableList<Object> a = dataTable.getItems();
		System.out.println(((HaulingJob) a.get(0)).getRoute());
		ObjectWriter.saveJob(new ArrayList<Object>(a), fileNameCC.getValue(), "Vehicle");
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
		
		System.out.println("new data added");
		dataTable.setItems(dataSet);
	}
}
