/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author liamt
 */

@Entity
@Table(name = "ProductDetailTable" )
public class ProductDetail {
    //Variables
    private @Id @GeneratedValue Long productDetailId;
    private String description;
    private String comment;
    
    
    @OneToOne(mappedBy = "productDetail")
    @JsonIgnore
    private Product product;
    
    
    public ProductDetail(){};
    
    //Contstuctor
    public ProductDetail(String description, String comment) {
        this.description = description;
        this.comment = comment;
    }
    
    //Setters
    public void setProductDetaiId(Long productDetaiId) {
        this.productDetailId = productDetaiId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    
    //Getters
    public Long getProductDetaiId() {
        return productDetailId;
    }
    public String getDescription() {
        return description;
    }
    public String getComment() {
        return comment;
    }
    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.productDetailId);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.comment);
        hash = 41 * hash + Objects.hashCode(this.product);
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
        final ProductDetail other = (ProductDetail) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.productDetailId, other.productDetailId)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "productDetaiId=" + productDetailId + ", description=" + description + ", comment=" + comment + ", product=" + product + '}';
    }
    
    
    
}
