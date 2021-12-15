/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Service;

import CSCI318.Product.Repository.ProductRepository;
import CSCI318.Product.Model.Product;
import CSCI318.Product.Model.ProductDetail;
import CSCI318.Product.Repository.ProductDetailRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    
    //Sets the repository for product
    @Autowired
    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
       this.productRepository = productRepository;
       this.productDetailRepository = productDetailRepository;
    }

    //returns a list of all products in the repositoy
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    
    //returns a single Product from the repository
    public Optional<Product> getProduct(Long productId){
        return productRepository.findById(productId);
    }

    //adds a list of new products to the repository
    public void addNewProducts(Product[] products){
        for (Product product : products) {
            addNewProduct(product);
        }
    }
    
    //adds a new Product to the repository
    public void addNewProduct(Product product){
        productRepository.save(product);
    }

    //updates a Product within the repository
    @Transactional
    public void updateProduct(Long productId, String productCategory, String name, double price, int stockQuantity){
        Product Product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalStateException("Product with id: " + productId + " does not exist" ));
        
        if(productCategory != null && productCategory.length() > 0 && !Objects.equals(Product.getProductCategory(), productCategory)){
            Product.setProductCategory(productCategory);
        }
        
        if(name != null && name.length() > 0 && !Objects.equals(Product.getName(), name)){
            Product.setName(name);
        }
        
        if(price != 0 && !Objects.equals(Product.getPrice(), price)){
            Product.setPrice(price);
        }

        if(!Objects.equals(Product.getStockQuantity(), stockQuantity)){
            Product.setStockQuantity(stockQuantity);
        }        
    }
    
    //updates productDetails for the Product by id
    public Product updateProductProductDetails(@PathVariable Long id, @PathVariable Long productDetailId) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(productDetailId).orElseThrow(RuntimeException::new);
        product.setProductDetail(productDetail);
        return productRepository.save(product);
    }

    public boolean validateInventory(Long productId) {
        return productRepository.findById(productId).isPresent();
    }   
    
    public void updateStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalStateException("Product with id: " + productId + " does not exist" ));

        product.setStockQuantity(product.getStockQuantity() - quantity);        
    }
}
