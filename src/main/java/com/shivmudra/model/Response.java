package com.shivmudra.model;

public class Response<T> {

	private Object error;
	private int status;
	private String message;
	private T body;
	private Boolean accessType;
	private String claimId;
	private String referenceId;
	
	public Response() {
		super();
	}

	public Response(int status, String message, T body, Object error) {
		super();
		this.status = status;
		this.message = message;
		this.body = body;
		this.error = error;
	}
	
	public Response(int status, String message, T body) {
		super();
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public Response(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Response [error=" + error + ", status=" + status + ", message=" + message + ", body=" + body
				+ "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public Boolean getAccessType() {
		return accessType;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Boolean isAccessType() {
		return accessType;
	}
	
	public void setAccessType(Boolean accessType) {
		this.accessType = accessType;
	}

}
