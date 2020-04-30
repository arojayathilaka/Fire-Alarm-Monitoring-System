// resource class
package com.ds.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sensors") // this resource will be called when a request is made with "sensors" in it
public class SensorResource {
	
	SensorRepository repo = new SensorRepository();
	
	// this method will be called when a GET request is made with /sensors at the end
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sensor> getSensors() {
			
		return repo.getSensors();
	}
	
	// this method will be called when a GET request is made with /sensors/id at the end
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sensor getSensor(@PathParam("id") int id) {
		
		return repo.getSensor(id);
	}
	
	// this method will be called when a POST request is made with /sensors/add at the end
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Sensor addSensor(Sensor sensor) {
		
		return repo.addSensor(sensor);
	}
	
	// this method will be called when a PUT request is made with sensors/update at the end
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Sensor updateSensor(Sensor sensor) {
		
		return repo.updateSensor(sensor);
	}
}
