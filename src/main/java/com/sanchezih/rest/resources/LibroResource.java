
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

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.sanchezih.rest.database.CloudantDBSingleton;
import com.sanchezih.rest.model.Libro;
import com.sanchezih.rest.services.LibroService;

@Path("/libros")
public class LibroResource {

	private LibroService libroService = new LibroService();

	@GET
	@Path("/echo/{mensaje}")
	@Produces("text/plain")
	public String showMsg(@PathParam("mensaje") String msg) {
		return msg;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Libro> getLibros() {
		return libroService.getAllLibros();
	}

	@POST
	@Path("/{titulo}/{autor}/{likes}/{anio}/{descripcion}")
	@Produces("text/plain")
	public String addLibro(@PathParam("titulo") String titulo, @PathParam("descripcion") String descripcion,
			@PathParam("likes") Long likes, @PathParam("year") String year, @PathParam("autor") String autor) {

		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();

		Libro libro = new Libro();
		libro.setTitle(titulo);
		libro.setDescription(descripcion);
		libro.setLikes(likes);
		libro.setYear(year);
		libro.setBy(autor);

		Response r = db.post(libro);
		return r.getId() + " ; " + db.getDBUri();
	}

	@GET
	@Path("/{titulo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Libro> getBookByName(@PathParam("titulo") String message) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		List<Libro> list = db.findByIndex("\"selector\": {\"title\": \"" + message + "\" }", Libro.class);
		return list;
	}

	@PUT
	@Path("/libro/{titulo}/{likes}")
	@Produces("text/plain")
	public String update(@PathParam("titulo") String titulo, @PathParam("likes") long likes) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		try {
			Libro libro = db.find(Libro.class, titulo);
			libro.setLikes(libro.getLikes() + likes);
			Response r = db.update(libro);
			return r.getId() + " updated likes to " + libro.getLikes();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DELETE
	@Path("/{titulo}")
	@Produces("text/plain")
	public String delete(@PathParam("titulo") String title) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		try {
			Libro book = db.find(Libro.class, title);
			db.remove(book);
		} catch (Exception e) {
		}
		return title + " deleted";
	}

}