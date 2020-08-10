package com.example.customermanagement.repository;

import com.example.customermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author: Aditya
 * Date: 08/08/20
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findAll();

    public Optional<Customer> findById(Long id);
}
