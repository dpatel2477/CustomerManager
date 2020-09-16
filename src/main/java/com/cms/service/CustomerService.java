package com.cms.service;

import java.util.List;

import com.cms.entity.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theEmployee);
	
	public void deleteById(int theId);
	
	public List<Customer> searchBy(String theName);

}
