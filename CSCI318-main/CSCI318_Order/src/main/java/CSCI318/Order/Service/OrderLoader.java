/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Order.Service;

import CSCI318.Order.Model.OrderEvent;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
//import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 *
 * @author liamt
 */
@Component
public class OrderLoader implements CommandLineRunner {
    private OrderService orderService;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OrderLoader.class);
    @Autowired
    private StreamBridge streamBridge;

    
    public OrderLoader(OrderService orderService,StreamBridge streamBridge){
        this.orderService = orderService;
        this.streamBridge = streamBridge;
    }
    
     public void createRandomOrder()throws Exception{
        int quantity = 1;
        long custID = 0, productID = 0;
        while(custID%2 == 0){ //redo until odd to get correct id between 1-10
            custID = getRandom();
            //log.info("CustID: " + Long.toString(custID));
        }
        while(productID%2 == 0){ //redo until odd to get correct id between 1-10
            productID = getRandom();
            //log.info("ProductID: " + Long.toString(productID));
        }
        quantity = getRandom();
        //log.info("Quantity: " + Integer.toString(quantity));

        orderService.addNewOrder(custID, productID, quantity); 
    }
            
    @Transactional
    public void sendOrder(OrderEvent orderEvent){
        streamBridge.send("order-outbound", orderEvent);
        log.info("Order sent: " + orderEvent.toString());
    }
    
    @Override
    public void run(String... strings) throws Exception{
        log.info("Adding orders to the database...");
        
         try{
            while(!Thread.currentThread().isInterrupted()){
                createRandomOrder();            
                Thread.sleep(1200);
           }
        }
        catch(InterruptedException ignored){}
    }
    
    public int getRandom() {
        Random random = new Random();
    return random.nextInt(10 - 1) + 1;
    }
}
