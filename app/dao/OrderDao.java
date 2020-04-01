package dao;

import bo.Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import db.MongoDbConnection;
import org.apache.log4j.Logger;
import org.bson.Document;
import play.libs.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao {

    private static OrderDao orderDao;
    private static MongoCollection<Document> ORDER_COLLECTION;
    private static Logger logger;

    public OrderDao() {
        logger = Logger.getLogger(this.getClass());
        Config config = ConfigFactory.load();
        MongoClient mongoClient = MongoDbConnection.getMongoClient();
        String db = config.getString("app.db.mongo.db");
        String order_collection = config.getString("app.db.collection.orders");
        ORDER_COLLECTION = mongoClient.getDatabase(db).getCollection(order_collection);
    }

    public static OrderDao getInstance() {
        orderDao = orderDao == null ? new OrderDao() : orderDao;
        return orderDao;
    }

    public JsonNode getAllOrders() {
        List<Object> orders = new ArrayList<>();
        MongoCursor<Document> cursor = ORDER_COLLECTION.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            orders.add(document);
        }
        return Json.toJson(orders);
    }

    public JsonNode findOrder(String id) {
        Document query = new Document();
        query.put("orderId", Integer.parseInt(id));
        Document document = ORDER_COLLECTION.find(query).first();
        JsonNode orderJson = Json.toJson(document);
        return orderJson;
    }

    public JsonNode saveOrder(JsonNode reqData, int totalPrice, int itemsCount, List<JsonNode> productsToBeSaved) {
        Order order = new Order();
        order.setOrderId((int) (ORDER_COLLECTION.countDocuments() + 1));
        order.setCustId(reqData.get("custId").asInt());
        order.setProducts(productsToBeSaved);
        order.setOrderStatus(reqData.get("orderStatus").asText().toUpperCase());
        order.setDate();
        order.setTotalQuantity(itemsCount);
        order.setTotalPrice(totalPrice);
        JsonNode orderJson = Json.toJson(order);
        Document document = Document.parse(orderJson.toString());
        ORDER_COLLECTION.insertOne(document);
        return Json.toJson(order);
    }

    public List<Object> getAllOrdersOfCustomer(String custId) {
        List<Object> orders = new ArrayList<>();
        Document query = new Document();
        query.put("custId", Integer.parseInt(custId));
        MongoCursor<Document> cursor = ORDER_COLLECTION.find(query).iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            orders.add(document);
        }
        return orders;
    }

    public boolean updateOrder(Document existingDocument, Document newDocument){
        UpdateResult result = ORDER_COLLECTION.replaceOne(existingDocument, newDocument);
        return result.wasAcknowledged();
    }

    public boolean deleteOrder(String id) {
        boolean isDeleted;
        Document query = new Document();
        query.put("orderId", Integer.parseInt(id));
        Document document = ORDER_COLLECTION.find(query).first();
        if (document == null) {
            isDeleted = false;
        } else {
            DeleteResult result = ORDER_COLLECTION.deleteOne(Objects.requireNonNull(document));
            isDeleted = result.wasAcknowledged();
        }
        return isDeleted;
    }

}
