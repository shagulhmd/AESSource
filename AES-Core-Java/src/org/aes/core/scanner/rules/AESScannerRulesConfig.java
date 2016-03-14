package org.aes.core.scanner.rules;



import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class AESScannerRulesConfig {
	
	@Override
	public String toString() {
		return "AESScannerRulesConfig [scannerRules=" + scannerRules + "]";
	}

	public List<ScannerRule> getScannerRules() {
		return scannerRules;
	}

	public void setScannerRules(List<ScannerRule> scannerRules) {
		this.scannerRules = scannerRules;
	}

	@XmlElementWrapper( name="scannerRules" )
	@XmlElement(name = "scannerRule")
	private List<ScannerRule> scannerRules;

}
