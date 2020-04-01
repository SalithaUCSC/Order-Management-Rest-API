package services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.CustomerDao;
import org.apache.log4j.Logger;
import play.libs.Json;
import services.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private static Logger logger;

    public CustomerServiceImpl() {
        logger = Logger.getLogger(this.getClass());
        customerDao = CustomerDao.getInstance();
    }

    @Override
    public JsonNode getAllCustomersList() {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Customer DAO");
        JsonNode response = customerDao.getAllCustomers();
        if(response.isEmpty()){
            result.put("result", "No Customers in Store");
        }
        else {
            result.putPOJO("result", response);
        }
        logger.info("Response Returned From Customer DAO: "+result);
        return result;
    }

    @Override
    public JsonNode getCustomerById(String id) {
        logger.info("Request Received From Customer Controller");
        ObjectNode result = Json.newObject();
        if (id == null) {
            result.put("result", "ID is NULL");
        } else {
            JsonNode customer = customerDao.findCustomer(id);
            if (customer.isEmpty()) {
                result.put("result", "Customer Not Found - ID: "+id);
            } else {
                result.putPOJO("result", customer);
            }
        }
        return result;
    }

    @Override
    public JsonNode removeCustomerById(String id) {
        logger.info("Request Received From Customer Controller");
        ObjectNode result = Json.newObject();
        boolean customer =  customerDao.deleteCustomer(id);
        if (id == null) {
            result.put("result", "ID is NULL");
        }
        else if (customer) {
            result.put("result","Customer is Deleted");
        }
        else {
            result.put("result", "Customer Not Found - ID: "+id);
        }
        return result;
    }

    @Override
    public JsonNode addCustomer(JsonNode reqData) {
        logger.info("Request Received From Customer Controller");
        ObjectNode result = Json.newObject();
        if (reqData == null) {
            result.put("result", "Request Body is NULL");
        }
        else if (
                reqData.get("name") == null || reqData.get("age") == null ||
                        reqData.get("activeStatus") == null || reqData.get("address") == null
        ) {
            result.put("result", "Request is Missing Fields");
        } else {
            result.putPOJO("result", customerDao.saveCustomer(reqData));
        }
        return result;
    }

}
