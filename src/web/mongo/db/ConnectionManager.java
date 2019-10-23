package web.mongo.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {

	private static MongoClient mongo;

	private ConnectionManager() {

	}

	public static MongoClient getMongo() {
		if (mongo == null) {
			mongo = MongoClients.create("mongodb://localhost:27017");
		}
		return mongo;
	}

	public static MongoDatabase getDb(String databaseName) {
		return getMongo().getDatabase(databaseName);
	}

	public static void close() {
		getMongo().close();
	}

}