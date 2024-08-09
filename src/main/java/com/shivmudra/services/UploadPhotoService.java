package com.shivmudra.services;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shivmudra.model.CopyObjectRequest;
import com.shivmudra.model.DeleteObjectRequest;
import com.shivmudra.model.S3Object;

@Service
public interface UploadPhotoService {
	
	
	public void copyObject(CopyObjectRequest copyObjRequest);

	public Object getUrl(String bucketsDocuments, String key);

	public void putObject(String bucketsDocuments, String key, FileInputStream fileInputStream,
			ObjectMetadata objectMetaData);

	public S3Object getObject(String bucketsDocuments, String key);

	public void deleteObject(DeleteObjectRequest deleteObjectRequest);

	public S3Object getFileByUrl(String url);
	
	public S3Object getObjectNew(String bucketsDocuments, String key);

}
