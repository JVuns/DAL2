package cloudAPI;

import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class UploadToCloud {
	
	public static FileMetadata UploadFile(DbxClientV2 client, InputStream filepath, String cloudFilepath) throws UploadErrorException, DbxException, IOException
	{
		FileMetadata metadata = client.files().uploadBuilder(cloudFilepath).uploadAndFinish(filepath);
		return metadata;
	}
	
	public static FileMetadata UploadFile(DbxClientV2 client, InputStream filepath) throws UploadErrorException, DbxException, IOException
	{
		String defaultCloudPath = "/file";
		return UploadFile(client, filepath, defaultCloudPath);
	}

}
