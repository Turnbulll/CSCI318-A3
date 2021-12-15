/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Customer.Model;

import CSCI318.Customer.Model.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author liamt
 */
@Entity
@Table(name = "CustomerTable")
public class Customer {
    //Variables
    private @Id @GeneratedValue Long customerId;
    private String companyName;
    private String address;
    private String country;
    
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "contact_id", referencedColumnName= "contactId")
    private Contact contact;
   
    
    //Default contstructor
    Customer(){}
    
    //Constructor
    public Customer(String companyName, String address, String country){
        this.companyName = companyName;
        this.address = address;
        this.country = country;
    }
    
    public Customer(String companyName, String address, String country, Contact contact){
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.contact = contact;
    }
    
    //Getters for Customer
    public Long getId() {
        return this.customerId;
    }
    public String getCompanyName(){
        return companyName;
    }
    public String getAddress(){
        return address;
    }
    public String getCountry(){
        return country;
    }
    
    public Contact getContact() {
        return contact;
    }
    /*
    public Order getOrder() {
        return order;
    }
    */
    
    //Setters for Customer
    public void setId(Long id) {
        this.customerId = id;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    /*
    public void setOrder(Order order) {
        this.order = order;
    }
    */
    
    //Compare Customer
    @Override
    public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Customer))
      return false;
    Customer customer = (Customer) o;
    return Objects.equals(this.customerId, customer.customerId) &&
           Objects.equals(this.companyName, customer.companyName) &&
           Objects.equals(this.address, customer.address) && 
           Objects.equals(this.country, customer.country) &&
           Objects.equals(this.contact, customer.contact);
    }
    
    //Hashcode for Customer
    @Override
    public int hashCode() {
      return Objects.hash(this.customerId, this.companyName, this.address, this.country, this.contact);
    }
    
    //ToString for Customer
    @Override
    public String toString() {
      return "Contact{" + "id=" + this.customerId + 
                          ", companyName='" + this.companyName + '\'' + 
                          ", address='" + this.address + '\'' + 
                          ", country='" + this.country + '\'' + 
                          ", contact='" + this.contact + '\'' +
                          '}';
    }
}