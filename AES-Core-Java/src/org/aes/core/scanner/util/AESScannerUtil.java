package org.aes.core.scanner.util;

import org.aes.metadata.classes.FoundMetaData;
import org.aes.metadata.root.ClassMetaData;

public class AESScannerUtil {
	
	
	public static boolean comparePattern(String input, String pattern){
		
		boolean found = false;
		
		if(pattern.startsWith("*") && pattern.endsWith("*")){
			pattern = pattern.replace("*", "").trim();
			if(input.contains(pattern)){
				found = true;
			}
		}
		else if(pattern.startsWith("*")){
			pattern = pattern.replace("*", "").trim();
			if(input.endsWith(pattern)){
				found = true;
			}
		}
		else if(pattern.endsWith("*")){
			pattern = pattern.replace("*", "").trim();
			if(input.startsWith(pattern)){
				found = true;
			}
		}
		else if(input.equalsIgnoreCase(pattern)) {
			found = true;
		}
		
		return found;
	}
	
	
	
	public static void loadFoundData(ClassMetaData cMetaData, String key, int lineNo, String methodName){
		
		if(cMetaData != null){
			cMetaData.getFoundMetaDatas().put(key, new FoundMetaData(key, String.valueOf(lineNo), methodName));
		}
	}

}
