/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Order.Service;


import CSCI318.Order.Model.Order;
import CSCI318.Order.Model.OrderEvent;
import CSCI318.Order.Repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author liamt
 */

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OrderService.class);
    private ApplicationEventPublisher publisher;
    private OrderLoader orderLoader;
    
    
    
    //Sets the repository for Orders
    @Autowired
    public OrderService(@Lazy OrderLoader orderLoader, OrderRepository orderRepository, ApplicationEventPublisher publisher) {
       this.orderRepository = orderRepository;
       this.publisher = publisher;
       this.orderLoader = orderLoader;
    }
    
    public void recordEvent(Order order) {
        OrderEvent orderEvent = new OrderEvent(order);
        publisher.publishEvent(orderEvent);
    }
    
    //returns a list of all orders in the repositoy
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    
    //returns a single order from the repository
    public Optional<Order> getOrder(Long orderid){
        return orderRepository.findById(orderid);
    }
    
    public boolean validateCustomer(Long custID){
        RestTemplate restTemplate = new RestTemplate();
        //validate the customersID 
        
        log.info("Validating Customer: " + custID);
        String customerUrl = "http://localhost:1010/Customer/Validate/";
        ResponseEntity<String> customerResponse = restTemplate.getForEntity(customerUrl + custID , String.class);
        //log.info(customerResponse.toString());
        if(customerResponse.getBody().contains("false")){
            return false;
        }
        else
            return true;
    }
    
    public String getCustomerAddress(Long custID)throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
        
        String customerUrl = "http://localhost:1010/Customer/";
        ResponseEntity<String> customerResponse = restTemplate.getForEntity(customerUrl + custID , String.class);
        //log.info(customerResponse.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode custRoot = mapper.readTree(customerResponse.getBody());
        String address = custRoot.path("address").toString();
        address = address.replace("\"","");
        //log.info(address);
        return address;
    }
    
    public String getCustomerPhone(Long custID)throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
        
        String customerUrl = "http://localhost:1010/Customer/";
        ResponseEntity<String> customerResponse = restTemplate.getForEntity(customerUrl + custID , String.class);
        //log.info(customerResponse.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode custRoot = mapper.readTree(customerResponse.getBody());
        //log.warn(">>>" + custRoot.toString());
        //log.warn(">>>" + customerResponse.getBody());
        String phone = custRoot.path("contact").path("phone").toString();
        //log.warn(">>>" + phone);
        phone = phone.replace("\"","");
        return phone;
    }
    
    public boolean validateProduct(Long productID){
        RestTemplate restTemplate = new RestTemplate(); 
        
        log.info("Validating Product: " + productID);
        String productUrl = "http://localhost:2020/Product/Validate/";
        ResponseEntity<String> productResponse = restTemplate.getForEntity(productUrl + productID , String.class);
        //log.info(productResponse.toString());
        if(productResponse.getBody().contains("false")){
            return false;
        }
        else
            return true;
    }
    
    public boolean checkProductQuantity(Long productID, int quantity)throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "http://localhost:2020/Product/";
        ResponseEntity<String> productResponse = restTemplate.getForEntity(url + productID , String.class);
        //log.info(productResponse.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode custRoot = mapper.readTree(productResponse.getBody());
        int prodQuantity = custRoot.path("stockQuantity").asInt();
        //log.info("stockQuantity" + Integer.toString(prodQuantity));
        
        return quantity<=prodQuantity;
    }
    
    public double getProductPrice(Long productID)throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "http://localhost:2020/Product/";
        ResponseEntity<String> productResponse = restTemplate.getForEntity(url + productID , String.class);
        //log.info(productResponse.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode custRoot = mapper.readTree(productResponse.getBody());
        double price = custRoot.path("price").asDouble();
        //log.info(Double.toString(price));
        return price;
    }
    
    public String getProductName(Long productID)throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "http://localhost:2020/Product/";
        ResponseEntity<String> productResponse = restTemplate.getForEntity(url + productID , String.class);
        //log.info(productResponse.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode custRoot = mapper.readTree(productResponse.getBody());
        String name = custRoot.path("name").toString();
        name = name.replace("\"","");
        //log.info("ProductName: " + name);
        return name;
    } 
    
    public void updateStock(Long productID, int quantity){
        RestTemplate restTemplate = new RestTemplate();
        //Update the product DB to reflect the change in quantity
        String productUpdateUrl = "http://localhost:2020/Product/UpdateStock/";
        ResponseEntity<String> productUpdateResponse = restTemplate.getForEntity(productUpdateUrl + productID + "/" + quantity , String.class);
        //log.info(productUpdateResponse.toString());  
    }

    //adds a new order to the repository
    public OrderEvent addNewOrder(Long custID, Long productID, int quantity) throws JsonProcessingException{
        String address, phone, name;
        double price;
        
        if(validateCustomer(custID)){
            address = getCustomerAddress(custID);
            phone = getCustomerPhone(custID);
        }
        else{
            log.error("Customer ID: " + custID + " is invalid");
            return null;
        }
        
        if(validateProduct(productID)){
            if(checkProductQuantity(productID, quantity)){
                name = getProductName(productID);
                //log.info(name);
                price = getProductPrice(productID);
            }
            else{
                log.error("Product ID: " + productID + " has not got the sufficient quantity to make and order");
                return null;
            }    
        }
        else{
            log.error("Product ID: " + productID + " is invalid");
            return null;
        }
        
        Order order = new Order(custID, productID, quantity, address, phone, name, price);
        assert order != null;
        orderRepository.save(order);
        log.info("Order added" + order.toString());
        
        recordEvent(order);
        OrderEvent orderEvent = new OrderEvent(order);
        assert orderEvent != null;
        log.info("OrderEvent recorded" + order.toString());
       
        log.info("Updating product ID: " + productID + " stock level with -" + quantity);
        updateStock(productID, quantity);
        
        //send the orderEvent to the Order Loader for Kafka
        orderLoader.sendOrder(orderEvent);

        return orderEvent;
    }
    
    
}
