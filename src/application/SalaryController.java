package application;

import application.Individual.HaulingJob;
import application.Misc.ListData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SalaryController {
	@FXML private TextField jobTF;
	@FXML private ComboBox<String> salaryPageCC;
	
	ObservableList<String> options = FXCollections.
			observableArrayList(ListData.getDirData()); 
	@FXML
	public void initialize() {
		salaryPageCC.setItems(options);
	}
	@FXML
	private void ComboAction(ActionEvent event) {
		System.out.print(salaryPageCC.getValue());
	}
	public void Submit() {
		HaulingJob job = new HaulingJob(null);
		System.out.println(jobTF.getText()+" Test");
	}
}

