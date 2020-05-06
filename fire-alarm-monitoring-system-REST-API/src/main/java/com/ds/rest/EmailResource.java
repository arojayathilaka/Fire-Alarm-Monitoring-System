// email resource class
package com.ds.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("emails") // this resource will be called when a request is made with "emais" in it
public class EmailResource {
	
	EmailRepository repo = new EmailRepository();
	
	// this method will be called when a GET request is made with /emails at the end
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Email getStatus() {
		System.out.println("called");
		return repo.getStatus();
	}
	
	
	// this method will be called when a PUT request is made with /emails/update at the end
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Email updateStatus(Email email) {
		System.out.println("update called");

		return repo.updateEmail(email);
	}
	
}
