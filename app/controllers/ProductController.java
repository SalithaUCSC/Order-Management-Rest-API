package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import play.libs.Json;
import play.mvc.*;
import services.ProductService;
import services.impl.ProductServiceImpl;

public class ProductController extends Controller {

    private ProductService productService;
    private static Logger logger;

    public ProductController() {
        logger = Logger.getLogger(this.getClass());
        productService = new ProductServiceImpl();
    }

    public Result index() {
        return ok("welcome to product service");
    }

    public Result getProducts() {
        logger.info("Request Sent to Product Service");
        JsonNode productsList = productService.getAllProductsList();
        logger.info("Product List Returned: " + Json.toJson(productsList));
        return ok(productsList);
    }

    public Result getProduct(String id) {
        logger.info("Request Sent to Product Service");
        JsonNode productResult = productService.getProductById(id);
        logger.info("Product Returned to Product Controller: " + productResult.get("result"));
        return ok(productResult);
    }

    public Result addNewProduct(Http.Request request) {
        logger.info("Request Sent to Product Service");
        JsonNode addResult = productService.addProduct(request.body().asJson());
        logger.info("Newly Added Product Returned to Product Controller: " + addResult.get("result"));
        return ok(addResult);
    }

    public Result removeProduct(String id) {
        logger.info("Request Sent to Product Service");
        JsonNode deleteResult = productService.removeProductById(id);
        logger.info("Deleted Product Returned to Product Controller: " + deleteResult.get("result"));
        return ok(deleteResult);
    }

}
