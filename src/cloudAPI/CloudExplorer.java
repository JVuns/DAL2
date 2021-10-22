package cloudAPI;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class CloudExplorer {
	public static void CloudSearch(DbxClientV2 client, String path) throws ListFolderErrorException, DbxException
	{
	ListFolderResult result = client.files().listFolder(path);
	while (true) {
	    for (Metadata metadata : result.getEntries()) {
	        System.out.println("Lower path: " + metadata.getPathLower());
	    }

	    if (!result.getHasMore()) {
	        System.out.println(result.getEntries());
	        break;
	    }

	    result = client.files().listFolderContinue(result.getCursor());
	}
	}

}
