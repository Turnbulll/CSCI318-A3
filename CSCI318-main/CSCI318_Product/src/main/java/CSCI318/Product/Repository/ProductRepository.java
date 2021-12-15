/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Product.Repository;

/**
 *
 * @author liamt
 */
import CSCI318.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//The Product repository
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
