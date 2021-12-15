/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSCI318.Customer.ErrorMessages;

/**
 *
 * @author liamt
 */
//returns and exception code when a customer cannot be found by a certain id
class CustomerNotFoundException extends RuntimeException {

  CustomerNotFoundException(Long id) {
    super("Could not find customer " + id);
  }
}
