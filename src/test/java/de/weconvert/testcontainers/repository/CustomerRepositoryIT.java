package de.weconvert.testcontainers.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	
	@Test
	void insertAndDeleteCustomer() {
		Customer customer = new Customer(UUID.randomUUID().toString(), "Hauke", "Sach");
		Customer savedCustomer = this.customerRepository.save(customer);
		
		Optional<Customer> customerOpt = customerRepository.findById(savedCustomer.getId());
		assertEquals(customerOpt.isPresent(), true);
		
		Customer foundCustomer = customerOpt.get();
		assertEquals(savedCustomer.getId(), foundCustomer.getId());
		assertEquals(savedCustomer.getFirstName(), foundCustomer.getFirstName());
		assertEquals(savedCustomer.getLastName(), foundCustomer.getLastName());
		
		this.customerRepository.delete(foundCustomer);
	}

}