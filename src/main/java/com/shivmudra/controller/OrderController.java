package com.shivmudra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivmudra.dto.OrderDto;
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
			return new ResponseEntity<Response<String>>(resp, HttpStatus.BAD_REQUEST);
		}

	}

}
