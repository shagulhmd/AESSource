package org.aes.core.engine;


public class AESMain {
	
	/**
	 * Starts the AES Scanner Engine
	 * based on the configuration data
	 * @param as
	 */
	public static void main(String as[]){
		
		AESUserSession session = new AESUserSession("DEFAULT", "DEF#12345", "SAMPLEPROJ", "SAMPLEPROJ#324234");
		session.process();
	}

}
