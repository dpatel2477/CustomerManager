package com.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.entity.Customer;
import com.cms.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(CustomerService theEmployeeService) {
		this.customerService=theEmployeeService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		//get customers
		List<Customer> theCustomers= customerService.findAll();
		// add to the spring model
		theModel.addAttribute("customers", theCustomers);
		return "customers/list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomers= new Customer();
		theModel.addAttribute("customer", theCustomers);
		return "customers/customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer
		customerService.save(theCustomer);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer form the service
		Customer theCustomer = customerService.findById(theId);
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form
		return "customers/customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteById(theId);
		// redirect to /customers/list
		return "redirect:/customers/list";
	}
	
	@GetMapping("/search")
	public String delete(@RequestParam("customerName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Customer> theCustomers = customerService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("customers", theCustomers);
		
		// send to /employees/list
		return "/customers/list-customers";
		
	}

}
