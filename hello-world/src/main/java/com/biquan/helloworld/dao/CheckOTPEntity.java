package com.biquan.helloworld.dao;

public class CheckOTPEntity {

	private String AuthToken;
	private String Certificate;
	private String RocId;
	
	
	public String getAuthToken() {
		return AuthToken;
	}


	public void setAuthToken(String authToken) {
		AuthToken = authToken;
	}


	public String getCertificate() {
		return Certificate;
	}


	public void setCertificate(String certificate) {
		Certificate = certificate;
	}


	public String getRocId() {
		return RocId;
	}


	public void setRocId(String rocId) {
		RocId = rocId;
	}


	public CheckOTPEntity() {
		// TODO Auto-generated constructor stub
	}

	public String toString() 
	{
		return "AuthToken:"+this.getAuthToken()+"\n"+
				"Certificate:"+this.getCertificate()+"\n"+
				"RocId:"+this.getRocId()+"\n";
				
			
	}
}
