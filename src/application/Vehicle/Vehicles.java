package application.Vehicle;

public abstract class Vehicles implements java.io.Serializable{
	public Vehicles(String model, String name, String id, String spec, String status) {
		super();
		this.model = model;
		this.name = name;
		this.id = id;
		this.spec = spec;
		this.status = status;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8728215829461315015L;
	String model;
	String name;
	String id;
	String spec;
	String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
}
