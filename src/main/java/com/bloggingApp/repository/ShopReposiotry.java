package com.bloggingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entity.Shop;

public interface ShopReposiotry extends JpaRepository<Shop,Long> {

	List<Shop> findByCustomerId(Long customerId);

}
