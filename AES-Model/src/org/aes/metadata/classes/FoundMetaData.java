package org.aes.metadata.classes;

public class FoundMetaData {

	private String addtionalData;
	private String foundKey;
	private String lineNo;
	private String methodName;

	public FoundMetaData(String foundKey, String lineNo, String methodName) {
		super();
		this.foundKey = foundKey;
		this.lineNo = lineNo;
		this.methodName = methodName;
	}

	public String getAddtionalData() {
		return addtionalData;
	}

	public String getFoundKey() {
		return foundKey;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setAddtionalData(String addtionalData) {
		this.addtionalData = addtionalData;
	}

	public void setFoundKey(String foundKey) {
		this.foundKey = foundKey;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
