package application.People;

public class Driver extends Employee implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4538905310137281785L;
	String vehicles[];
	String routes[];
	
	public String[] getVehicles() {
		return vehicles;
	}
	public void setVehicles(String[] vehicles) {
		this.vehicles = vehicles;
	}
	public String[] getRoutes() {
		return routes;
	}
	public void setRoutes(String[] routes) {
		this.routes = routes;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

}
