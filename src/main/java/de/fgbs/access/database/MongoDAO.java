package de.fgbs.access.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.primefaces.json.JSONObject;

// @LC: Usually there exists a DAO for every database entity and not for the whole database but I
// assume this class exists just for testing purposes.
// I recommend creating DAOs like UserDAO, or InvitationDAO that enable access to user or invitation
// entities respectively.
public class MongoDAO {

  // @LC: I recommend naming the Logger variable eigther "log" or "LOG". Since you need one of these
  // in every class, long variable names become cumbersome after a while.
  private static final Logger logger = LogManager.getLogger(MongoDAO.class);

  // @LC: This is business logic and should never be placed in a DataAccessObject (DAO)! Please move
  // to a separate service.
  public static boolean validate(String usr, String pwd) {
    MongoClient mongoClient = MongoDB.getInstance();
    try {
      MongoDatabase userDB = mongoClient.getDatabase("Access");
      MongoCollection<Document> userCollection = userDB.getCollection("User");
      Document email = userCollection.find(new Document("email", usr)).first();
      if (email == null) return false;
      String jsonString = email.toJson();
      JSONObject jsonObject = new JSONObject(jsonString);
      String password = (String) jsonObject.get("password");

      return password.equals(pwd);
    } catch (Exception e) {
      logger.error(e);
      return false;
    } finally {
      mongoClient.close();
    }
  }

  public static void insert() {
    MongoClient mongoClient = MongoDB.getInstance();
    MongoDatabase userDB = mongoClient.getDatabase("Access");
    MongoCollection<Document> collection = userDB.getCollection("User");
    Document u = new Document("_id", new ObjectId());
    u.append("email", "test@email.com").append("password", "sheesh");
    collection.insertOne(u);
    logger.info("Daten wurden eingefügt!");
  }
}
