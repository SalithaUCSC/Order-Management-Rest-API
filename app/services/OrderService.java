package services;

import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

    JsonNode getAllOrdersList();

    JsonNode getOrderById(String id);

    JsonNode getOrdersListByCustomer(String custId);

    JsonNode placeOrder(JsonNode reqData);

    JsonNode changeOrderStatus(String orderId, JsonNode request);

    JsonNode removeOrderById(String id);

}
