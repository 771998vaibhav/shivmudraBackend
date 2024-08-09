package com.shivmudra.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "mobile")
	private String mobileNo;

	@Column(name = "address")
	private String address;

	@Column(name = "size")
	private String size;

	@Column(name = "payment_photo_url")
	private String paymentUrl;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private String pincode;
	
	@Column(name =	"is_payment_photo_uploaded")
	private Boolean isPaymentPhotoUploaded;
	
	
	
	

	public Boolean getIsPaymentPhotoUploaded() {
		return isPaymentPhotoUploaded;
	}

	public void setIsPaymentPhotoUploaded(Boolean isPaymentPhotoUploaded) {
		this.isPaymentPhotoUploaded = isPaymentPhotoUploaded;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
