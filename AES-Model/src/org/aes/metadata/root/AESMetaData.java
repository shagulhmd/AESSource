package org.aes.metadata.root;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AESMetaData {
	
	
	
	public AESMetaData() {
		applicationMetaData = new ApplicationMetaData();
		classMetaDatas = new HashMap<String, ClassMetaData>();
		referenceMetaDatas = new HashMap<String, ReferenceMetaData>();
		runDate = new Date();
	}

	private String projectName;
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public ApplicationMetaData getApplicationMetaData() {
		return applicationMetaData;
	}

	public void setApplicationMetaData(ApplicationMetaData applicationMetaData) {
		this.applicationMetaData = applicationMetaData;
	}

	public Map<String, ClassMetaData> getClassMetaDatas() {
		return classMetaDatas;
	}

	public void setClassMetaDatas(Map<String, ClassMetaData> classMetaDatas) {
		this.classMetaDatas = classMetaDatas;
	}

	public Map<String, ReferenceMetaData> getReferenceMetaDatas() {
		return referenceMetaDatas;
	}

	public void setReferenceMetaDatas(
			Map<String, ReferenceMetaData> referenceMetaDatas) {
		this.referenceMetaDatas = referenceMetaDatas;
	}

	private String projectKey;
	private String userName;
	private Date runDate;
	
	private ApplicationMetaData applicationMetaData;
	
	private Map<String, ClassMetaData> classMetaDatas;
	
	private Map<String, ReferenceMetaData> referenceMetaDatas;

}
