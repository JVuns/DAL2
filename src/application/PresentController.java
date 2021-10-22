package application;

import java.time.LocalDate;
import java.util.ArrayList;

import application.Misc.ExceptionDate;
import application.Misc.ObjectReader;
import application.Misc.ObjectWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PresentController {
	@FXML private DatePicker date;
	@FXML private ComboBox<String> shift;
	@FXML private TextField desc;
	@FXML private TextField code;
	
	final ObservableList<Object> exceptionSet = FXCollections.observableArrayList();
	
	@FXML private TableView<Object> exceptionTable;
	@FXML private TableColumn<ExceptionDate, String> dateCol;
	@FXML private TableColumn<ExceptionDate, String> shiftCol;
	@FXML private TableColumn<ExceptionDate, String> descCol;
	@FXML private TableColumn<ExceptionDate, String> codeCol;
	
	ObservableList<String> mode = FXCollections.observableArrayList(
			"Day",
			"Night",
			"-");
	
	public void initialize() throws Exception {
		shift.setItems(mode);
		
		ArrayList<?> loadException = (ArrayList<?>) ObjectReader.readConst("Exception", "Exception"); //loading driver list to tableview
		try{
			for(int i = 0; i<loadException.size(); i++) {
				exceptionSet.add(loadException.get(i));
		}
			populate(exceptionSet);
		}catch (Exception e) {
			System.out.println(e);
		}
		
		EventHandler<KeyEvent> deleteKey = key -> { //Event handler for key bind
			if(key.getCode() == KeyCode.DELETE) { //Condition to make tables delete their respective
				if(exceptionTable.isVisible())
					try {
						deleteRow();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		};
		exceptionTable.addEventHandler(KeyEvent.KEY_PRESSED, deleteKey);	
	}
	
	@FXML
	public void addException() throws Exception {
		ExceptionDate dateEx = new ExceptionDate(null, null, null, null);
		dateEx.setDate(date.getValue());
		dateEx.setShift(shift.getValue());
		dateEx.setDesc(desc.getText());
		dateEx.setCode(code.getText());
		exceptionSet.add(dateEx);
		if(dateEx.getDate() instanceof LocalDate || 
				dateEx.getCode() != null ||
				dateEx.getDate() != null ||
				dateEx.getDesc() != null)
		populate(exceptionSet);
		else
		{
			System.out.println("Bad argument Error");
		}
	}

	public void populate(ObservableList<Object> set) throws Exception {
		System.out.println("populating exception table");
		dateCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getDate().toString()));
		shiftCol.setCellValueFactory(cellData -> 
	    new SimpleStringProperty(cellData.getValue().getShift()));
		descCol.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getDesc()));
		codeCol.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getCode()));
		System.out.println("new log added");
		exceptionTable.setItems(set);
		save();
	}
	
	public void save() throws Exception{
		ObservableList<Object> a = exceptionTable.getItems();
		System.out.println("Vehicle data updated");
		ObjectWriter.saveJob(new ArrayList<Object>(a), "Exception", "Exception");
	}

	public void deleteRow() throws Exception {
		exceptionTable.getItems().removeAll(exceptionTable.getSelectionModel().getSelectedItems());
	    System.out.println("Deleted");
	    save();
		}

	
}
