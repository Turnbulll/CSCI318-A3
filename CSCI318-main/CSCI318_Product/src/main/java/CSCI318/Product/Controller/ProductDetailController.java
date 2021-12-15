/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CSCI318.Product.Model.ProductDetail;
import CSCI318.Product.Service.ProductDetailService;

/**
 *
 * @author liamt
 */
@RestController
public class ProductDetailController {
    
    private ProductDetailService productDetailService;

  //Set the productDetail service
  ProductDetailController(ProductDetailService productDetailService) {
    this.productDetailService = productDetailService;
  }

  //Send a GET request for all productDetails
  @GetMapping("/ProductDetail")
  List<ProductDetail> getProductDetails() {
    return productDetailService.getProductDetails();
  }
  
  //Send a GET request for a single productDetail by id
  @GetMapping("/ProductDetail/{productDetailid}")
  public Optional<ProductDetail> getProductDetail(@PathVariable("productDetailid") Long productDetailid){
      return productDetailService.getProductDetail(productDetailid);
  }
  
  //Creates a POST request for a new productDetail
  @PostMapping("/ProductDetail/New")
  public void newProductDetails(@RequestBody ProductDetail[] productDetails){
    productDetailService.addNewProductDetails(productDetails);
  }
  
  //Creates a PUT request to update a productDetail
  @PutMapping("ProductDetail/{productDetailid}")
  public void updateProductDetail(
        @PathVariable("productDetailid") Long productDetailid,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String comment){
      productDetailService.updateProductDetail(productDetailid, description, comment);
  }

}
