package com.example.customermanagement.controller;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.CustomerService;
import com.example.customermanagement.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Aditya
 * Date: 08/08/20
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @GetMapping("/customers") // get all customers
    public ResponseEntity<Object> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @GetMapping("/customers/{id}") // get customer by id
    public ResponseEntity<Object> getCustomer(@PathVariable Long id) {
    	if(id == null) {
    		System.out.println("Id is invalid");
    		return new ResponseEntity(ResponseUtil.createResponse("Invalid id"), HttpStatus.BAD_REQUEST);
    	}
    	try {
    		Customer customer = customerService.getCustomer(id);
    		return new ResponseEntity<>(customer, HttpStatus.OK);
    	}catch(Exception e) {
    		System.out.println("Error occurred while getting customer");
            e.printStackTrace();
            return new ResponseEntity<>(ResponseUtil.createResponse("Unexpected Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    }

    @PostMapping("/customers") // create customer
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        if(customer == null) {
            System.out.println("Customer is empty");
            return new ResponseEntity<>(ResponseUtil.createResponse("Customer is empty"), HttpStatus.BAD_REQUEST);
        }
        try{
            Customer savedCustomer = customerService.saveNewCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Error occurred while creating new customer");
            e.printStackTrace();
            return new ResponseEntity<>(ResponseUtil.createResponse("Unexpected Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}") // to update customer
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer,@PathVariable long id) {
        if(customer == null) {
            System.out.println("Customer is invalid");
            return new ResponseEntity<>(ResponseUtil.createResponse("Customer is invalid"), HttpStatus.BAD_REQUEST);
        }
        try{
            Customer savedCustomer = customerService.updateCustomer(customer,id);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Error occurred while updating");
            e.printStackTrace();
            return new ResponseEntity<>(ResponseUtil.createResponse("Unexpected Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}") // delete custome
    public ResponseEntity<Object> deleteCustomer(@PathVariable long id) {
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(ResponseUtil.createResponse("Customer deleted successfully"), HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Error occurred while deleting customer");
            e.printStackTrace();
            return new ResponseEntity<>(ResponseUtil.createResponse("Unexpected Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
