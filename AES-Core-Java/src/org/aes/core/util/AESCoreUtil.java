package org.aes.core.util;

public class AESCoreUtil {
	
	
	public static boolean isNotNullOrEmpty(String s){
		if(s != null && s.trim().length() > 0){
			return true;
		}
		else
		{
			return false;
		}
	}

}
