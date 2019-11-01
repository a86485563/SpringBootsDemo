package com.biquan.helloworld.dao;

public class AuthInfo {

	private String ChannelID;
	private String Password;
	private String UserID;
	
	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getChannelID() {
		return ChannelID;
	}

	public void setChannelID(String ChannelID) {
		this.ChannelID = ChannelID;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String toString() 
	{
		return "ChannelID:"+this.getChannelID()+"\n"+
				"Password:"+this.getPassword()+"\n"+
				"UserID:"+this.getUserID()+"\n";
				
			
	}

	public AuthInfo() {
		// TODO Auto-generated constructor stub
	}

}
