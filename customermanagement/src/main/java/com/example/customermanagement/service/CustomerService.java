package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author: Aditya kachhwal
 * Date: 08/08/20
 */
@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if(!customerOpt.isPresent()) {
            String errorMessage = "Customer not found for ID " + id;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return customerOpt.get();
    }

    public Customer updateCustomer(Customer customer,long Id) {
    	customer.setId(Id);
        getCustomer(customer.getId()); 
        return customerRepository.save(customer);
    }

    public Customer saveNewCustomer(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long customerId) {
        Customer customer = getCustomer(customerId);
        customerRepository.delete(customer);
    }
}
