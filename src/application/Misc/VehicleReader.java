package application.Misc;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class VehicleReader {
	/*
	 * Loading vehicles data
	 */
	public static Object readVehicle() throws Exception{
        try { 
        	String pathIn = ("src/Vehicle/Vehicle");
            FileInputStream fileIn = new FileInputStream(pathIn);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
 
            System.out.println("Vehicle data has been read");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            }
        }
}
