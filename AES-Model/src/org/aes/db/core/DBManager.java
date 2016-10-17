package org.aes.db.core;

import org.aes.db.exception.AESDBException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	
	private static MongoClient conn;
	private static MongoDatabase database;
	
	
	private static String getConnectionString(DBAuth config){
		if(config.isAuth()){
			return new StringBuilder("mongodb://")
				.append(config.getUsername().trim())
				.append(":")
				.append(config.getPassword().trim())
				.append("@")
				.append(config.getHost().trim())
				.append(":")
				.append(config.getPort().trim())
				.append("/?")
				.append("authSource=")
				.append(config.getDbName().trim())
				.toString();
		}
		else
		{
			return new StringBuilder("mongodb://")
				.append(config.getHost().trim())
				.append(":")
				.append(config.getPort().trim())
				.toString();
				
		}
	}
	
	
	public static void initialize(DBAuth config) throws AESDBException{
		
		if (config == null || !config.validate())
		{
			throw new AESDBException("DB Connection not successful");
		}
		else
		{
			MongoClientURI uri = new MongoClientURI(getConnectionString(config));
			conn = new MongoClient(uri);
			database = conn.getDatabase(config.getDbName());
		}
	}
	
	
	public static MongoDatabase getDatabase(){
		return database;
	}
	
	public static void main(String as[]){
		
		try{
			DBAuth config = new DBAuth();
			config.setAuth(false);
			config.setHost("localhost");
			config.setPort("27017");
			config.setDbName("aesdb");
			initialize(config);
			MongoDatabase db = getDatabase();
			db.createCollection("sample");
			System.out.println();
		}
		catch(AESDBException ex){
			System.out.println(ex.getMessage());
		}

	}

}
