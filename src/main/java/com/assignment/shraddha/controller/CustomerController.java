package com.assignment.shraddha.controller;

import com.assignment.shraddha.entity.Customer;
import com.assignment.shraddha.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

}
