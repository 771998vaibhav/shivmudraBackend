package com.shivmudra.dto;

public class PaymentPhotoDto {
	
	private String file;
	private String url;
	private String extension;
	private String fileName;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "AbhiDocumentDto [file=" + file + ", url=" + url + ", extension=" + extension + ", fileName=" + fileName
				+ "]";
	}

}
