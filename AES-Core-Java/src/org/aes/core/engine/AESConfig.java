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
	
	@XmlElement(name = "sourceFilePath")
	private String sourceFilePath;
	
	@XmlElement(name = "scannerRulesPath")
	private String scannerRulesPath;
	
	@XmlElementWrapper( name="ignoreFileExts" )
	@XmlElement(name = "ext")
	private List<String> ignoreFileExts;
	
	@XmlElement(name = "multiThreadEnabled")
	private boolean multiThreadEnabled;
	

	public boolean isMultiThreadEnabled() {
		return multiThreadEnabled;
	}
	public void setMultiThreadEnabled(boolean multiThreadEnabled) {
		this.multiThreadEnabled = multiThreadEnabled;
	}
	public String getSourceFilePath() {
		return sourceFilePath;
	}
	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	public String getScannerRulesPath() {
		return scannerRulesPath;
	}
	public void setScannerRulesPath(String scannerRulesPath) {
		this.scannerRulesPath = scannerRulesPath;
	}
	public List<String> getIgnoreFileExts() {
		return ignoreFileExts;
	}
	public void setIgnoreFileExts(List<String> ignoreFileExts) {
		this.ignoreFileExts = ignoreFileExts;
	}
	
	public void addIgnoreFileExts(String ext){
		if(ignoreFileExts == null){
			ignoreFileExts = new ArrayList<String>();
		}
		ignoreFileExts.add(ext);
	}
	
	@Override
	public String toString() {
		return "AESConfig [sourceFilePath=" + sourceFilePath
				+ ", scannerRulesPath=" + scannerRulesPath
				+ ", ignoreFileExts=" + ignoreFileExts
				+ ", multiThreadEnabled=" + multiThreadEnabled + "]";
	}

}
