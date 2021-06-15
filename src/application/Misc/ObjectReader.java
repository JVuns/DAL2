package application.Misc;

import java.io.File;
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
	public static Object readConst(String dir, String fileName) throws Exception{
        try { 
        	String pathIn = ("src/"+dir+"/"+fileName);
        	System.out.println("Looking from " + pathIn);
        	FileInputStream fileIn = null;
        	try {
            fileIn = new FileInputStream(pathIn);
        	}catch (Exception e) {
				System.out.print("File is missing, making one");
				File file = new File("src/"+dir +"/", fileName + ".txt");
		        file.createNewFile();
		        fileIn = new FileInputStream(pathIn);
			}
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
