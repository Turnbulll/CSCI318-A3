/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Customer.Service;

import CSCI318.Customer.Model.Contact;
import CSCI318.Customer.Model.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 *
 * @author liamt
 */
@Component
public class CustomerLoader implements CommandLineRunner {
    private CustomerService customerService;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CustomerLoader.class);
    
    public CustomerLoader(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @Override
    public void run(String... strings) throws Exception{
        log.info("Adding customers to the database...");
        customerService.addNewCustomer(new Customer("Dans dentist", "42 Wallaby Way, Sydney", "Australia", new Contact("Dan", "1800TEETH", "dansdentist@teeth.com", "Dentist")));
        customerService.addNewCustomer(new Customer("Jims Mowing", "Your front yard", "Australia", new Contact("Jim", "18000JIMS", "jimsmowing@mowing.com", "Prof grass cutter")));
        customerService.addNewCustomer(new Customer("Monsters Inc", "3 Scary Lane", "Monstropolis", new Contact("Sully", "1800SCARY11", "Scarefloor@MonstersINC.com", "CEO")));
        customerService.addNewCustomer(new Customer("Apple", "Cali rd", "America", new Contact("Steve Jobs", "18000APPLE", "Steve@apple.com", "CEO")));
        customerService.addNewCustomer(new Customer("KFC", "KFC RD", "America", new Contact("The Colonel", "1800CHICKEN", "TheColonel@KFC.com", "CEO")));
        log.info("Finished adding customers to the database");
    }        

}
