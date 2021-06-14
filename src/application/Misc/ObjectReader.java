package application.Misc;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectReader {
	public static Object readJob(String filepath) throws Exception{
        try { 
        	String pathIn = ("src/Data/" + filepath);
        	System.out.println("Looking from " + pathIn);
            FileInputStream fileIn = new FileInputStream(pathIn);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            }
        }
	public static Object readVehicle() throws Exception{
        try { 
        	String pathIn = ("src/Vehicle/Vehicle");
        	System.out.println("Looking from " + pathIn);
            FileInputStream fileIn = new FileInputStream(pathIn);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
            }
        }
}
