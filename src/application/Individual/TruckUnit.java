package application.Individual;

import application.Vehicle.Truck;

public class TruckUnit extends Truck implements Unit{
	public TruckUnit(String model, String name, String id, String spec, String status, int year) {
		super(model, name, id, spec, status, year);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047455891126300358L;
	String job;
	double tonage;
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public double getTonage() {
		return tonage;
	}
	public void setTonage(double tonage) {
		this.tonage = tonage;
	}
	public void Reset() {
		this.job = "No Job";
		this.tonage = 0;
	}
}
