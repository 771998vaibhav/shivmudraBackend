package com.shivmudra.model;
public class CopyObjectRequest {

	private String oldBucket;
	private String oldKey;
	private String newBucket;
	private String newKey;
	public String getOldBucket() {
		return oldBucket;
	}
	public CopyObjectRequest(String oldBucket, String oldKey, String newBucket, String newKey) {
		super();
		this.oldBucket = oldBucket;
		this.oldKey = oldKey;
		this.newBucket = newBucket;
		this.newKey = newKey;
	}
	public void setOldBucket(String oldBucket) {
		this.oldBucket = oldBucket;
	}
	public String getOldKey() {
		return oldKey;
	}
	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}
	public String getNewBucket() {
		return newBucket;
	}
	public void setNewBucket(String newBucket) {
		this.newBucket = newBucket;
	}
	public String getNewKey() {
		return newKey;
	}
	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}
	
	
}
