package application.Individual;

import application.People.Driver;

public class HaulingJob implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String routes;
	int voucher;
	double tonage;
	String shift;
	String location;
	String date;
	TruckUnit Vehicle;
	Driver Driver;
	
	public HaulingJob(String location) {
		super();
		this.location = location;
		this.Vehicle = new TruckUnit();
		this.Driver = new Driver();	
		this.routes = "0";
	}
	public void setVehicleName(String name) {
		Driver.setName(name);
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
		return Vehicle;
	}
	public void setVehicle(TruckUnit vehicle) {
		Vehicle = vehicle;
	}
	public Driver getDriver() {
		return Driver;
	}
	public void setDriver(Driver driver) {
		Driver = driver;
	}
	public double getTonage() {
		return tonage;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
