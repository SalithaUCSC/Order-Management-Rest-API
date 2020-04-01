package dao;

import bo.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import db.MongoDbConnection;
import org.apache.log4j.Logger;
import org.bson.Document;
import play.libs.Json;
import java.util.*;

public class CustomerDao {

    private static CustomerDao customerDao;
    private static MongoCollection<Document> COLLECTION;
    private static Logger logger;

    public CustomerDao() {
        logger = Logger.getLogger(this.getClass());
        Config config = ConfigFactory.load();
        MongoClient mongoClient = MongoDbConnection.getMongoClient();
        String db = config.getString("app.db.mongo.db");
        String collection_name = config.getString("app.db.collection.customers");
        COLLECTION = mongoClient.getDatabase(db).getCollection(collection_name);
    }

    public static CustomerDao getInstance() {
        customerDao = customerDao == null ? new CustomerDao() : customerDao;
        return customerDao;
    }

    public JsonNode getAllCustomers() {
        List<Object> customers = new ArrayList<>();
        MongoCursor<Document> cursor = COLLECTION.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            customers.add(document);
        }
        return Json.toJson(customers);
    }

    public Customer saveCustomer(JsonNode reqData) {
        Customer customer = new Customer();
        customer.setCustId((int) (COLLECTION.countDocuments() + 1));
        customer.setName(reqData.get("name").asText());
        Map<String, String> address = new HashMap<>();
        address.put("addrline1", reqData.get("address").get("addrline1").asText());
        address.put("addrline2", reqData.get("address").get("addrline2").asText());
        address.put("streetNo", reqData.get("address").get("streetNo").asText());
        address.put("landmark", reqData.get("address").get("landmark").asText());
        address.put("city", reqData.get("address").get("city").asText());
        address.put("country", reqData.get("address").get("country").asText());
        address.put("zipCode", reqData.get("address").get("zipCode").asText());
        customer.setAddress(address);
        customer.setAge(reqData.get("age").asInt());
        customer.setActiveStatus(reqData.get("activeStatus").asBoolean());
        JsonNode customerJson = Json.toJson(customer);
        Document document = Document.parse(customerJson.toString());
        COLLECTION.insertOne(document);
        return customer;
    }

    public JsonNode findCustomer(String id) {
        Document query = new Document();
        query.put("custId", Integer.parseInt(id));
        Document document = COLLECTION.find(query).first();
        JsonNode customerJson = Json.toJson(document);
        return customerJson;
    }

    public boolean deleteCustomer(String id) {
        boolean isDeleted;
        Document query = new Document();
        query.put("custId", Integer.parseInt(id));
        Document document = COLLECTION.find(query).first();

        if (document == null) {
            isDeleted = false;
        } else {
            DeleteResult result = COLLECTION.deleteOne(Objects.requireNonNull(document));
            isDeleted = result.wasAcknowledged();
        }
        return isDeleted;
    }
}
