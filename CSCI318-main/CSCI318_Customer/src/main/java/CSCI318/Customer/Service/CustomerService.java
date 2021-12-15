/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Customer.Service;

import CSCI318.Customer.Model.Contact;
import CSCI318.Customer.Repository.CustomerRepository;
import CSCI318.Customer.Model.Customer;
import CSCI318.Customer.Repository.ContactRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author liamt
 */
@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;
   private final ContactRepository contactRepository;
    
    //sets the customer repository
    @Autowired
    public CustomerService(CustomerRepository customerRepository, ContactRepository contactRepository) {
       this.customerRepository = customerRepository;
       this.contactRepository = contactRepository;
    }
    
    //returns a list of all customers in the repositoy
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    
    //returns a single customer from the repository
    public Optional<Customer> getCustomer(Long customerid){
        return customerRepository.findById(customerid);
    }

    //adds a list of new customers to the repository
    public void addNewCustomers(Customer[] customers){
        for (Customer customer : customers) {
            addNewCustomer(customer);
        }
    }
    
    //adds a new customer to the repository
    public void addNewCustomer(Customer customer){
        customerRepository.save(customer);
    }
    
    //updates a customer within the repository
    @Transactional
    public void updateCustomer(Long customerid, String companyName, String address, String country){
        Customer customer = customerRepository.findById(customerid)
                .orElseThrow(()-> new IllegalStateException("customer with id " + customerid + " does not exist" ));
        
        if(companyName != null && companyName.length() > 0 && !Objects.equals(customer.getCompanyName(), companyName)){
            customer.setCompanyName(companyName);
        }
        
        if(address != null && address.length() > 0 && !Objects.equals(customer.getAddress(), address)){
            customer.setAddress(address);
        }
        
        if(country != null && country.length() > 0 && !Objects.equals(customer.getCountry(), country)){
            customer.setCountry(country);
        }
        customerRepository.save(customer);
    }
    
    //updates contact for the customer by id
    public Customer updateCustomerContact(@PathVariable Long id, @PathVariable Long contactId) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        Contact contact = contactRepository.findById(contactId).orElseThrow(RuntimeException::new);
        customer.setContact(contact);
        return customerRepository.save(customer);
    }

    public boolean validateCustomer(Long custID) {
        return customerRepository.findById(custID).isPresent();      
    }
}
