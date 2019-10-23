package web.mongo;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import web.mongo.db.ConnectionManager;
import web.mongo.db.modal.Book;




@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public FetchData() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				MongoClient connection = ConnectionManager.getMongo();
				MongoDatabase db = ConnectionManager.getDb("library1");
				MongoCollection<Document> collection = db.getCollection("books");
		
				MongoCursor<Document> cursor = collection.find().limit(30).iterator();
		
				List<Book> dataList = new LinkedList<>();
		//		System.out.println("inside get...");
		
				while (cursor.hasNext()) {
					Document d = (Document) cursor.next();
		
					Book b = new Book();
					b.setid(d.getInteger("bid"));
					b.setName(d.getString("name"));
					b.setAuthor(d.getString("author"));
					b.setPrice(d.getString("price"));
					b.setType(d.getString("type"));
					dataList.add(b);
				}
//				System.out.println();
//				for (Book book : dataList) {
//					System.out.println(book.getAuthor());
//				}
//		
				// ConnectionManager.close();
		
				request.setAttribute("list", dataList);
				request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		MongoClient connection = ConnectionManager.getMongo();
		MongoDatabase db = ConnectionManager.getDb("library1");
		MongoCollection<Document> collection = db.getCollection("books");
		
		List<Book> dataList = show(collection);
		request.setAttribute("list", dataList);
		
		String id = request.getParameter("bookid");
		int id1 = Integer.valueOf(id);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String price = request.getParameter("price");
		String author = request.getParameter("author");
		Document document = new Document("bid", id1).append("name", name)
				.append("author", author)
				.append("type", type).append("price", price);
		

		String button = request.getParameter("button");
		switch(button) {
		case "Add":
			collection.insertOne(document);
			List<Book> dataList2 = show(collection);
			request.setAttribute("list", dataList2);
			break;
		case "Delete":
			collection.deleteOne(document);
			List<Book> dataList1 = show(collection);
			request.setAttribute("list", dataList1);
			break;
		
		case "Update":
			MongoCursor<Document> cursor = collection.find().limit(10).iterator();
			while(cursor.hasNext()) {
				Document d = (Document) cursor.next();
				if(id1 == d.getInteger("bid")) {
					collection.updateOne(Filters.eq("_id", d.getObjectId("_id")), 
							Updates.combine(
									Updates.set("name",name),
									Updates.set("author",author), 
									Updates.set("price",price),  
									Updates.set("type",type) 
					));
				}
			}
			List<Book> dataList3 = show(collection);
			request.setAttribute("list", dataList3);
			break;
		}
		

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
	List<Book> show(MongoCollection<Document> collection){
		List<Book> dataList = new LinkedList<>();
		MongoCursor<Document> cursor = collection.find().limit(10).iterator();
		while (cursor.hasNext()) {
			Document d = (Document) cursor.next();

			Book zip = new Book();
			zip.setid(d.getInteger("bid"));
			zip.setName(d.getString("name"));
			zip.setAuthor(d.getString("author"));
			zip.setPrice(d.getString("price"));
			zip.setType(d.getString("type"));

			dataList.add(zip);
		}
		return dataList;
	}

}
