package org.aes.model.metadata.app;

public class TechnologyModel {
	
	private String primaryDAO;
	private String isJPA;
	public String getPrimaryDAO() {
		return primaryDAO;
	}
	public void setPrimaryDAO(String primaryDAO) {
		this.primaryDAO = primaryDAO;
	}
	public String isJPA() {
		return isJPA;
	}
	public void setJPA(String isJPA) {
		this.isJPA = isJPA;
	}
	public String getJpaImpl() {
		return jpaImpl;
	}
	public void setJpaImpl(String jpaImpl) {
		this.jpaImpl = jpaImpl;
	}
	private String jpaImpl;
	
	

}
