package org.aes.metadata.root;

import java.util.HashMap;
import java.util.Map;

import org.aes.metadata.classes.FoundMetaData;

public class ClassMetaData {
	
	
	private String className;
	private String packageName;
	private String classKey;
	
	public ClassMetaData(){
		foundMetaDatas = new HashMap<String, FoundMetaData>();
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassKey() {
		return classKey;
	}

	public void setClassKey(String classKey) {
		this.classKey = classKey;
	}

	public Map<String, FoundMetaData> getFoundMetaDatas() {
		return foundMetaDatas;
	}

	public void setFoundMetaDatas(Map<String, FoundMetaData> foundMetaDatas) {
		this.foundMetaDatas = foundMetaDatas;
	}

	private Map<String, FoundMetaData> foundMetaDatas;
	
	

}
