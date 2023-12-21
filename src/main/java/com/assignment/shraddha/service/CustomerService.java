package com.assignment.shraddha.service;

import com.assignment.shraddha.entity.Customer;
import com.assignment.shraddha.entity.CustomerGroup;
import com.assignment.shraddha.entity.Occupation;
import com.assignment.shraddha.exception.EmailAlreadyExistsException;
import com.assignment.shraddha.exception.InvalidAgeException;
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
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if(customer.getEmail().endsWith("@hikeon.tech")){
            customer.setCustomerGroup(CustomerGroup.hikeon);
        }
        else if(customer.getOccupation() == Occupation.DEVELOPER || customer.getOccupation() == Occupation.CHEF){
            customer.setCustomerGroup(customer.getOccupation() == Occupation.DEVELOPER ? CustomerGroup.developer : CustomerGroup.chef);
        }
        else{
            customer.setCustomerGroup(CustomerGroup.NA);
        }

        LocalDate dob = LocalDate.parse(customer.getDob());
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dob, currentDate).getYears();
        if(age < 18){
            throw new InvalidAgeException("Customer must be 18 years or older");
        }
        return customerRepository.save(customer);
    }


}
