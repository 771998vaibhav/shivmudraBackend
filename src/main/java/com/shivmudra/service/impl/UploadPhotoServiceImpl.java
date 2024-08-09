package com.shivmudra.service.impl;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shivmudra.services.UploadPhotoService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shivmudra.model.CopyObjectRequest;
import com.shivmudra.model.DeleteObjectRequest;
import com.shivmudra.model.S3Object;
import java.io.FileInputStream;
import java.io.FileOutputStream;
@Component
public class UploadPhotoServiceImpl implements UploadPhotoService {
	


	@Value("${file.server.base.url}")
	private String fileServerBaseUrl;

	@Override
	public void copyObject(CopyObjectRequest copyObjRequest) {
		System.out.println("Insude Copy Object");
		FileInputStream in = null;
		try {
			String existingLocation=fileServerBaseUrl+copyObjRequest.getOldBucket()+"/"+copyObjRequest.getOldKey();
			System.out.println("existingLocation::"+existingLocation);
			File initialFile = new File(existingLocation);
		    in = new FileInputStream(initialFile);
		    
		    String newLocation=fileServerBaseUrl+copyObjRequest.getNewBucket()+"/"+copyObjRequest.getNewKey();
		    System.out.println("newLocation::"+newLocation);
		    File newFile = new File(newLocation);
		    
		    newFile.getParentFile().mkdirs();
		    
		    FileOutputStream out = new FileOutputStream(newFile);
			  
	        try {
	            int n;
	            while ((n = in.read()) != -1) {
	                out.write(n);
	            }
	        }
	        finally {
	            if (in != null) {
	                in.close();
	            }
	            if (out != null) {
	                out.close();
	            }
	        }
	        System.out.println("File Copied");
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getUrl(String bucket, String key) {
		System.out.println("getURL::::::"+fileServerBaseUrl + bucket +"/"+ key);
		return fileServerBaseUrl + bucket +"/"+ key;
	}

	@Override
	public void putObject(String bucket, String key, FileInputStream in,
			ObjectMetadata objectMetaData) {
		try {
			String location = fileServerBaseUrl + bucket +"/"+ key;
			System.out.println("S3ClientttServvice:::URL::"+location);
			File file = new File(location);
			
			file.getParentFile().mkdirs();
			
	        FileOutputStream out = new FileOutputStream(file);
		  
	        try {
	            int n;
	            while ((n = in.read()) != -1) {
	                out.write(n);
	            }
	        }
	        finally {
	            if (in != null) {
	                in.close();
	            }
	            if (out != null) {
	                out.close();
	            }
	        }
	        System.out.println("File Inserted");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public S3Object getObject(String bucket, String key) {
		FileInputStream in = null;
		try {
			String existingLocation=fileServerBaseUrl+bucket+"/"+key;
			System.out.println("existingLocation:::"+existingLocation);
			File initialFile = new File(existingLocation);
		    in = new FileInputStream(initialFile);
		    S3Object s3 = new S3Object();
		    s3.setObjectContent(in);
		    return s3;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteObject(DeleteObjectRequest deleteObjectRequest) {
		try {
			String location = fileServerBaseUrl + deleteObjectRequest.getBucket() +"/"+ deleteObjectRequest.getKey();
			File file = new File(location);
			file.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public S3Object getFileByUrl(String url) {
		FileInputStream in = null;
		try {
			System.out.println("existingLocation:::"+url);
			File initialFile = new File(url);
		    in = new FileInputStream(initialFile);
		    S3Object s3 = new S3Object();
		    s3.setObjectContent(in);
		    return s3;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public S3Object getObjectNew(String bucket, String key) {
		FileInputStream in = null;
		try {
//			String existingLocation=bucket+key;
//			System.out.println("existingLocation:::"+existingLocation);
			File initialFile = new File(key);
		    in = new FileInputStream(initialFile);
		    S3Object s3 = new S3Object();
		    s3.setObjectContent(in);
		    return s3;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

}
