package com.shivmudra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivmudra.dto.OrderDto;
import com.shivmudra.dto.OrderResponse;
import com.shivmudra.model.Response;
import com.shivmudra.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	@PostMapping("/createOrder")
	public ResponseEntity<Response<String>> createOrder(@RequestBody OrderDto dto) throws Exception {
		Response<String> res = new Response<String>();
		try {

			return orderService.createOrder(dto);
		}

		catch (Exception e) {
			Response<String> resp = new Response<String>();
			e.printStackTrace();
			return new ResponseEntity<Response<String>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/getAllOrder")
	public ResponseEntity<Response<List<OrderResponse>>> getOrderDetails(@RequestParam String shirtSize,Pageable Pageable ) throws Exception {
		Response<OrderResponse> res = new Response<OrderResponse>();
		try {

			return orderService.getOrderDetails(shirtSize,Pageable);
		}

		catch (Exception e) {
			Response<List<OrderResponse>>resp = new Response<List<OrderResponse>>();
			e.printStackTrace();
			return new ResponseEntity<Response<List<OrderResponse>>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/getAllOrderCount")
	public ResponseEntity<Response<Long>> getOrderDetails(@RequestParam String shirtSize) throws Exception {
		Response<Long> res = new Response<Long>();
		try {

			return orderService.getOrderCount(shirtSize);
		}

		catch (Exception e) {
			Response<Long>resp = new Response<Long>();
			e.printStackTrace();
			return new ResponseEntity<Response<Long>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
