package com.biquan.helloworld.dao;

public class TransInfo {
	
	private String TransAuthToken;
	private String TransBpId;
	private String TransPath;
	
	public String getTransAuthToken() {
		return TransAuthToken;
	}

	public void setTransAuthToken(String transAuthToken) {
		TransAuthToken = transAuthToken;
	}

	public String getTransBpId() {
		return TransBpId;
	}

	public void setTransBpId(String transBpId) {
		TransBpId = transBpId;
	}

	public String getTransPath() {
		return TransPath;
	}

	public void setTransPath(String transPath) {
		TransPath = transPath;
	}

	public TransInfo() {
		// TODO Auto-generated constructor stub
	}
	public String toString() 
	{
		return "TransAuthToken:"+this.getTransAuthToken()+"\n"+
				"TransBpId:"+this.getTransBpId()+"\n"+
				"TransPath:"+this.getTransPath()+"\n";
				
			
	}

}
