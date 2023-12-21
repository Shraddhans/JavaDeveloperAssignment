package com.assignment.shraddha.junit;

import com.assignment.shraddha.entity.Customer;
import com.assignment.shraddha.entity.Occupation;
import com.assignment.shraddha.repository.CustomerRepository;
import com.assignment.shraddha.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJUnitApplicationTests {
    @Autowired
    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepository;
    @Test
    public void saveCustomerTest() {
            Customer customer = new Customer();
            customer.setName("John Doe");
            customer.setEmail("johndoe@example.com");
            customer.setDob(String.valueOf(LocalDate.of(1990, 5, 15)));
            customer.setOccupation(Occupation.DEVELOPER);
            when(customerRepository.save(customer)).thenReturn(customer);
            assertEquals(customer, customerService.saveCustomer(customer));
        }
}
