package CSCI318.BusinessIntelligence.Model;

public class CustomerProductOrders {
    private String customerID;
    private String productName;
    private double price;
    private int quantity;

    public CustomerProductOrders() {
    }

    public CustomerProductOrders(String customerID, String productName, double price, int quantity) {
        this.customerID = customerID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "CustomerProductOrders{" +
        "customerID='" + customerID + '\'' +
        "productName='" + productName + '\'' +
        "price='" + price + '\'' +
        ", quantity=" + quantity +
                '}';
    }
}
