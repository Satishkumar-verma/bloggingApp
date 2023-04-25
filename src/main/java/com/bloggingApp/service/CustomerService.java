package com.bloggingApp.service;

import java.util.List;

import com.bloggingApp.dto.CustomerDto;
import com.bloggingApp.dto.CustomerResponse;

public interface CustomerService {

	
	CustomerDto createCustomer(CustomerDto customerDto);
	CustomerDto findCustomerById(Long id);
	List<CustomerDto> findAllCustomerDto();
	CustomerDto updateById(Long id,CustomerDto customerDto);
	void deleteById(Long id);
	CustomerResponse findCustomerDto(int pageNo, int pageSize, String sortBy, String sortDir);
}
