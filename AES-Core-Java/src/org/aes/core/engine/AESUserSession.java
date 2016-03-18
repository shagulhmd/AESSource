package org.aes.core.engine;

import org.aes.metadata.root.AESMetaData;


public class AESUserSession {
	
	private AESMetaData aesMetaData;
	private String userKey;
	
	private String userName;
	
	public AESUserSession(String userName, String userKey, String projectName, String projectKey){
		this.userName = userName;
		this.userKey = userKey;
		aesMetaData = new AESMetaData();
		aesMetaData.setUserName(userName);
		aesMetaData.setProjectName(projectName);
		aesMetaData.setProjectKey(projectKey);
	}

	public AESMetaData getAesMetaData() {
		return aesMetaData;
	}

	public String getUserKey() {
		return userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setAesMetaData(AESMetaData aesMetaData) {
		this.aesMetaData = aesMetaData;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public void process(){
		String path = AESUserSession.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		AESEngine engine = new AESEngine();
		engine.init(path);
		engine.setMetaData(aesMetaData);
		engine.start();
	}
	
	

}
