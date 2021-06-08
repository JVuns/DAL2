package application.Individual;

import application.People.Driver;

public class HaulingJob implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String job = "Hauling";
	String routes;
	int voucher;
	double tonage;
	String shift;
	String location;
	String date;
	TruckUnit vehicle;
	Driver driver;
	
	public HaulingJob(String location, Driver driver, TruckUnit vehicle) {
		super();
		this.location = location;
		this.vehicle = vehicle;
		this.driver = driver;	
		this.routes = "0";
	}
	public void setVehicleName(String name) {
		driver.setName(name);
	}
	public void setDate() {
		this.date = application.Misc.Date.getDate();
	}
	public void setTonage(double tonage) {
		this.tonage = tonage;
	}
	public void setlocation(String location) {
		this.location = location;
	}
	public void addRoute(int routes) {
		this.routes += routes;
	}
	public String getLocation() {
		return this.location;
	}
	public String getRoute() {
		return this.routes;
	}
	public void setRoutes(String routes) {
		this.routes = routes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public TruckUnit getVehicle() {
		return vehicle;
	}
	public void setVehicle(TruckUnit vehicle) {
		this.vehicle = vehicle;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public double getTonage() {
		return tonage;
	}
	public String getTonage(String string) {
		String stringTon = String.valueOf(this.tonage);
		return stringTon;
	}
	public String getJob() {
		return job;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
