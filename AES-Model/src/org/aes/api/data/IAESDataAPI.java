package org.aes.api.data;

import java.util.List;

import org.aes.metadata.root.ClassMetaData;

public interface IAESDataAPI {
	
	public List<String> getAllPackages();
	
	public List<String> getAllUIPackages();
	
	public List<String> getAllBusinessPackages();
	
	public List<String> getAllDAOPackages();
	
	public List<String> getAllUtilityPackages();
	
	public List<ClassMetaData> getAllClasses();
	
	public List<ClassMetaData> getAllUIComponents();
	
	public List<ClassMetaData> getAllBusinessComponents();
	
	public List<ClassMetaData> getAllDAOComponents();
	
	public List<ClassMetaData> getAllUtilityComponents();
	
	public boolean saveUILayerPackages(String applicationCode, List<String> uiPackages);
	
	public boolean saveBusinessLayerPackages(String applicationCode, List<String> bizPackages);
	
	public boolean saveDAOLayerPackages(String applicationCode, List<String> daoPackages);
	
	public boolean saveUtilLayerPackages(String applicationCode, List<String> utilPackages);
	
	public boolean saveClasses(String applicationCode, List<ClassMetaData> uiClasses);

	public List<String> getAllImportStatements(String classKey);
	
	public List<String> getAllImportStatementsInUILayer(String classKey);
	
	public List<String> getAllImportStatementsInBusinessLayer(String classKey);
	
	public List<String> getAllImportStatementsInDAOLayer(String classKey);
	
	public List<String> getAllImportStatementsInUtilLayer(String classKey);

}
