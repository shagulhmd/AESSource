package org.aes.model.metadata.app;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JavaApplicationModel {
	
	public static HashMap<String, String> hMap = new HashMap<String, String>();
	
	public static synchronized void putData(String key, String data){
		
		hMap.put(key, data);
	}
	
	public static synchronized String getData(String key){
		
		return hMap.get(key);
	}
	
	private static Model model = new Model();

	public static Model getModel() {
		return model;
	}
	
	
	public static void processMapData(){
		
		Iterator it = hMap.entrySet().iterator();
		Class clazz = model.getClass();
		Object obj = null;
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        String methodName = (String)pair.getKey();
	        if(methodName.startsWith("TechnologyModel")){
	        	clazz = model.getTechnologyModel().getClass();
	        	obj = model.getTechnologyModel();
	        }
	        methodName = processMethodName(methodName);
	        String value = (String)pair.getValue();
	        try{
	        	 Method m = clazz.getDeclaredMethod(methodName, new Class[] {value.getClass()});
	        	 m.invoke(obj, value);
	        }
	        catch(Exception ex){
	        	System.out.println("Method not found: " + ex.getMessage());
	        }
	    }
		
	}
	
	
	private static String processMethodName(String input){
		
		boolean first = true;
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(input, ".");
		
		while(tokenizer.hasMoreTokens()){
			if(first){
				first = false;
				tokenizer.nextToken();
			}
			else {
				return "set" + tokenizer.nextToken();
			}
		}
		
		return null;
		
	}

	
	

}
