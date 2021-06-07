package application.Individual;

import application.Vehicle.Truck;

public class TruckUnit extends Truck implements Unit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047455891126300358L;
	String job;
	int Hours;
	double tonage;
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getHours() {
		return Hours;
	}
	public void setHours(int hours) {
		Hours = hours;
	}
	public double getTonage() {
		return tonage;
	}
	public void setTonage(double tonage) {
		this.tonage = tonage;
	}
	public void Reset() {
		this.Hours = 0;
		this.job = "No Job";
		this.tonage = 0;
	}
}
