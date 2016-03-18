package org.aes.core.scanner.rules;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType (XmlAccessType.FIELD)
public class ScannerRule {

	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="for")
	private String forType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForType() {
		return forType;
	}

	public void setForType(String forType) {
		this.forType = forType;
	}

	@XmlElement(name="ruleDesc")
	private String ruleDesc;
	
	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	@XmlElement(name="activeRule")
	private boolean activeRule;

	public boolean isActiveRule() {
		return activeRule;
	}

	public void setActiveRule(boolean active) {
		this.activeRule = active;
	}
	
	@XmlElement(name="scanType")
	private String scanType;
	
	@Override
	public String toString() {
		return "ScannerRule [name=" + name + ", forType=" + forType
				+ ", ruleDesc=" + ruleDesc + ", activeRule=" + activeRule
				+ ", scanType=" + scanType + ", scanPatterns=" + scanPatterns
				+ "]";
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
