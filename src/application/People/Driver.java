package application.People;

public class Driver extends Employee implements java.io.Serializable{

	private static final long serialVersionUID = -4538905310137281785L;
	String truck[];
	String routes[];
	
	public Driver(String name, String id, int salary, String[] truck, String[] routes) {
		super(name, id, salary);
		this.truck = truck;
		this.routes = routes;
	}
	
	public String[] getVehicles() {
		return truck;
	}
	public void setVehicles(String[] truck) {
		this.truck = truck;
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
