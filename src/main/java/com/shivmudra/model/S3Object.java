package com.shivmudra.model;

import java.io.InputStream;

public class S3Object {

	private InputStream objectContent;

	public InputStream getObjectContent() {
		return objectContent;
	}

	public void setObjectContent(InputStream objectContent) {
		this.objectContent = objectContent;
	}
		
}
