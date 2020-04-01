package dao;

import bo.Product;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDao {

    private static ProductDao productDao;
    private static MongoCollection<Document> COLLECTION;
    private static Logger logger;

    public ProductDao() {
        logger = Logger.getLogger(this.getClass());
        Config config = ConfigFactory.load();
        MongoClient mongoClient = MongoDbConnection.getMongoClient();
        String db = config.getString("app.db.mongo.db");
        String collection_name = config.getString("app.db.collection.products");
        COLLECTION = mongoClient.getDatabase(db).getCollection(collection_name);
    }

    public static ProductDao getInstance() {
        productDao = productDao == null ? new ProductDao() : productDao;
        return productDao;
    }

    public JsonNode getAllProducts() {
        List<Object> products = new ArrayList<>();
        MongoCursor<Document> cursor = COLLECTION.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            products.add(document);
        }
        return Json.toJson(products);
    }

    public Product saveProduct(JsonNode reqData) {
        Product product = new Product();
        product.setProductId((int) (COLLECTION.countDocuments() + 1));
        product.setProductName(reqData.get("productName").asText());
        product.setStockQuantity(reqData.get("stockQuantity").asInt());
        product.setPricePerUnit(reqData.get("pricePerUnit").asDouble());
        JsonNode productJson = Json.toJson(product);
        Document document = Document.parse(productJson.toString());
        COLLECTION.insertOne(document);
        return product;
    }

    public JsonNode findProduct(String id) {
        Document query = new Document();
        query.put("productId", Integer.parseInt(id));
        Document document = COLLECTION.find(query).first();
        JsonNode productJson = Json.toJson(document);
        return productJson;
    }

    public boolean deleteProduct(String id) {
        boolean isDeleted;
        Document query = new Document();
        query.put("productId", Integer.parseInt(id));
        Document document = COLLECTION.find(query).first();
        if (document == null) {
            isDeleted = false;
        } else {
            DeleteResult result = COLLECTION.deleteOne(Objects.requireNonNull(document));
            isDeleted = result.wasAcknowledged();
        }
        return isDeleted;
    }

    public int getProductQuantity(String productId) {
        JsonNode productJson = productDao.findProduct(productId);
        Product product = Json.fromJson(productJson, Product.class);
        return product.getStockQuantity();
    }

    public void updateProduct(Document existingDocument, Document newDocument){
        COLLECTION.replaceOne(existingDocument, newDocument);
    }

}
