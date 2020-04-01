package bo;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private List<JsonNode> products;
    private int custId;
    private String date;
    private int totalQuantity;
    private String orderStatus;
    private double totalPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<JsonNode> getProducts() {
        return products;
    }

    public void setProducts(List<JsonNode> products) {
        this.products = products;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.format(date);
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", custId=" + custId +
                ", date='" + date + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
