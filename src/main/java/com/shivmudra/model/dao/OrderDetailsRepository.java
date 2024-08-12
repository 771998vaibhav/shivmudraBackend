package com.shivmudra.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivmudra.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	
	List<OrderDetails>findAll();
	
	Page<OrderDetails>getBySize(String size,Pageable pageable);
	
	Long countBySize(String size);
	

}
