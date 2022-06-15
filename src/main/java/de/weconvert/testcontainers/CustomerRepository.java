package de.weconvert.testcontainers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.weconvert.testcontainers.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

	List<Customer> findByFirstName(String firstName);
	
}
