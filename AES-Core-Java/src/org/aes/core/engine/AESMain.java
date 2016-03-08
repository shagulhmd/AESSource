package org.aes.core.engine;


public class AESMain {
	
	/**
	 * Starts the AES Scanner Engine
	 * based on the configuration data
	 * @param as
	 */
	public static void main(String as[]){
		
		String path = AESMain.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		AESEngine engine = new AESEngine();
		engine.init(path + "//aes-config.xml");
		engine.start();
	}

}
