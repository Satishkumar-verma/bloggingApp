package com.bloggingApp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bloggingApp.dto.CustomerDto;
import com.bloggingApp.dto.CustomerResponse;
import com.bloggingApp.entity.Customer;
import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	Customer dtoToEntity(CustomerDto customerDto) {
		Customer customer=new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setAddress(customerDto.getAddress());
		customer.setMobile(customerDto.getMobile());
		return customer;
	}
	CustomerDto entityToDto(Customer customer) {
		CustomerDto customerDto=new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setAddress(customer.getAddress());
		customerDto.setMobile(customer.getMobile());
		return customerDto;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = dtoToEntity(customerDto);
		Customer save = customerRepo.save(customer);
		CustomerDto entityToDto = entityToDto(save);
		return entityToDto;
	}

	@Override
	public CustomerDto findCustomerById(Long id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
		return entityToDto(customer);
	}

	@Override
	public List<CustomerDto> findAllCustomerDto() {
		List<Customer> findAll = customerRepo.findAll();
		return findAll.stream().map(customer->entityToDto(customer)).collect(Collectors.toList());
	}

	@Override
	public CustomerDto updateById(Long id,CustomerDto customerDto) {
		Customer customer = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setAddress(customerDto.getAddress());
		customer.setMobile(customerDto.getMobile());
		Customer save = customerRepo.save(customer);
		return entityToDto(save);
	}

	@Override
	public void deleteById(Long id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
		customerRepo.deleteById(id);
	}
	@Override
	public CustomerResponse findCustomerDto(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
		Page<Customer> findAll = customerRepo.findAll(pageRequest);
		List<Customer> content = findAll.getContent();
		List<CustomerDto> collect = content.stream().map(customer->entityToDto(customer)).collect(Collectors.toList());
		CustomerResponse response=new CustomerResponse();
		response.setContents(collect);
		response.setPageNo(findAll.getNumber());
		response.setPageSize(findAll.getSize());
		response.setTotalPage(findAll.getTotalPages());
		response.setTotalElements(findAll.getTotalElements());
		response.setLast(findAll.isLast());
		return response;
	}

}
