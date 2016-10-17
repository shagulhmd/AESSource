package org.aes.metadata.root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aes.metadata.classes.FoundMetaData;
import org.aes.metadata.classes.MethodMetaData;

public class ClassMetaData {
	
	
	private String className;
	private String packageName;
	private String classKey;
	private String classCategory;
	
	public String getClassCategory() {
		return classCategory;
	}

	public void setClassCategory(String classCategory) {
		this.classCategory = classCategory;
	}

	private List<String> imports = new ArrayList<String>();
	private List<String> methodNames = new ArrayList<String>();
	private List<MethodMetaData> methods = new ArrayList<MethodMetaData>();
	private String applicationCode;
	
	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public List<MethodMetaData> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodMetaData> methods) {
		this.methods = methods;
	}
	
	public void addMethod(MethodMetaData method){
		this.methods.add(method);
	}

	public List<String> getMethodNames() {
		return methodNames;
	}

	public void setMethodNames(List<String> methodNames) {
		this.methodNames = methodNames;
	}
	
	public void addMethodName(String methodName){
		this.methodNames.add(methodName);
	}

	public List<String> getImports() {
		return imports;
	}
	

	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	
	public void addImport(String importStmt){
		this.imports.add(importStmt);
	}

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
