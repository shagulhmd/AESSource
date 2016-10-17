package org.aes.db.core;

public class DBAuth {
	
	private String username;
	private String password;
	
	private String host;
	private String port;
	
	private String dbName;
	
	private boolean isAuth;
	
	public boolean validate(){
		
		if(isAuth && (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0)){
			return false;
		}
		else if(host == null || host.trim().length() == 0 || port == null && port.trim().length() == 0){
			
			return false;
			
		}
		else
		{
			return true;
		}
		
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
