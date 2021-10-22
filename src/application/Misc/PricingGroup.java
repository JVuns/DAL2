package application.Misc;

import java.io.Serializable;

public class PricingGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4618723388933934800L;
	private String route;
	private String vehicleModel;
	private String location;
	private int price;
	
	public PricingGroup(String vehicleModel, String location, int price) {
		this.vehicleModel = vehicleModel;
		this.location = location;
		this.price = price;
	}
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}
	
	public String getVehicle() {
		return vehicleModel;
	}
	
	public void setVehicle(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}	
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
