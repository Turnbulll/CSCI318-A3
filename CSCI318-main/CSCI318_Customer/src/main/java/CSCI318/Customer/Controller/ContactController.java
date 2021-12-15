/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Customer.Controller;
import CSCI318.Customer.Service.ContactService;
import CSCI318.Customer.Model.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author liamt
 */
@RestController
class ContactController {

  private ContactService contactService;

  //Set the contact service
  ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  //Send a GET request for all contacts
  @GetMapping("/Contact")
  List<Contact> getContacts() {
    return contactService.getContacts();
  }
  
  //Send a GET request for a single contact by id
  @GetMapping("/Contact/{contactid}")
  public Optional<Contact> getContact(@PathVariable("contactid") Long contactid){
      return contactService.getContact(contactid);
  }
  
  //Creates a POST request for a new contact
  @PostMapping("/Contact/New")
  public void newContacts(@RequestBody Contact[] contacts){
    contactService.addNewContacts(contacts);
  }
  
  //Creates a PUT request to update a contact
  @PutMapping("Contact/{contactid}")
  public void updateContact(
        @PathVariable("contactid") Long contactid,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String position){
      contactService.updateContact(contactid, name, phone, email, position);
  }

}
