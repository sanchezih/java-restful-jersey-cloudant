
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
import com.sanchezih.rest.demo.CloudantDBSingleton;
import com.sanchezih.rest.entities.Libro;

@Path("/books")
public class LibroResource {

	@GET
	@Path("/echo/{message}")
	@Produces("text/plain")
	public String showMsg(@PathParam("message") String message) {
		return message;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Libro> getBooks() {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();

		List<Libro> list = db.findByIndex("\"selector\": { \"_id\": { \"$gt\": 0} }", Libro.class);
		return list;
	}

	@POST
	@Path("/book/{name}/{by}/{likes}/{year}/{description}")
	@Produces("text/plain")
	public String addBook(@PathParam("name") String name, @PathParam("description") String description,
			@PathParam("likes") Long likes, @PathParam("year") String year, @PathParam("by") String by) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		Libro book = new Libro();
		book.setTitle(name);
		book.setDescription(description);
		book.setLikes(likes);
		book.setYear(year);
		book.setBy(by);
		Response r = db.post(book);
		return r.getId() + " ; " + db.getDBUri();
	}

	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Libro> getBookByName(@PathParam("title") String message) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		List<Libro> list = db.findByIndex("\"selector\": {\"title\": \"" + message + "\" }", Libro.class);
		return list;
	}

	@PUT
	@Path("/book/{title}/{likes}")
	@Produces("text/plain")
	public String update(@PathParam("title") String title, @PathParam("likes") long likes) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		try {
			Libro book = db.find(Libro.class, title);
			book.setLikes(book.getLikes() + likes);
			Response r = db.update(book);
			return r.getId() + " updated likes to " + book.getLikes();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DELETE
	@Path("/{title}")
	@Produces("text/plain")
	public String delete(@PathParam("title") String title) {
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