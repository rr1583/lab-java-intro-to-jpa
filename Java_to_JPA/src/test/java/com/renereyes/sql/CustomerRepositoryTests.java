package com.renereyes.sql.repositories;

import com.renereyes.sql.entities.Customer;
import com.renereyes.sql.enums.CustomerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTests {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("John Doe", CustomerStatus.GOLD, 10000);
        customerRepository.save(customer);
        assertThat(customer.getCustomerId()).isNotNull();
    }

    @Test
    public void testFindCustomerByName() {
        Customer customer = new Customer("Jane Doe", CustomerStatus.SILVER, 5000);
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findByCustomerName("Jane Doe");
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).getCustomerName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testFindCustomerByStatus() {
        Customer customer = new Customer("Jim Doe", CustomerStatus.NONE, 0);
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findByCustomerStatus(CustomerStatus.NONE);
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).getCustomerStatus()).isEqualTo(CustomerStatus.NONE);
    }
}
