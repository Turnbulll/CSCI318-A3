/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.BusinessIntelligence.Model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author liamt
 */
@Entity
@Table(name = "OrderEventTable")
public class OrderEvent {
    //Variables
    private @Id long orderId;
    private String customerId;
    private String customerAddress;
    private String customerPhone;
    private String productId;
    private String productName;
    private double productPrice;
    private int quantity;

    //Default contstructor
    public OrderEvent(){};
    
    //Getters
    public long getId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
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

    
    //setters
    public void setId(long orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCustomerAddress(String address) {
        this.customerAddress = address;
    }

    public void setCustomerPhone(String phone) {
        this.customerPhone = phone;
    }

    public void setProductPrice(double price) {
        this.productPrice = price;
    }
    
    public void setProductName(String name) {
        this.productName = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        hash = 53 * hash + Objects.hashCode(this.productPrice);
        hash = 53 * hash + Objects.hashCode(this.quantity);
        hash = 53 * hash + Objects.hashCode(new Date());
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
        final OrderEvent other = (OrderEvent) obj;
        if (!Objects.equals(this.quantity, other.quantity)) {
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
