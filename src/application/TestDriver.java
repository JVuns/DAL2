package application;

import java.util.ArrayList;

import application.Individual.HaulingJob;
import application.Misc.ObjectWriter;
import application.Misc.ObjectReader;
import application.People.Driver;
import application.Vehicle.Vehicles;

public class TestDriver {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		HaulingJob job1 = new HaulingJob("Q1", null, null);
		Driver josh = new Driver(null, null, 0, null, args);
		josh.setName("Jayen");
		job1.addRoute(1);
		job1.setDate();
		job1.setDriver(josh);
		System.out.println("Date: " + job1.getDate());
		System.out.println("Driver Name: " + job1.getDriver().getName());
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(job1); 	
		ObjectWriter.saveJob(objects, "Test3", "Data");
		ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob("Test3");
		System.out.print(jobread);
//		System.out.println(((HaulingJob) jobread.get(0)).getDriver());
		ArrayList<Vehicles> vehicleRead = (ArrayList<Vehicles>) ObjectReader.readVehicle();
		System.out.print(vehicleRead.get(0).getName());
	}
}
