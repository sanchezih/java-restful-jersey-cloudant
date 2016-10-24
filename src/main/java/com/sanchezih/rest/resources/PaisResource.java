package com.sanchezih.rest.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sanchezih.rest.demo.PaisService;
import com.sanchezih.rest.entities.Pais;

@Path("/countries")
public class PaisResource {

	PaisService countryService = new PaisService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pais> getCountries() {

		List<Pais> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pais getCountryById(@PathParam("id") int id) {
		return countryService.getCountry(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Pais addCountry(Pais country) {
		return countryService.addCountry(country);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Pais updateCountry(Pais country) {
		return countryService.updateCountry(country);

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCountry(@PathParam("id") int id) {
		countryService.deleteCountry(id);

	}

}
