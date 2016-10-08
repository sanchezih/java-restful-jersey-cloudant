
package com.sanchezih.rest.demo;

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

/*

  findByIndex("\selector\": {"\_id"\: { "\$gt\": 0}},
  Books.class,new FindByIndexOptions().
  sort(new IndexField("title",sortOrder.asc).
  fields("title").fields("author"));


 */

@Path("/webservice")
public class WebController {

	@GET
	@Path("/echo/{message}")
	@Produces("text/plain")
	public String showMsg(@PathParam("message") String message) {
		return message;
	}

	@POST
	@Path("/book/{name}/{by}/{likes}/{year}/{description}")
	@Produces("text/plain")
	public String insert(@PathParam("name") String name, @PathParam("description") String description,
			@PathParam("likes") Long likes, @PathParam("year") String year, @PathParam("by") String by) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		Book book = new Book();
		book.setTitle(name);
		book.setDescription(description);
		book.setLikes(likes);
		book.setYear(year);
		book.setBy(by);
		Response r = db.post(book);
		return r.getId() + " ; " + db.getDBUri();

	}

	@GET
	@Path("/records")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getRecords() {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();

		List<Book> list = db.findByIndex("\"selector\": { \"_id\": { \"$gt\": 0} }", Book.class);
		return list;
	}

	@GET
	@Path("/record/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getRecordFromName(@PathParam("title") String message) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		List<Book> list = db.findByIndex("\"selector\": {\"title\": \"" + message + "\" }", Book.class);
		return list;
	}

	@PUT
	@Path("/book/{title}/{likes}")
	@Produces("text/plain")
	public String update(@PathParam("title") String title, @PathParam("likes") long likes) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		try {
			Book book = db.find(Book.class, title);
			book.setLikes(book.getLikes() + likes);
			Response r = db.update(book);
			return r.getId() + " updated likes to " + book.getLikes();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@DELETE
	@Path("/book/{title}")
	@Produces("text/plain")
	public String delete(@PathParam("title") String title) {
		CloudantDBSingleton dbSingleton = CloudantDBSingleton.getInstance();
		Database db = dbSingleton.testDatabase();
		try {
			Book book = db.find(Book.class, title);
			db.remove(book);
		} catch (Exception e) {
		}
		return title + " deleted";
	}

}