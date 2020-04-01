package services.impl;

import bo.Customer;
import bo.Order;
import bo.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.CustomerDao;
import dao.OrderDao;
import dao.ProductDao;
import org.apache.log4j.Logger;
import org.bson.Document;
import play.libs.Json;
import services.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private ProductDao productDao;
    private CustomerDao customerDao;
    private static Logger logger;

    public OrderServiceImpl() {
        logger = Logger.getLogger(this.getClass());
        orderDao = OrderDao.getInstance();
        customerDao = CustomerDao.getInstance();
        productDao = ProductDao.getInstance();
    }

    @Override
    public JsonNode getAllOrdersList() {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        JsonNode response = orderDao.getAllOrders();
        if (response.isEmpty()) {
            result.put("result", "No Orders Placed");
        } else {
            result.putPOJO("result", response);
        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

    @Override
    public JsonNode getOrderById(String id) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        if (id == null) {
            result.put("result", "ID is NULL");
        } else {
            JsonNode order = orderDao.findOrder(id);
            if (order.isEmpty()) {
                result.put("result", "Order Not Found - ID: " + id);
            } else {
                result.putPOJO("result", order);
            }
        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

    @Override
    public JsonNode getOrdersListByCustomer(String custId) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        JsonNode customer = customerDao.findCustomer(custId);
        if (custId == null) {
            result.put("result", "ID is NULL");

        } else if (!customer.has("custId")) {
            result.put("result", "Customer Not Found - ID: " + custId);

        } else {
            List<Object> ordersOfCustomer = orderDao.getAllOrdersOfCustomer(custId);
            result.putPOJO("result", ordersOfCustomer);
        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

    @Override
    public JsonNode placeOrder(JsonNode reqData) {
        JsonNode orderValidated;
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        if (reqData == null || reqData.isEmpty()) {
            result.put("result", "Request Body is NULL");

        } else {
            JsonNode cust = customerDao.findCustomer(String.valueOf(reqData.get("custId").asInt()));
            Customer customer = Json.fromJson(cust, Customer.class);
            if (customer == null) {
                result.put("result", "Customer is not Found - ID: " + reqData.get("custId"));

            } else {
                if (customer.getActiveStatus()) {
                    ArrayNode arrayOfProducts = (ArrayNode) reqData.get("products");
                    for (JsonNode json : arrayOfProducts) {
                        int stock = productDao.getProductQuantity(String.valueOf(json.get("productId").asInt()));
                        if (stock > 0 && stock < json.get("quantity").asInt()) {
                            result.put("result", "Stock is Not Enough");
                        }
                    }
                    List<JsonNode> productsToBeSaved = new ArrayList<>();
                    ArrayNode productsArray = (ArrayNode) reqData.get("products");
                    int sumOfItems = 0;
                    int totalBill = 0;
                    for (JsonNode json : productsArray) {
                        productsToBeSaved.add(json);
                        sumOfItems += json.get("quantity").asInt();

                        JsonNode product = productDao.findProduct(String.valueOf(json.get("productId").asInt()));

                        Product existingProduct = Json.fromJson(product, Product.class);
                        JsonNode existingProJson = Json.toJson(existingProduct);
                        Document existingDocument = Document.parse(existingProJson.toString());

                        double totalPricePerProduct = (json.get("quantity").asInt() * existingProduct.getPricePerUnit());
                        totalBill += totalPricePerProduct;

                        int availableStock = existingProduct.getStockQuantity() - json.get("quantity").asInt();

                        if (availableStock > 0) {
                            Product newProduct = Json.fromJson(product, Product.class);
                            newProduct.setStockQuantity(availableStock);
                            JsonNode proJson = Json.toJson(newProduct);
                            Document newDocument = Document.parse(proJson.toString());
                            productDao.updateProduct(existingDocument, newDocument);
                        } else {
                            result.put("result", "Invalid Quantity is Requested");
                        }
                    }
                    orderValidated = orderDao.saveOrder(reqData, totalBill, sumOfItems, productsToBeSaved);
                    result.putPOJO("result", orderValidated);

                } else {
                    result.put("result", "Customer is not Active");
                }
            }

        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

    @Override
    public JsonNode changeOrderStatus(String orderId, JsonNode request) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        if (orderId == null) {
            result.put("result", "ID is NULL");
        }
        boolean orderChangeStatus;
        JsonNode order = orderDao.findOrder(orderId);

        if (order == null || order.isEmpty()) {
            result.put("result", "Order Not Found - ID: " + orderId);

        } else if (request == null) {
            result.put("result", "Request Body is NULL");

        } else if (request.get("orderStatus").asText().isEmpty()) {
            result.put("result", "Status is NULL");

        } else {
            Order existingOrder = Json.fromJson(order, Order.class);
            JsonNode existingOrderJson = Json.toJson(existingOrder);
            Document existingDocument = Document.parse(existingOrderJson.toString());

            String reqStatus = request.get("orderStatus").asText();
            String orderStatus = order.get("orderStatus").asText();

            String msg = validateChangeStatus(reqStatus, orderStatus, order).get("result").asText();

            if (msg.equals("VALID")) {
                Order newOrder = Json.fromJson(order, Order.class);
                newOrder.setOrderStatus(reqStatus.toUpperCase());
                newOrder.setDate();
                JsonNode newOrderJson = Json.toJson(newOrder);
                Document newDocument = Document.parse(newOrderJson.toString());
                orderChangeStatus = orderDao.updateOrder(existingDocument, newDocument);
                if (orderChangeStatus) {
                    result.put("result", "Order Status is Updated - ID: " + existingOrder.getOrderId());
                } else {
                    result.put("result", "Request Failed");
                }
            } else {
                result.put("result", msg);

            }
        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

    public JsonNode validateChangeStatus(String reqStatus, String orderStatus, JsonNode order) {
        List<String> statusArray = Arrays.asList("PENDING", "INPROGRESS", "CANCELLED", "DELIVERED");
        String upperReqStatus = reqStatus.toUpperCase();
        String upperOrderStatus = orderStatus.toUpperCase();
        ObjectNode result = Json.newObject();

        if (!statusArray.contains(upperReqStatus)) {
            result.put("result", "Request Status is Invalid");

        } else if (statusArray.contains(upperReqStatus) && !statusArray.contains(upperOrderStatus)) {
            result.put("result", "Order Status is Invalid");
        } else {
            if (upperOrderStatus.equals(upperReqStatus)) {
                result.put("result", "Both Status are Same");

            } else if (upperOrderStatus.equals("DELIVERED")) {
                if (upperReqStatus.equals("PENDING") || upperReqStatus.equals("CANCELLED") || upperReqStatus.equals("INPROGRESS")) {
                    result.put("result", "Delivered Orders Can not be Reverted");

                }
            } else if (upperOrderStatus.equals("PENDING")) {
                if (upperReqStatus.equals("DELIVERED")) {
                    result.put("result", "Pending Orders Can not be Delivered");

                } else if (upperReqStatus.equals("CANCELLED")) {
                    increaseProductCount(order, upperReqStatus);
                    orderDao.deleteOrder(order.get("orderId").asText());
                    result.put("result", "VALID");

                } else {
                    result.put("result", "VALID");
                }

            } else if (upperOrderStatus.equals("INPROGRESS")) {
                if (upperReqStatus.equals("PENDING")) {
                    result.put("result", "InProgress Orders Can not be Moved to Pending");

                } else if (upperReqStatus.equals("CANCELLED")) {
                    increaseProductCount(order, upperReqStatus);
                    result.put("result", "VALID");

                } else {
                    result.put("result", "VALID");

                }
            } else if (upperOrderStatus.equals("CANCELLED")) {
                if (upperReqStatus.equals("PENDING") || upperReqStatus.equals("INPROGRESS")) {
                    result.put("result", "Cancelled Orders Can not be Initialized");

                }
                result.put("result", "Cancelled Orders Can not be Delivered");

            }
        }
        logger.info("Validated Order Message: " + result);
        return result;
    }

    public void increaseProductCount(JsonNode order, String upperReqStatus) {
        if (upperReqStatus.equals("CANCELLED")) {
            ArrayNode products = (ArrayNode) order.get("products");
            for (JsonNode json : products) {
                JsonNode prevJson = productDao.findProduct(json.get("productId").asText());
                Product prevProduct = Json.fromJson(prevJson, Product.class);
                JsonNode productJson = productDao.findProduct(json.get("productId").asText());
                Product product = Json.fromJson(productJson, Product.class);
                product.setStockQuantity(product.getStockQuantity() + Integer.parseInt(json.get("quantity").asText()));
                JsonNode proJson = Json.toJson(product);
                Document newDocument = Document.parse(proJson.toString());
                productDao.updateProduct(Document.parse(Json.toJson(prevProduct).toString()), newDocument);
                logger.info("Increase Product Count of: " + json.get("productId").asText());
            }
        }
    }

    public JsonNode removeOrderById(String id) {
        ObjectNode result = Json.newObject();
        logger.info("Request Sent to Order DAO");
        boolean product = orderDao.deleteOrder(id);
        if (id == null) {
            result.put("result", "ID is NULL");
        } else if (product) {
            result.put("result", "Order is Deleted - ID: " + id);
        } else {
            result.put("result", "Order Not Found");
        }
        logger.info("Response Returned From Order DAO: " + result);
        return result;
    }

}
