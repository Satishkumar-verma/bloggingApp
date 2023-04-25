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

import com.bloggingApp.dto.ShopDto;
import com.bloggingApp.service.ShopService;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    
    @Autowired
    private ShopService shopService;
    
    @PostMapping("/{customerId}/customer")
    public ResponseEntity<ShopDto> createShop(@PathVariable Long customerId, @RequestBody ShopDto shopDto) {
        ShopDto createdShop = shopService.createShop(customerId, shopDto);
        return new ResponseEntity<ShopDto>(createdShop,HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<ShopDto> getAllShops() {
       return shopService.findAllShops();
    }
  //http://localhost:8080/api/find/?pageNo=0&pageSize=4&sortBy=id&sortDir=asc
    @GetMapping("/find")
    public List<ShopDto> getCompleteShops(
    				@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
    				@RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
    				@RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
    				@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
    									) {
    	return shopService.findShops(pageNo,pageSize,sortBy,sortDir);
    }
    
    @GetMapping("/{id}/customers")
    public ResponseEntity<ShopDto> getShopById(@PathVariable Long customerId, @PathVariable Long id) {
        ShopDto shop = shopService.findShopById(customerId, id);
        return new ResponseEntity<ShopDto>(shop,HttpStatus.CREATED);
    }
    
    @GetMapping("/{customerId}/customer")
    public List<ShopDto> getShopsByCustomerId(@PathVariable Long customerId) {
      return shopService.findByCustomerId(customerId);
      
    }
    //http://localhost:8080/api/shops/1/customer/2
    @PutMapping("/{id}/customer/{customerId}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable Long customerId, @PathVariable Long id, @RequestBody ShopDto shopDto) {
        ShopDto updatedShop = shopService.updateShop(customerId, id, shopDto);
        return new ResponseEntity<ShopDto>(updatedShop,HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}/customer/{customerId}")
    public ResponseEntity<String> deleteShop(@PathVariable Long customerId, @PathVariable Long id) {
        shopService.deleteById(customerId, id);
        return new ResponseEntity<String>("deleted successfully",HttpStatus.CREATED);
    }
}

