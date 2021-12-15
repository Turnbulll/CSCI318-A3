/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Order.Controller;

import CSCI318.Order.Model.Order;
import CSCI318.Order.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author liamt
 */
@RestController
public class OrderController {
   private final OrderService orderService;

  //Set the order service
  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  //Get request that returns all orders
  @GetMapping("/Order")
  List<Order> getOrders() {
    return orderService.getOrders();
  }
  
  //Get request that returns a single order from id
  @GetMapping("/Order/{orderid}")
  public Optional<Order> getOrder(@PathVariable("orderId") Long orderId){
      return orderService.getOrder(orderId);
  }

  //Post request that creates a new order
  @PostMapping("/Order/CreateOrder")
  public void newOrders(@RequestParam Long custID, @RequestParam long productID, @RequestParam int quantity) throws JsonProcessingException{
        orderService.addNewOrder(custID, productID, quantity);
  }
}
