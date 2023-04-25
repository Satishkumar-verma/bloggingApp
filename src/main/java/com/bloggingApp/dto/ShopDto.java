package com.bloggingApp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ShopDto {
    private Long id;
    private String shopName;
    private String itemName;
    private double price;
}

