/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Controller;
import CSCI318.Product.Model.Product;
import CSCI318.Product.Service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
  private final ProductService productService;

  //Set the product service
  ProductController(ProductService productService) {
    this.productService = productService;
  }

  //Get request that returns all products
  @GetMapping("/Product")
  List<Product> getProducts() {
    return productService.getProducts();
  }
  
  //Get request that returns a single product from id
  @GetMapping("/Product/{productId}")
  public Optional<Product> getProduct(@PathVariable("productId") Long productId){
      return productService.getProduct(productId);
  }
  
  //Post request that creates a list of new products
  @PostMapping("/Product/New")
  public void newProducts(@RequestBody Product[] products){
      productService.addNewProducts(products);
  }
  
  //Put request that updates product information
  @PutMapping("Product/{productid}")
  public void updateProduct(
        @PathVariable("productid") Long productid,
        @RequestParam(required = false) String productCategory,
        @RequestParam(required = false) String productName,
        @RequestParam(required = false) double price,
        @RequestParam(required = false) int stockQuantity){
      productService.updateProduct(productid, productCategory, productName, price, stockQuantity);
  }
 
  
  //Put request that updates contact for the product by id
  @PutMapping("/Product/{id}/ProductDetail/{productDetailId}")
  public void updateProductProductDetail(@PathVariable Long id, @PathVariable Long productDetailId) {
        productService.updateProductProductDetails(id, productDetailId);
  }
  
  //Get request returns if a product is valid
  @GetMapping("/Product/Validate/{productId}")
  public boolean validateInventory(@PathVariable("productId") Long productId){
      return productService.validateInventory(productId);
  }

  @GetMapping("/Product/UpdateStock/{productId}/{quantity}")
  public void updateStock(@PathVariable("productId") Long productId, @PathVariable("quantity") int quantity){
      productService.updateStock(productId, quantity);
  }
}
