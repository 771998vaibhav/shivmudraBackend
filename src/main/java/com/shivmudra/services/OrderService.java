package com.shivmudra.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.shivmudra.dto.OrderDto;
import com.shivmudra.model.Response;

@Service
public interface OrderService {
	
	public ResponseEntity<Response<String>> createOrder( OrderDto dto) throws Exception;
	

}
