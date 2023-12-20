package com.assignment.shraddha.service;

import com.assignment.shraddha.entity.Customer;
import com.assignment.shraddha.entity.CustomerGroup;
import com.assignment.shraddha.entity.Occupation;
import com.assignment.shraddha.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer saveCustomer(Customer customer){
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        if(customer.getEmail().endsWith("@hikeon.tech")){
            customer.setCustomerGroup(CustomerGroup.hikeon);
        }
        else if(customer.getOccupation() == Occupation.developer || customer.getOccupation() == Occupation.chef){
            customer.setCustomerGroup(customer.getOccupation() == Occupation.developer ? CustomerGroup.developer : CustomerGroup.chef);
        }
        else{
            customer.setCustomerGroup(CustomerGroup.NA);
        }
        int age = Period.between(customer.getDob(), LocalDate.now()).getYears();
        if(age < 18){
            throw new RuntimeException("Customer must be 18 years or older");
        }
        return customerRepository.save(customer);
    }

}
