package services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.ProductDao;
import org.apache.log4j.Logger;
import play.libs.Json;
import services.ProductService;;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private static Logger logger;

    public ProductServiceImpl() {
        logger = Logger.getLogger(this.getClass());
        productDao = ProductDao.getInstance();
    }

    @Override
    public JsonNode getAllProductsList() {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Product DAO");
        JsonNode response = productDao.getAllProducts();
        if(response.isEmpty()){
            result.put("result", "No Products in Store");
        }
        else {
            result.putPOJO("result", response);
        }
        logger.info("Response Returned From Product DAO: "+result);
        return result;
    }

    @Override
    public JsonNode getProductById(String id) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Product DAO");
        if (id == null) {
            result.put("result", "ID is NULL");
        } else {
            JsonNode product = productDao.findProduct(id);
            if (product.isEmpty()) {
                result.put("result", "Product Not Found - ID: "+id);
            } else {
                result.putPOJO("result", product);
            }
        }
        logger.info("Response Returned From Product DAO: "+result);
        return result;
    }

    @Override
    public JsonNode removeProductById(String id) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Product DAO");
        boolean product =  productDao.deleteProduct(id);
        if (id == null) {
            result.put("result", "ID is NULL");
        }
        else if (product) {
            result.put("result","Product is Deleted - ID: "+id);
        }
        else {
            result.put("result", "Product Not Found");
        }
        logger.info("Response Returned From Product DAO: "+result);
        return result;
    }

    @Override
    public JsonNode addProduct(JsonNode reqData) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Product DAO");
        if (reqData == null) {
            result.put("result", "Request Body is NULL");
        }
        else if (
                reqData.get("productName") == null || reqData.get("stockQuantity") == null || reqData.get("pricePerUnit") == null
        ) {
            result.put("result", "Request is Missing Fields");
        } else {
            result.putPOJO("result", productDao.saveProduct(reqData));
        }
        logger.info("Response Returned From Product DAO: "+result);
        return result;
    }


}
