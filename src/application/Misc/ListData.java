package application.Misc;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import application.People.Driver;
import application.Vehicle.Vehicles;

public class ListData {

	/*
	 * returning list of files in data directory
	 */
	
    public static String[] getDirData(String dir) {
        String[] dataList;
        String filePath = new File("").getAbsolutePath();
        File f = new File(filePath+ "/src/" + dir);
        dataList = f.list();
        System.out.println(dataList);
		return dataList;
    }
    
    /*
     * returning a list of vehicle from Vehicle directory
     */
    
    public static String[] getVehicle() throws Exception {
    	System.out.println("getVehicle");
    	@SuppressWarnings("unchecked")
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
    
    /*
     * returning a list of driver from Driver directory
     */
    
    public static String[] getDriver() throws Exception {
    	System.out.println("getDriver");
    	@SuppressWarnings("unchecked")
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

    /*wdsdqwja
     * returning a list of models from vehicle file
     */
    public static String[] getModels() throws Exception{
    	System.out.println("getModels");
    	@SuppressWarnings("unchecked")
		ArrayList<Vehicles> objectData = (ArrayList<Vehicles>) ObjectReader.readConst("Vehicle", "Vehicle");
    	String[] dataList = new String[objectData.size()];
    	System.out.print(objectData.size());
    	for(int i = 0; i<objectData.size(); i++) {
    		dataList[i] = objectData.get(i).getSpec();
    		System.out.println(objectData.get(i).getSpec());
    	}
    	String[] uData = Arrays.stream(dataList).distinct().toArray(String[]::new); // find unique values in dataList
    	System.out.println("Returning Data");
		return uData;
    }
}