package CSCI318.BusinessIntelligence.Controller;

import CSCI318.BusinessIntelligence.Service.ProductInteractiveQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryController {

    @Autowired
    ProductInteractiveQuery productInteractiveQuery;

    @GetMapping("/Product/{productName}")
    long getProductQuantityByName(@PathVariable String productName) {
        return productInteractiveQuery.getProductQuantity(productName);
    }

    @GetMapping("/Product")
    List<String> getAllProduct() {
        return productInteractiveQuery.getProductNames();
    }

    @GetMapping("/CustomersProducts/{customerID}")
    List<String> getAllProductForCustomer(@PathVariable String customerID) {
        return productInteractiveQuery.getAllProductForCustomer(customerID);
    }

    @GetMapping("/CustomersOrderValue/{customerID}")
    double getTotalOrderValueForCustomer(@PathVariable String customerID) {
        return productInteractiveQuery.getTotalOrderValueForCustomer(customerID);
    }

}
