package org.aes.db.core;

import java.util.ArrayList;
import java.util.List;

import org.aes.db.exception.AESDBException;
import org.aes.metadata.classes.MethodMetaData;
import org.aes.metadata.root.ClassMetaData;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class AESDBManager {
	
	MongoDatabase db;
	
	public AESDBManager() {
		initDB();
	}

	private void initDB() {
		try{
			DBAuth config = new DBAuth();
			config.setAuth(false);
			config.setHost("localhost");
			config.setPort("27017");
			config.setDbName("aesdb");
			DBManager.initialize(config);
			db = DBManager.getDatabase();
			
		}
		catch(AESDBException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public List<String> getAllPackages(){
		final List<String> list = new ArrayList<String>();
		
		FindIterable<Document> iterable = db.getCollection("pack-ui").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        list.add(document.getString("packageName"));
		    }
		});		
		
		
		iterable = db.getCollection("pack-biz").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        list.add(document.getString("packageName"));
		    }
		});	
		
		iterable = db.getCollection("pack-dao").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        list.add(document.getString("packageName"));
		    }
		});	
		
		
		iterable = db.getCollection("pack-util").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        list.add(document.getString("packageName"));
		    }
		});	
		
		return list;
	}
	
	public List<String> getPackagesFor(String pType){
		final List<String> list = new ArrayList<String>();
		
		FindIterable<Document> iterable = db.getCollection(pType).find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        list.add(document.getString("packageName"));
		    }
		});		
		
		
		return list;
	}
	
	
	public List<ClassMetaData> getComponentsFor(String cType){
		final List<ClassMetaData> list = new ArrayList<ClassMetaData>();
		
		FindIterable<Document> iterable = db.getCollection("classes-meta").find(new Document("classType", cType));
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	ClassMetaData classMeta = new ClassMetaData();
		    	classMeta.setClassName(document.getString("className"));
		    	classMeta.setClassKey(document.getString("classKey"));
		    	classMeta.setPackageName(document.getString("packageName"));
		    	list.add(classMeta);
		    }
		});		
		
		
		return list;
	}
	
	public List<ClassMetaData> getAllComponents(){
		final List<ClassMetaData> list = new ArrayList<ClassMetaData>();
		
		FindIterable<Document> iterable = db.getCollection("classes-meta").find();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	ClassMetaData classMeta = new ClassMetaData();
		    	classMeta.setClassName(document.getString("className"));
		    	classMeta.setClassKey(document.getString("classKey"));
		    	classMeta.setPackageName(document.getString("packageName"));
		    	
		    	list.add(classMeta);
		    }
		});		
		
		
		return list;
	}
	
	public boolean storePackagesFor(String type, String appName, List<String> uiPackages)
	{
		for(String uiPackage: uiPackages){
			db.getCollection(type).insertOne(
					new Document().append("packageName", uiPackage)	
								  .append("appName", appName)
					);
		}
		
		return true;
	}
	
	
	public boolean storeComponents(String applicationCode,	List<ClassMetaData> classes)
	{
		for(ClassMetaData oClass: classes){
			
			List<String> imports = oClass.getImports();
			
			List<MethodMetaData> methods = oClass.getMethods();
			
			List<String> methodNames = oClass.getMethodNames();
			
			List<Document> methodDocs = new ArrayList<Document>();
			for(MethodMetaData method: methods){
				Document methodDoc = new Document().append("methodName", method.getMethodName())
						.append("methodText", method.getMethodName());
				methodDocs.add(methodDoc);
			}
			
			db.getCollection("classes-meta").insertOne(
					new Document().append("appCode", oClass.getApplicationCode())
								  .append("className", oClass.getClassName())	
								  .append("classKey", oClass.getClassKey())
								  .append("category", oClass.getClassCategory())
								  .append("packageName", oClass.getPackageName())
								  .append("imports", imports)
								  .append("methodNames", methodNames)
								  .append("methods", methodDocs)
					);
		}
		
		return true;
	}
	
	
	public List<String> getAllImportStatements(String applicationCode, String classKey){
		
		final List<String> list = new ArrayList<String>();
		
		
		
		FindIterable<Document> iterable = db.getCollection("classes-meta").find(new Document("classKey", classKey));
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	List<String> iList = (ArrayList<String>) document.get("imports");
		    	for(String imp: iList){
		    		list.add(imp);
		    	}
		    }
		});		
		
		
		return list;
	}
	
	
	public List<String> getAllImportStatementsFor(String applicationCode, String layer){
		
		final List<String> list = new ArrayList<String>();
		
		
		
		FindIterable<Document> iterable = db.getCollection("classes-meta").find(new Document("category", layer));
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	List<String> iList = (ArrayList<String>) document.get("imports");
		    	for(String imp: iList){
		    		list.add(imp);
		    	}
		    }
		});		
		
		
		return list;
	}
	

}
