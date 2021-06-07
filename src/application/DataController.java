package application;

import java.util.ArrayList;

import application.Individual.HaulingJob;
import application.Misc.ObjectReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataController {
	@FXML private TableView<Object> dataTable;
	@FXML private TableColumn<HaulingJob, String> Job;
	@FXML private TableColumn<HaulingJob, String> Tonage;
	@FXML private TableColumn<HaulingJob, String> Route;
	@FXML private TableColumn<HaulingJob, String> Driver;
	@FXML private TableColumn<HaulingJob, String> Date;
	
	
	public void populate() throws Exception {

		ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob("compatible");
		
		HaulingJob data2 = (HaulingJob) jobread.get(0);
		System.out.print(data2.getDate());
		final ObservableList<Object> dataSet = FXCollections.observableArrayList(
				data2,
				data2
				);
		
		for(int i = 0; i<jobread.size());
		new SimpleStringProperty("A");
		Driver.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getRoute()));
		
		System.out.print("new data added");
		dataTable.setItems(dataSet);
	}
}
