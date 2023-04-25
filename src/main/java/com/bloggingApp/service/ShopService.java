package com.bloggingApp.service;

import java.util.List;

import com.bloggingApp.dto.ShopDto;

public interface ShopService {
	
	ShopDto createShop(Long customerId,ShopDto shopDto);
	List<ShopDto> findAllShops();
	ShopDto updateShop(Long customerId,Long id,ShopDto shopDto);
	ShopDto findShopById(Long customerId,Long id);
	List<ShopDto> findByCustomerId(Long customerId);
	void deleteById(Long customerId,Long id);
	List<ShopDto> findShops(int pageNo, int pageSize, String sortBy, String sortDir);

}
