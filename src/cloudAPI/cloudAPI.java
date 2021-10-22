package cloudAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class cloudAPI {
	private static final String ACCESS_TOKEN = "Jo1KpIivgyoAAAAAAAAAAVdGALq8Gy_VX5YBSyd73Eol8Xwc4seJbzuiGN40JT6-";
	
	public static void main(String[] args) throws DbxApiException, DbxException, FileNotFoundException, IOException {
		DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		FullAccount account = client.users().getCurrentAccount();
		System.out.println("Dropbox Account: " + account.getName().getDisplayName());
		System.out.println("Account Gmail" + account.getEmail());
//		CloudExplorer.CloudSearch(client, "/parent");
//		InputStream in = new FileInputStream("src/cloudAPI/Test.txt");
//		UploadToCloud.UploadFile(client, in);
    }
}

