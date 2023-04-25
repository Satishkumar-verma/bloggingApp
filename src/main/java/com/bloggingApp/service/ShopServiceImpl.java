package com.bloggingApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bloggingApp.dto.ShopDto;
import com.bloggingApp.entity.Customer;
import com.bloggingApp.entity.Shop;
import com.bloggingApp.exception.BlopApiException;
import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.repository.CustomerRepository;
import com.bloggingApp.repository.ShopReposiotry;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ShopReposiotry shopRepo;
	
	Shop dtoToEntity(ShopDto shopDto){
		Shop shop=new Shop();
		shop.setShopName(shopDto.getShopName());
		shop.setItemName(shopDto.getItemName());
		shop.setPrice(shopDto.getPrice());
		return shop;
	}
	ShopDto entityToDto(Shop shop){
		ShopDto shopDto=new ShopDto();
		shopDto.setId(shop.getId());
		shopDto.setShopName(shop.getShopName());
		shopDto.setItemName(shop.getItemName());
		shopDto.setPrice(shop.getPrice());
		return shopDto;
	}

	@Override
	public ShopDto createShop(Long customerId, ShopDto shopDto) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		Shop shop = dtoToEntity(shopDto);
		shop.setCustomer(customer);
		Shop save = shopRepo.save(shop);
		ShopDto dto = entityToDto(save);
		return dto;
	}

	@Override
	public List<ShopDto> findAllShops() {
		List<Shop> findAll = shopRepo.findAll();
		return findAll.stream().map(shop->entityToDto(shop)).collect(Collectors.toList());
	}

	@Override
	public ShopDto updateShop(Long customerId,Long id, ShopDto shopDto) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		Shop shop = shopRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Shop", "id", id));
		if(!shop.getCustomer().getId().equals(customer.getId())) {
			throw new BlopApiException(HttpStatus.BAD_REQUEST,"id not foundt");
		}
		
		shop.setShopName(shopDto.getShopName());
		shop.setItemName(shopDto.getItemName());
		shop.setPrice(shopDto.getPrice());
		Shop save = shopRepo.save(shop);
		return entityToDto(save);
	}

	@Override
	public ShopDto findShopById(Long customerId, Long id) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		Shop shop = shopRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Shop", "id", id));
		if(!shop.getCustomer().getId().equals(customer.getId())) {
			throw new BlopApiException(HttpStatus.BAD_REQUEST,"id not foundt");
		}
		
		return entityToDto(shop);
	}

	@Override
	public 	List<ShopDto> findByCustomerId(Long customerId) {
		List<Shop> findByCustomerId = shopRepo.findByCustomerId(customerId);
		return findByCustomerId.stream().map(e->entityToDto(e)).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long customerId, Long id) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		Shop shop = shopRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Shop", "id", id));
		if(!shop.getCustomer().getId().equals(customer.getId())) {
			throw new BlopApiException(HttpStatus.BAD_REQUEST,"id not foundt");
		}
		
		shopRepo.deleteById(id);
	}
	@Override
	public List<ShopDto> findShops(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
		Page<Shop> findAll = shopRepo.findAll(pageRequest);
		List<Shop> content = findAll.getContent();
		
		return content.stream().map(e->entityToDto(e)).collect(Collectors.toList());
	}

}
