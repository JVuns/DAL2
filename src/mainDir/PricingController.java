package application;

import java.util.ArrayList;

import application.Misc.ListData;
import application.Misc.ObjectReader;
import application.Misc.ObjectWriter;
import application.Misc.PricingGroup;
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

public class PricingController {
	
	@FXML private TextField locationTF;
	@FXML private TextField routeTF;
	@FXML private TextField priceTF;

	@FXML private TableView<Object> pricingTable;
	@FXML private TableColumn<PricingGroup, String> vehicleCol;
	@FXML private TableColumn<PricingGroup, String> locationCol;
	@FXML private TableColumn<PricingGroup, String> retCol;
	@FXML private TableColumn<PricingGroup, String> priceCol;
	
	ObservableList<Object> vehicleSet = FXCollections.observableArrayList();
	ObservableList<String> vehicles = FXCollections.observableArrayList();
	
	@FXML private ComboBox<String> filenameCC;
	@FXML private ComboBox<String> vehicleCC;
	
	@FXML
	public void initialize() throws Exception {
		vehicles.setAll(ListData.getModels());
		filenameCC.setItems(options);
		vehicleCC.setItems(vehicles);
		filenameCC.setEditable(true);
		EventHandler<KeyEvent> deleteKey = key -> { //Event handler for key bind
			if(key.getCode() == KeyCode.DELETE) { 	//Condition to make tables delete their respective
				deleteData();						//cell
			}
		};
		pricingTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey);
	}
	
	public void addPricing() throws Exception {
//		ArrayList<?> loadVehicle = (ArrayList<?>) ObjectReader.readConst("Vehicle", "Vehicle");
		PricingGroup price = new PricingGroup(null, null, 0); 

		price.setVehicle(vehicleCC.getValue());
		price.setLocation(locationTF.getText());
		price.setPrice(Integer.valueOf(priceTF.getText()));
		price.setRoute(routeTF.getText());
		vehicleSet.add(price);
		if(!vehicleCC.getValue().isBlank()
				&&!locationTF.getText().isBlank()
				&&!priceTF.getText().isBlank()
				&&!routeTF.getText().isBlank()) 
				populateVehicle(vehicleSet, "add");
	}

	ObservableList<String> options = FXCollections.
			observableArrayList(ListData.getDirData("Pricing")); 
	
	@FXML
	private void ComboAction() throws Exception {
		vehicles = FXCollections.
				observableArrayList(ListData.getModels());
		vehicleCC.setItems(vehicles);
	}
	
	@FXML
	private void comboActionFile() throws Exception{
		options = FXCollections.
				observableArrayList(ListData.getDirData("Pricing"));
		filenameCC.setItems(options);
		System.out.println(filenameCC.getValue());
		vehicleSet.clear();
		if(options.contains(filenameCC.getValue())) //loading selected data set to the tableview
			populateVehicle(vehicleSet, "load");
	}

	public void savepr() throws Exception{
		ObservableList<Object> a = pricingTable.getItems();
		System.out.println("Pricing data updated");
		ObjectWriter.saveJob(new ArrayList<Object>(a), filenameCC.getValue(), "Pricing" );
		System.out.println(filenameCC.getValue());
	}
	
	public void populateVehicle(ObservableList<Object> vehicleSet, String func) throws Exception{
		
		if(func.equals("load")) {
			System.out.print(filenameCC.getValue());
			ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readConst("Pricing", filenameCC.getValue());
			for(int i = 0; i<jobread.size(); i++) {
				vehicleSet.add(jobread.get(i));
			}
		}
		
		System.out.println("adding price");
		pricingTable.setItems(vehicleSet);
		vehicleCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getVehicle()));
		locationCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getLocation()));
		retCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getRoute()));
		priceCol.setCellValueFactory(cellData ->
	    new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));
		System.out.println("new pricing added");
	}
	
	public void deleteData() {
		pricingTable.getItems().removeAll(pricingTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
		}

}
