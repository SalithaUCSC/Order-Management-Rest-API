package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.OrderService;
import services.impl.OrderServiceImpl;

public class OrderController extends Controller {

    private OrderService orderService;
    private static Logger logger;

    public OrderController() {
        logger = Logger.getLogger(this.getClass());
        orderService = new OrderServiceImpl();
    }

    public Result index() {
        return ok("welcome to order service");
    }

    public Result getOrders() {
        logger.info("Request Sent to Order Service");
        JsonNode ordersList = orderService.getAllOrdersList();
        logger.info("Order List Returned to Order Controller: " + Json.toJson(ordersList));
        return ok(Json.toJson(ordersList));
    }

    public Result getOrder(String id) {
        logger.info("Request Sent to Order Service");
        JsonNode orderResult = orderService.getOrderById(id);
        logger.info("Order Returned to Order Controller: " +orderResult.get("result"));
        return ok(orderResult);
    }

    public Result placeNewOrder(Http.Request request) {
        logger.info("Request Sent to Order Service");
        JsonNode addResult = orderService.placeOrder(request.body().asJson());
        logger.info("Newly Added Order Returned to Order Controller: " +addResult.get("result"));
        return ok(addResult);
    }

    public Result getOrdersOfCustomer(String id) {
        logger.info("Request Sent to Order Service");
        JsonNode ordersList = orderService.getOrdersListByCustomer(id);
        logger.info("Order List of Customer Returned to Order Controller: " +Json.toJson(ordersList));
        return ok(Json.toJson(ordersList));
    }

    public Result changeStatusOfOrder(String id, Http.Request request) {
        logger.info("Request Sent to Order Service");
        JsonNode updateResult = orderService.changeOrderStatus(id, request.body().asJson());
        logger.info("Status Change Returned to Order Controller: " +updateResult.get("result"));
        return ok(updateResult);
    }

    public Result removeOrder(String id){
        logger.info("Request Sent to Order Service");
        JsonNode deleteResult = orderService.removeOrderById(id);
        logger.info("Deleted Order Status Returned to Order Controller: " + deleteResult.get("result"));
        return ok(deleteResult);
    }

}
