/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Order.Model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author liamt
 */
@Entity
@Table(name = "OrderTable")
public class Order {
    //Variables
    private @Id @GeneratedValue long orderId;
    private long customerId;
    private String customerAddress;
    private String customerPhone;
    private long productId;
    private String productName;
    private double productPrice;
    private int quantity;

    //Default contstructor
    public Order(){};
    
    //contstuctor
    public Order(long custID, long productID, int quantity, String customerAddress, String customerPhone, String productName, double productPrice) {
        this.customerId = custID;
        this.productId = productID;
        this.quantity = quantity;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
    //Getters
    public long getId() {
        return orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getProductId() {
        return productId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public double getProductPrice() {
        return productPrice;
    }
    
    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.orderId);
        hash = 53 * hash + Objects.hashCode(this.productId);
        hash = 53 * hash + Objects.hashCode(this.customerId);
        hash = 53 * hash + Objects.hashCode(this.customerAddress);
        hash = 53 * hash + Objects.hashCode(this.customerPhone);
        hash = 53 * hash + Objects.hashCode(this.productName);
        hash = 53 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.customerAddress, other.customerAddress)) {
            return false;
        }
        if (!Objects.equals(this.customerPhone, other.customerPhone)) {
            return false;
        }
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        return Objects.equals(this.orderId, other.orderId);
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId + ", productName="+ productName+ ", productPrice=" + productPrice  + ", quantity=" + quantity + '}';
    }                                                                                                                       
}
