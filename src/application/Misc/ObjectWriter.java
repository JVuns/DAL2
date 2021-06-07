package application.Misc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ObjectWriter {
	public static void saveJob(ArrayList<Object> object, String InPath) throws Exception{
		Path path = Paths.get("src/Data");
		if (Files.notExists(path)) {
			System.out.println("Cannot found directory\n"
					+ "Making a new one");
			File dir = new File("src/Data");
			dir.mkdir();
		}
		File f = new File("src/data/", InPath);
		ObjectOutputStream filesOut = new ObjectOutputStream(new FileOutputStream(f));
		filesOut.writeObject(object);
		filesOut.close();
		System.out.println("Saved");
	}
}		
