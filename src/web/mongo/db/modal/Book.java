package web.mongo.db.modal;

public class Book {
	int id;
	String name;
	String author;
	String price;
	String type;

	public Book() {
		
	}

	public Book(int id, String name, String author, String price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.type = type;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	

}
