package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MiscController {
	@FXML private TextField jobTF;
	@FXML private TextField tonageTF;
	@FXML private TextField routeTF;
	@FXML private TextField driverTF;
	
	
	@FXML private ComboBox<String> modeCC;
	
	String[] optionsList = {"Data Entry"};
	
	@FXML
	public void initialize() {
		modeCC.setItems(options);
	}

	ObservableList<String> options = FXCollections.
			observableArrayList(optionsList); 

	@FXML
	private void ComboAction(ActionEvent event) {
		System.out.print(modeCC.getValue());
	}
	@FXML
	private void test() {
		System.out.print(jobTF.getText() + tonageTF.getText() + routeTF.getText() + driverTF.getText());
		//make print system here
	}
}
