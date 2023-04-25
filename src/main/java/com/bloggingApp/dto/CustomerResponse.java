package com.bloggingApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerResponse {
	
	private List<CustomerDto> contents;
	private int pageNo;
	private int pageSize;
	private int totalPage;
	private long totalElements;
	private boolean isLast;

}
