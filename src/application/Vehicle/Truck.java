package application.Vehicle;

public class Truck extends Vehicles{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4840599902292522644L; //Serialisation for object writer
	public Truck(String model, String name, String id, String spec, String status, int year) {
		super(model, name, id, spec, status);
		this.year = year;
	}
	int year;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getYear(String a) {
		String yearProp = Integer.toString(year); //wrapping year so that it can be displayed
		return yearProp;
	}
}
