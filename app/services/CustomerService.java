package services;

import com.fasterxml.jackson.databind.JsonNode;

public interface CustomerService {

    JsonNode getAllCustomersList();

    JsonNode removeCustomerById(String id);

    JsonNode addCustomer(JsonNode reqData);

    JsonNode getCustomerById(String id);

}
