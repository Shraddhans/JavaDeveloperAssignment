package com.assignment.shraddha.controller;

import com.assignment.shraddha.entity.Customer;
import com.assignment.shraddha.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addCustomer(@Valid @RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.saveCustomer(customer);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedCustomer.getId());
            response.put("name", savedCustomer.getName());
            response.put("email", savedCustomer.getEmail());
            response.put("dob", savedCustomer.getDob());
            response.put("occupation", savedCustomer.getOccupation());
            response.put("customerGroup", savedCustomer.getCustomerGroup());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
}
