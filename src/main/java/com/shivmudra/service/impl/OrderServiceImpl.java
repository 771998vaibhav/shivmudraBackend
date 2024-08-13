package com.shivmudra.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shivmudra.dto.OrderDto;
import com.shivmudra.dto.OrderResponse;
import com.shivmudra.dto.PaymentPhotoDto;
import com.shivmudra.model.OrderDetails;
import com.shivmudra.model.Response;
import com.shivmudra.model.dao.OrderDetailsRepository;
import com.shivmudra.services.OrderService;
import com.shivmudra.services.UploadPhotoService;
import com.shivmudra.utils.CommonUtils;
import com.shivmudra.utils.DateUtils;
import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.Base64;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Value("${temp-doc-location}")
	private String tempDocLocation;

	@Value("${shirt-payment-bucket}")
	private String bucketName;

	@Autowired
	UploadPhotoService uploadPhotoService;

	@Override
	public ResponseEntity<Response<String>> createOrder(OrderDto dto) throws Exception {
		Response<String> res = new Response<String>();
		try {

			if (dto.getName() == null) {

				res.setMessage("Name is mandatory");
				res.setStatus(400);
				return new ResponseEntity<Response<String>>(res, HttpStatus.BAD_REQUEST);

			}

			if (dto.getMobileNo() == null) {
				res.setMessage("Mobile No is mandatory");
				res.setStatus(400);
				return new ResponseEntity<Response<String>>(res, HttpStatus.BAD_REQUEST);

			}

			if (dto.getSize() == null) {
				res.setMessage("please select T-Shirt size");
				res.setStatus(400);
				return new ResponseEntity<Response<String>>(res, HttpStatus.BAD_REQUEST);

			}

			if (dto.getAddress() == null) {
				res.setMessage("please select Address size");
				res.setStatus(400);
				return new ResponseEntity<Response<String>>(res, HttpStatus.BAD_REQUEST);
			}

			if (dto.getPhoto() == null) {

				res.setMessage("please select screenshot photo");
				res.setStatus(400);
				return new ResponseEntity<Response<String>>(res, HttpStatus.BAD_REQUEST);
			}

			OrderDetails details = new OrderDetails();

			details.setName(dto.getName());
			details.setAddress(dto.getAddress());
			details.setMobileNo(dto.getMobileNo());
			details.setSize(dto.getSize());
			details.setOrderDate(new Date());
			details.setCreatedDate(new Date());
			if (dto.getCity() != null) {
				details.setCity(dto.getCity());
			}

			if (dto.getPincode() != null) {
				details.setPincode(dto.getPincode());
			}

			String url = this.uploadFileToLocal(dto.getPhoto());
			if (url != null) {
				details.setPaymentUrl(url);
				details.setIsPaymentPhotoUploaded(Boolean.TRUE);
			} else {
				details.setPaymentUrl(null);
				details.setIsPaymentPhotoUploaded(Boolean.FALSE);
			}

			details = orderDetailsRepository.save(details);

			System.out.println("id" + details.getId());
			res.setStatus(200);
			res.setMessage("Order Placed With Order Id:" + details.getId());

			return new ResponseEntity<Response<String>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);

			return new ResponseEntity<Response<String>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private String uploadFileToLocal(PaymentPhotoDto dto) {
		try {
			String[] encoded = dto.getFile().split(",");
			byte[] decodedBytes = null;
			if (CommonUtils.isNotNull(encoded) && encoded.length > 0) {
				decodedBytes = Base64.getDecoder().decode(encoded[1]);
			}
			File file = new File(tempDocLocation + dto.getFileName());
			FileUtils.writeByteArrayToFile(file, decodedBytes);

			Date date = new Date();

			String key = "ShivMudra/" + DateUtils.getCurYear() + "/" + DateUtils.getCurMonth() + "/" + dto.getFileName()
					+ "_" + date.getTime() + "." + dto.getExtension();

			System.out.println("key::::" + key);

			try {
				ObjectMetadata sobjectMetaData = new ObjectMetadata();
				sobjectMetaData.setContentType("application/octet-stream");
				sobjectMetaData.setContentLength(file.length());
				uploadPhotoService.putObject(bucketName, key, new FileInputStream(file), sobjectMetaData);
				String url = uploadPhotoService.getUrl(bucketName, key).toString();
				System.out.println("URL :::: " + url);

				return url;
			} catch (IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResponseEntity<Response<List<OrderResponse>>> getOrderDetails(String shirtSize, Pageable Pageable)
			throws Exception {
		Response<List<OrderResponse>> res = new Response<List<OrderResponse>>();
		try {
			List<OrderResponse> List = new ArrayList<OrderResponse>();
			Page<OrderDetails> details;
			
			if (shirtSize != null && !shirtSize.isEmpty()) {

			
				details = orderDetailsRepository.getBySize(shirtSize, Pageable);

			} else {
				
				details = orderDetailsRepository.findAll(Pageable);
			}

			if (details != null && details.getSize() > 0) {
				for (OrderDetails order : details) {

					OrderResponse response = new OrderResponse();

					response.setOrderId(order.getId().toString());
					if (order.getAddress() != null) {
						response.setAddress(order.getAddress());
					}

					if (order.getName() != null) {
						response.setName(order.getName());
					}

					if (order.getMobileNo() != null) {
						response.setMobileNo(order.getMobileNo());
					}

					if (order.getSize() != null) {
						response.setSize(order.getSize());
					}

					if (order.getIsPaymentPhotoUploaded() != null) {
						if (order.getIsPaymentPhotoUploaded() == Boolean.TRUE) {
							response.setPaymentDone("YES");
						} else {
							response.setPaymentDone("NO");
						}
					}

					if (order.getOrderDate() != null) {
						response.setOrderDate(CommonUtils.convertDateToString(order.getOrderDate()));
					}

					List.add(response);
				}
			}

			res.setBody(List);
			res.setMessage("fetched SucessFully");
			res.setStatus(200);
			return new ResponseEntity<Response<List<OrderResponse>>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Response<List<OrderResponse>>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Override
	public ResponseEntity<Response<Long>> getOrderCount(String shirtSize) throws Exception {
	    Response<Long> res = new Response<>();
	    try {
	        long count;
	        
	        if (shirtSize != null && !shirtSize.isEmpty()) {
	            count = orderDetailsRepository.countBySize(shirtSize);
	        } else {
	            count = orderDetailsRepository.count();
	        }

	        res.setBody(count);
	        res.setMessage("Count fetched successfully");
	        res.setStatus(200);
	        return new ResponseEntity<>(res, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        res.setMessage("Error occurred while fetching the count");
	        res.setStatus(500);
	        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
