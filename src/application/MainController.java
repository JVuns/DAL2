package application;

import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadErrorException;

import cloudAPI.UploadToCloud;

public class MainController {
		/*
		 * TODO:	Add more pane corresponding to the excel sheet 
		 * 			Add cloudConnect to DataController.java
		 * 			
		 */
	public static void uploadData() throws UploadErrorException, DbxException, IOException
	{
		DbxClientV2 client = null;
		InputStream filepath = null;
		String cloudFilepath = null;
		try {
			System.out.println("File");
			//File refence in form of directory path (File, Filename)
			//String for intended cloud path
			if(cloudFilepath == null)
			UploadToCloud.UploadFile(client, filepath);
			else
			UploadToCloud.UploadFile(client, filepath, cloudFilepath);
		}
		catch(Error e)
		{
			System.out.println("No file selected");
		}
	}
	
	public static void deleteData()
	{
		;
	}
	
	public static void downloadData()
	{
		;
	}
}
	