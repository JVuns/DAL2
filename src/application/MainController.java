package application;

import java.util.ArrayList;
import application.Individual.HaulingJob;
import application.Misc.ListData;
import application.Misc.ObjectWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MainController {
		
	public void Save(ArrayList<Object> dataArray, String fileName) throws Exception {
		for(int i = 0; i>dataArray.size(); i++) {
			ObjectWriter.saveJob(dataArray, fileName);
		}
	}
}
	