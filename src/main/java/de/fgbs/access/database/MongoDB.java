package de.fgbs.access.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MongoDB extends MongoClient {

  // @LC: I recommend naming the Logger variable eigther "log" or "LOG". Since you need one of these
  // in every class, long variable names become cumbersome after a while.
  private static final Logger logger = LogManager.getLogger(MongoDAO.class);

  private static MongoClient mongoClient;

  private MongoDB() {}

  private static MongoClient getMongoClient() {
    if (mongoClient == null) {
      mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
      logger.info("MongoDB Verbindung wurde aufgebaut");
    }
    return mongoClient;
  }

  public static MongoClient getInstance() {
    return getMongoClient();
  }

  public void close() {
    mongoClient.close();
    logger.info("MongoDB Verbindung geschlossen");
  }
}
