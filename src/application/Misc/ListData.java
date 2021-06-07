package application.Misc;
import java.io.File;

public class ListData {

    public static String[] getDirData() {
        String[] dataList;
        File f = new File("src/Data");
        dataList = f.list();
        System.out.print(dataList);
		return dataList;
    }
}