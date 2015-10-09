package org.aes.model.metadata.report;

import java.util.ArrayList;

public class ApplicationReport {
	
	private ArrayList<String> result = new ArrayList<String>();

	public ArrayList<String> getResult() {
		return result;
	}

	public void setResult(ArrayList<String> result) {
		this.result = result;
	}
	
	
	public void addResult(String str){
		result.add(str);
	}
	
	

}
