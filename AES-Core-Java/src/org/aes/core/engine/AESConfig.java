package org.aes.core.engine;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class AESConfig {
	
	@XmlElement(name="dbHost")
	private String dbHost;
	
	@XmlElement(name="dbName")
	private String dbName;
	
	@XmlElement(name="dbPort")
	private String dbPort;
	
	@XmlElementWrapper( name="ignoreFileExts" )
	@XmlElement(name = "ext")
	private List<String> ignoreFileExts;
	
	@XmlElement(name = "multiThreadEnabled")
	private boolean multiThreadEnabled;
	
	@XmlElement(name = "scannerRulesPath")
	private String scannerRulesPath;
	
	@XmlElement(name = "sourceFilePath")
	private String sourceFilePath;
	
	public void addIgnoreFileExts(String ext){
		if(ignoreFileExts == null){
			ignoreFileExts = new ArrayList<String>();
		}
		ignoreFileExts.add(ext);
	}

	public String getDbHost() {
		return dbHost;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public String getDbPort() {
		return dbPort;
	}
	
	public List<String> getIgnoreFileExts() {
		return ignoreFileExts;
	}
	
	public String getScannerRulesPath() {
		return scannerRulesPath;
	}
	

	public String getSourceFilePath() {
		return sourceFilePath;
	}
	public boolean isMultiThreadEnabled() {
		return multiThreadEnabled;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	public void setIgnoreFileExts(List<String> ignoreFileExts) {
		this.ignoreFileExts = ignoreFileExts;
	}
	public void setMultiThreadEnabled(boolean multiThreadEnabled) {
		this.multiThreadEnabled = multiThreadEnabled;
	}
	public void setScannerRulesPath(String scannerRulesPath) {
		this.scannerRulesPath = scannerRulesPath;
	}
	
	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	
	@Override
	public String toString() {
		return "AESConfig [sourceFilePath=" + sourceFilePath
				+ ", scannerRulesPath=" + scannerRulesPath + ", dbHost="
				+ dbHost + ", dbPort=" + dbPort + ", dbName=" + dbName
				+ ", ignoreFileExts=" + ignoreFileExts
				+ ", multiThreadEnabled=" + multiThreadEnabled + "]";
	}

}
