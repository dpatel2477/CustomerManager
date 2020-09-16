package com.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	// method to sort by last name
	public List<Customer> findAllByOrderByLastNameAsc();
	
	public List<Customer> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);
	
	

}
