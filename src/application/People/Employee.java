package application.People;

public class Employee {
	public Employee(String name, String id, int salary) {
		super();
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	String name;
	String id;
	int salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + "]";
	}	
	public void addSalary(int salary) {
		this.salary += salary;
	}
	public void subSalary(int salary) {
		this.salary -= salary;
	}
}
