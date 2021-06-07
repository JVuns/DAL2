package application;

import java.util.ArrayList;

import application.Individual.HaulingJob;
import application.Misc.ObjectWriter;
import application.Misc.ObjectReader;
import application.People.Driver;

public class TestDriver {
	public static void main(String[] args) throws Exception {
		HaulingJob job1 = new HaulingJob("Q1");
		Driver josh = new Driver();
		josh.setName("Jayen");
		job1.addRoute(1);
		job1.setDate();
		job1.setDriver(josh);
		System.out.println("Date: " + job1.getDate());
		System.out.println("Driver Name: " + job1.getDriver().getName());
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(job1); 	
		ObjectWriter.saveJob(objects, "compatible");
		ArrayList<?> jobread = (ArrayList<?>) ObjectReader.readJob("compatible");
		System.out.println(((HaulingJob) jobread.get(0)).getDate());
		
	}
}
