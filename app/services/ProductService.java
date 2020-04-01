package services;

import com.fasterxml.jackson.databind.JsonNode;

public interface ProductService {

    JsonNode getAllProductsList();

    JsonNode removeProductById(String id);

    JsonNode addProduct(JsonNode reqData);

    JsonNode getProductById(String id);

}
