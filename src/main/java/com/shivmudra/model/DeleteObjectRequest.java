package com.shivmudra.model;

public class DeleteObjectRequest {

	public DeleteObjectRequest(String bucket, String key) {
		super();
		this.bucket = bucket;
		this.key = key;
	}
	private String bucket;
	private String key;
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
