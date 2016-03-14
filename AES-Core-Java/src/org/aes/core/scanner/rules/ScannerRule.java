package org.aes.core.scanner.rules;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType (XmlAccessType.FIELD)
public class ScannerRule {

	@XmlElement(name="activeRule")
	private boolean activeRule;

	public boolean getActiveRule() {
		return activeRule;
	}

	public void setActiveRule(boolean active) {
		this.activeRule = active;
	}
	
	@XmlElement(name="scanType")
	private String scanType;
	
	@Override
	public String toString() {
		return "ScannerRule [scanType=" + scanType + ", scanPatterns="
				+ scanPatterns + "]";
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public List<String> getScanPatterns() {
		return scanPatterns;
	}

	public void setScanPatterns(List<String> scanPatterns) {
		this.scanPatterns = scanPatterns;
	}

	@XmlElementWrapper( name="scanPatterns" )
	@XmlElement(name = "scanPattern")
	private List<String> scanPatterns;
	

	
	
}
