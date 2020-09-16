package com.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.dao.CustomerRepository;
import com.cms.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository theEmployeeRepository) {
		this.customerRepository= theEmployeeRepository;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Customer findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Customer> result = customerRepository.findById(theId);
		
		Customer theCustomer= null;
		if(result.isPresent()) {
			theCustomer=result.get();
		}else {
			throw new RuntimeException("Did not find customer id - " + theId);
		}
		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		customerRepository.save(theCustomer);
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> searchBy(String theName) {
		
List<Customer> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = customerRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		}
		else {
			results = findAll();
		}
		
		return results;
		// TODO Auto-generated method stub
	
	}

}
