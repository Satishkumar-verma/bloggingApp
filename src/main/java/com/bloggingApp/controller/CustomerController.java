package com.bloggingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.dto.CustomerDto;
import com.bloggingApp.dto.CustomerResponse;
import com.bloggingApp.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		CustomerDto savedCustomer = customerService.createCustomer(customerDto);
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
		CustomerDto foundCustomer = customerService.findCustomerById(id);
		return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
	}
	
	@GetMapping()
	public List<CustomerDto> findAllCustomers() {
		return customerService.findAllCustomerDto();
		
	}
	@GetMapping("/find")
	public ResponseEntity<CustomerResponse> findCustomers(
			@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir) {
	CustomerResponse findCustomerDto = customerService.findCustomerDto(pageNo,pageSize,sortBy,sortDir);
	return new ResponseEntity<CustomerResponse>(findCustomerDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
		CustomerDto updatedCustomer = customerService.updateById(id, customerDto);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Long id) {
		customerService.deleteById(id);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.NO_CONTENT);
	}
}

