package com.shivmudra.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.shivmudra.dto.OrderDto;
import com.shivmudra.dto.OrderResponse;
import com.shivmudra.model.Response;

@Service
public interface OrderService {
	
	public ResponseEntity<Response<String>> createOrder( OrderDto dto) throws Exception;
	
	public ResponseEntity<Response<List<OrderResponse>>> getOrderDetails(String shirtSize,Pageable Pageable) throws Exception ;

	ResponseEntity<Response<Long>> getOrderCount(String shirtSize) throws Exception;
	

}
