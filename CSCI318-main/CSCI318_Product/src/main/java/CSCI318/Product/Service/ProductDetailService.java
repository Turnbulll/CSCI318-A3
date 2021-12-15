/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Service;

import CSCI318.Product.Repository.ProductDetailRepository;
import CSCI318.Product.Model.ProductDetail;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    
    //Sets the repository for productDetail
    @Autowired
    public ProductDetailService(ProductDetailRepository productDetailRepository) {
       this.productDetailRepository = productDetailRepository;
    }

    //Returns a list of all productDetails
    public List<ProductDetail> getProductDetails() {
        return productDetailRepository.findAll();
    }
    
    //Returns a single productDetail
    public Optional<ProductDetail> getProductDetail(Long productDetailid){
        return productDetailRepository.findById(productDetailid);
    }

    //Function to add a new productDetail and inject to repository
    public void addNewProductDetails(ProductDetail[] productDetails){
        for (ProductDetail productDetail : productDetails) {
            addNewProductDetail(productDetail);
        }
    }
    
    //Function to add a new productDetail and inject to repository
    public void addNewProductDetail(ProductDetail productDetail){
        productDetailRepository.save(productDetail);
    }
    
    //Function to update productDetail info in repository
    @Transactional
    public void updateProductDetail(Long productDetailid, String description, String comment){
        ProductDetail productDetail = productDetailRepository.findById(productDetailid)
                .orElseThrow(()-> new IllegalStateException("productDetail with id: " + productDetailid + " does not exist" ));
        
        if(description != null && description.length() > 0 && !Objects.equals(productDetail.getDescription(), description)){
            productDetail.setDescription(description);
        }
        
        if(comment != null && comment.length() > 0 && !Objects.equals(productDetail.getComment(), comment)){
            productDetail.setComment(comment);
        }        
    }
    
}
