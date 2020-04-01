package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.CustomerService;
import services.impl.CustomerServiceImpl;

public class CustomerController extends Controller {

    private CustomerService customerService;
    private static Logger logger;

    public CustomerController() {
        logger = Logger.getLogger(this.getClass());
        customerService = new CustomerServiceImpl();
    }

    public Result index() {
        return ok("welcome to customer service");
    }

    public Result getCustomers() {
        logger.info("Request Sent to Customer Service");
        JsonNode customersList = customerService.getAllCustomersList();
        logger.info("Customer List Returned: " + Json.toJson(customersList));
        return ok(Json.toJson(customersList));
    }

    public Result getCustomer(String id) {
        logger.info("Request Sent to Customer Service");
        JsonNode customerResult = customerService.getCustomerById(id);
        logger.info("Customer Returned to Customer Controller: " + customerResult.get("result"));
        return ok(customerResult);
    }

    public Result addNewCustomer(Http.Request request) {
        logger.info("Request Sent to Customer Service");
        JsonNode addResult = customerService.addCustomer(request.body().asJson());
        logger.info("Newly Added Customer Returned to Customer Controller: " + addResult.get("result"));
        return ok(addResult);
    }

    public Result removeCustomer(String id) {
        logger.info("Request Sent to Customer Service");
        JsonNode deleteResult = customerService.removeCustomerById(id);
        logger.info("Deleted Customer Returned to Customer Controller: " + deleteResult.get("result"));
        return ok(deleteResult);
    }

}
