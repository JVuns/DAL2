package application.Misc;
import java.io.File;
import java.util.ArrayList;

import application.People.Driver;
import application.Vehicle.Vehicles;

public class ListData {

    public static String[] getDirData() {
        String[] dataList;
        File f = new File("src/Data");
        dataList = f.list();
        System.out.println(dataList);
		return dataList;
    }
    public static String[] getVehicle() throws Exception {
    	System.out.println("getVehicle");
    	ArrayList<Vehicles> objectData = (ArrayList<Vehicles>) ObjectReader.readConst("Vehicle", "Vehicle");
    	String[] dataList = new String[objectData.size()];
    	System.out.print(objectData.size());
    	for(int i = 0; i<objectData.size(); i++) {
    		dataList[i] = objectData.get(i).getName();
    		System.out.println(objectData.get(i).getName());
    	}
    	System.out.println("Returning Data");
		return dataList;
    }
    public static String[] getDriver() throws Exception {
    	System.out.println("getDriver");
    	ArrayList<Driver> objectData = (ArrayList<Driver>) ObjectReader.readConst("People", "Driver");
    	String[] dataList = new String[objectData.size()];
    	System.out.print(objectData.size());
    	for(int i = 0; i<objectData.size(); i++) {
    		dataList[i] = objectData.get(i).getName();
    		System.out.println(objectData.get(i).getName());
    	}
    	System.out.println("Returning Data");
		return dataList;
    }
}