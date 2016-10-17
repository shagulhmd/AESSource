package org.aes.core.engine;

import org.aes.api.data.impl.AESDataAPI;
import org.aes.metadata.root.AESMetaData;

public class AESDataBatch {
	
	AESDataAPI api;
	
	public AESDataBatch() {
		api = new AESDataAPI();
	}
	
	public boolean runBatch(AESMetaData metadata){
		
		return true;
	}

}
