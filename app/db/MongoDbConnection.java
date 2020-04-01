package db;

import com.mongodb.MongoClient;

public class MongoDbConnection {

    private static MongoClient mongoClient;

    public static MongoClient getMongoClient() {
        mongoClient = mongoClient == null ? new MongoClient() : mongoClient;
        return mongoClient;
    }

}
