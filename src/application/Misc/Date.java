package application.Misc;
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class

public class Date implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public static String getDate() {
		LocalDateTime curdate = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String format2 = curdate.format(format);
		return format2;
	  }
}
