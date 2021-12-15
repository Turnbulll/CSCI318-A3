/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author liamt
 */
@Entity
@Table(name = "ProductTable" )
public class Product {
    //Variables
    private @Id @GeneratedValue Long productId;
    private String productCategory;
    private String name;
    private double price;
    private int stockQuantity;
    
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "product_detail_id", referencedColumnName= "productDetailId")
    private ProductDetail productDetail;
    /*
    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private Order order;
    */
    
    public Product(){};

    //Contstructor
    public Product(String productCategory, String name, double price, int stockQuantity) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    public Product(String productCategory, String name, double price, int stockQuantity, ProductDetail productDetail) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
    }
    
    //Setters
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
    
    
    //Getters
    public Long getProductId() {
        return productId;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public ProductDetail getProductDetail() {
        return productDetail;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.productId);
        hash = 47 * hash + Objects.hashCode(this.productCategory);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 47 * hash + this.stockQuantity;
        hash = 47 * hash + Objects.hashCode(this.productDetail);
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
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.stockQuantity != other.stockQuantity) {
            return false;
        }
        if (!Objects.equals(this.productCategory, other.productCategory)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.productDetail, other.productDetail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productCategory=" + productCategory + ", name=" + name + ", price=" + price + ", stockQuantity=" + stockQuantity + ", productDetail=" + productDetail + '}';
    }
    
    
}
