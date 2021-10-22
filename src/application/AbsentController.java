package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.Misc.Absent;
import application.Misc.ListData;
import application.Misc.ObjectReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.Individual.HaulingJob;

public class AbsentController {
	@FXML private DatePicker dateFrom;
	@FXML private DatePicker dateTo;
	@FXML private TableView<Absent> AbsentTable;
	@FXML private ComboBox<String> fileObj;
	@FXML private Button loadButton;
	
	@FXML private TableColumn<Absent, String> noCol;
	@FXML private TableColumn<Absent, String> namaCol;
	@FXML private TableColumn<Absent, String> jobCol;
	@FXML private TableColumn<Absent, String> hadCol;
	@FXML private TableColumn<Absent, String> osCol;
	@FXML private TableColumn<Absent, String> alphaCol;
	@FXML private TableColumn<Absent, String> izinCol;
	@FXML private TableColumn<Absent, String> ipCol;
	@FXML private TableColumn<Absent, String> sakitCol;
	@FXML private TableColumn<Absent, String> breakCol;
	@FXML private TableColumn<Absent, String> liburCol;
	@FXML private TableColumn<Absent, String> cutiCol;
	@FXML private TableColumn<Absent, String> karantinaCol;
	@FXML private TableColumn<Absent, String> totalCol;
	
	TableColumn<Object, ?> column;
	ObservableList<String> options = FXCollections.				//Observable lists for combobox options
			observableArrayList(ListData.getDirData("Data"));
	ObservableList<Object> dataSet = FXCollections.observableArrayList();
	@FXML ObservableList<Absent> absentSet = FXCollections.observableArrayList();
	ObservableList<TableColumn<Absent, String>> columnSet = FXCollections.observableArrayList();
	
	@FXML
	public void initialize()
	{
		fileObj.setItems(options);
	}
	
	@FXML
	public void loadAction() throws Exception //loading file
	{
//		if(options.contains(fileObj.getValue())) {
//			dataSet.clear();
//			ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob(fileObj.getValue());
//			for(int i = 0; i<jobread.size(); i++) {
//				dataSet.add(jobread.get(i));
//			processAttendance(dataSet);
//			}
//		}
		System.out.println("Selecting" + " " + fileObj.getValue());
	}
	
	public ObservableList<Object> getLog() throws Exception {
		dataSet.clear();
		if(options.contains(fileObj.getValue())) {
			ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob(fileObj.getValue());
			for(int i = 0; i<jobread.size(); i++) {
				dataSet.add(jobread.get(i));
			}
			return dataSet;
		}
		return dataSet;
	}
	
	@FXML
	private void UpdateAction() { //happen everytime interacting with combobox
		System.out.println("Updating");
		options = FXCollections.
				observableArrayList(ListData.getDirData("Data"));

		fileObj.setItems(options); //updating combobox when new items were added
	}
	
	public void processAttendance(ObservableList<Object> dataset) throws Exception //process data to presentable form
	{
		for(int i = 0; i<dataset.size(); i++)
		{
			System.out.println(((HaulingJob) dataset.get(i)).getDriver().getName() + " " +  ((HaulingJob) dataset.get(i)).getDate());
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
    private void addColumn() throws Exception {
		if(dateFrom.getValue() != null && dateTo.getValue() != null)
		{
			if(AbsentTable.getColumns().size() > 4)
			{
				for(int i = AbsentTable.getColumns().size(); i>4; i--)
				{
					AbsentTable.getColumns().remove(i-1);
				}
			}
			LocalDate startDate = dateFrom.getValue();
			LocalDate endDate = dateTo.getValue();
			columnSet.removeAll(columnSet);
			List<LocalDate> listOfDates = startDate.datesUntil(endDate)
	                .collect(Collectors.toList());
			System.out.println(listOfDates);
			LocalDate[] dates = new LocalDate[listOfDates.size()];
			for(int i = 0; i<listOfDates.size(); i++)
			{
				TableColumn<Absent, ?> column = new TableColumn<Absent, String>();
				column.setText(listOfDates.get(i).toString());
				column.setEditable(true);
				dates[i] = listOfDates.get(i);
		        System.out.println(AbsentTable.getColumns().add(column));
		        columnSet.add((TableColumn<Absent, String>) column);
			}
			processData(columnSet, listOfDates);
		}
      }
	
	public void processData(ObservableList<TableColumn<Absent, String>> columnSet2, List<LocalDate> listOfDates) throws Exception {
		dataSet = getLog();
		absentSet.clear();
		ArrayList<String> assignedName = new ArrayList<>();
		System.out.println(dataSet.size());
		for (int i = 0; i<dataSet.size(); i++)
		{
			String Name = ((HaulingJob) dataSet.get(i)).getDriver().getName();
			String Job = ((HaulingJob) dataSet.get(i)).getJob();
//			System.out.println(((HaulingJob) dataSet.get(i)).getDriver().getName());
			if(!assignedName.contains(Name))
			{
				System.out.println("Creating " + Name + " database");
				String no = String.valueOf(i);
				Absent absent = new Absent(no, Name, Job, null, null, null, null, null, null, null, null, null, null, null);
				absent.setAbsDates(listOfDates);
				List<String> empty = new ArrayList<String>();
				absent.setAbsDatesVal(empty);
				absentSet.add(absent);
			}
			if(assignedName.contains(Name))
			{
				System.out.println("[A] Found name in database");
				for (int c = 0; c<absentSet.size(); c++)
				{
					System.out.println("[A] " + ((Absent) absentSet.get(c)).getAbsName() + "a");
					if(((Absent) absentSet.get(c)).getAbsName().equals(Name))
						{
							Absent absentDF = ((Absent) absentSet.get(c));
							absentDF.addDatesVal(absentDF.findPos(((HaulingJob) dataSet.get(i)).getDate()), ((HaulingJob) dataSet.get(i)).getDate());
							System.out.println("[A]hello");
							break;
						}
				}
			}
			assignedName.add(Name);
		}
		addToColumn(absentSet, columnSet2);
	}
	

	public void addToColumn(ObservableList<Absent> dataSet, ObservableList<TableColumn<Absent, String>> columnSet2) {
		System.out.println(((Absent) dataSet.get(0)).getAbsDates());
		for(int i = 0; i<columnSet.size(); i++)
		{
			final int iCopy = i;
			columnSet2.get(i).setCellValueFactory(cellData -> 
		    new SimpleStringProperty(((Absent) cellData.getValue()).getNotation(columnSet2.get(iCopy).getText(), iCopy)));
			System.out.println("test");
		}
		
		System.out.println("addAbsent");
//		noCol.setCellValueFactory(cellData -> 
//	    new SimpleStringProperty(cellData.getValue().getAbsAlpha()));
//		namaCol.setCellValueFactory(cellData -> 
//		new SimpleStringProperty(cellData.getValue()));
//		jobCol.setCellValueFactory(cellData -> 
//		new SimpleStringProperty(cellData.getValue()));
//		hadCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		osCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		alphaCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		izinCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		ipCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		sakitCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		breakCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		liburCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		cutiCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		karantinaCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
//		totalCol.setCellValueFactory(cellData ->
//		new SimpleStringProperty(cellData.getValue()));
		
//		System.out.println("new log added");
		System.out.println(dataSet.size());
		AbsentTable.setItems(dataSet);
	}
}
