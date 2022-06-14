package de.weconvert.testcontainers.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import de.weconvert.testcontainers.CustomerRepository;
import de.weconvert.testcontainers.model.Customer;
import de.weconvert.testcontainers.util.CentralPostgresqlContainer;

@SpringBootTest
@Testcontainers
class CustomerRepositoryIT extends CentralPostgresqlContainer {

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	void setUp() {
		customerRepository.deleteAll();
		customerRepository.save(Customer.builder().firstName("Martin").lastName("Klausen").build());
		customerRepository.save(Customer.builder().firstName("Nancy").lastName("Müller").build());
		customerRepository.save(Customer.builder().firstName("Karl").lastName("Vogel").build());
	}
	
	@Test
	void findAll() {
		List<Customer> customerList = customerRepository.findAll();
		assertThat(customerList).hasSize(3);
	}

	@Test
	void findByName() {
		List<Customer> customerList = customerRepository.findByFirstName("Martin");
		Customer customer = customerList.get(0);
		
		assertThat(customerList).hasSize(1);
		assertEquals(customer.getFirstName(), "Martin");
		assertEquals(customer.getLastName(), "Klausen");
	
	}

}