package application.People;

public class Driver extends Employee implements java.io.Serializable{

	private static final long serialVersionUID = -4538905310137281785L;
	String truck[];
	String routes[];
	String status;
	
	public Driver(String name, String id, int salary, String[] truck, String[] routes) {
		super(name, id, salary);
		this.truck = truck;
		this.routes = routes;
	}
	public String[] getVehicles() {
		return truck;
	}
	public void addVehicles(String truck) {
		this.truck[this.truck.length] = truck;
	}
	public String[] getRoutes() {
		return routes;
	}
	public void addRoutes(String routes) {
		this.routes[this.routes.length] = routes;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
