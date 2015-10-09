package org.aes.model.metadata.app;

public class Model {
	
	private TechnologyModel technologyModel;

	public TechnologyModel getTechnologyModel() {
		if(technologyModel == null)
			technologyModel = new TechnologyModel();
		return technologyModel;
	}

	public void setTechnologyModel(TechnologyModel technologyModel) {
		this.technologyModel = technologyModel;
	}
	
	

}
