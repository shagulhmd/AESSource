package org.aes.metadata.root;

import java.util.HashMap;
import java.util.Map;

import org.aes.metadata.application.TechnologyMetaData;

public class ApplicationMetaData {
	
	
	
	public ApplicationMetaData() {
		technologyMetaDatas = new HashMap<String, TechnologyMetaData>();
	}

	private Map<String, TechnologyMetaData> technologyMetaDatas;

	public Map<String, TechnologyMetaData> getTechnologyMetaDatas() {
		return technologyMetaDatas;
	}

	public void setTechnologyMetaDatas(
			Map<String, TechnologyMetaData> technologyMetaDatas) {
		this.technologyMetaDatas = technologyMetaDatas;
	}
	

}
