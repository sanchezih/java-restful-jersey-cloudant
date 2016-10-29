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

import com.sanchezih.rest.model.Pais;
import com.sanchezih.rest.services.PaisService;

@Path("/paises")
@Produces(MediaType.APPLICATION_JSON)
public class PaisResource {

	private PaisService paisService = new PaisService();

	@GET
	public List<Pais> getPaises() {
		return paisService.getAllPaises();
	}

	@GET
	@Path("/{id}")
	public Pais getCountryById(@PathParam("id") int id) {
		return paisService.getCountry(id);
	}

	@POST
	public Pais addCountry(Pais pais) {
		return paisService.addPais(pais);
	}

	@PUT
	public Pais updateCountry(Pais pais) {
		return paisService.updatePais(pais);
	}

	@DELETE
	@Path("/{id}")
	public void deleteCountry(@PathParam("id") int id) {
		paisService.deleteCountry(id);

	}

}
