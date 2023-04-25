package com.bloggingApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	
}
