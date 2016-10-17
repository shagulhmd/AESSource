package org.aes.api.data.impl;

import java.util.List;

import org.aes.api.data.IAESDataAPI;
import org.aes.db.core.AESDBManager;
import org.aes.metadata.root.ClassMetaData;

public class AESDataAPI implements IAESDataAPI {
	
	AESDBManager dbconn;
	
	public AESDataAPI() {
		dbconn = new AESDBManager();
	}

	@Override
	public List<String> getAllPackages() {
		return dbconn.getAllPackages();
	}

	@Override
	public List<ClassMetaData> getAllClasses() {
		return dbconn.getAllComponents();
	}

	@Override
	public boolean saveUILayerPackages(String applicationCode,
			List<String> uiPackages) {
		
		return dbconn.storePackagesFor("pack-ui", applicationCode, uiPackages);
	}


	@Override
	public List<String> getAllUIPackages() {
		return dbconn.getPackagesFor("pack-ui");
	}

	@Override
	public List<String> getAllBusinessPackages() {
		return dbconn.getPackagesFor("pack-biz");
	}

	@Override
	public List<String> getAllDAOPackages() {
		return dbconn.getPackagesFor("pack-dao");
	}

	@Override
	public List<String> getAllUtilityPackages() {
		return dbconn.getPackagesFor("pack-util");
	}

	@Override
	public List<ClassMetaData> getAllUIComponents() {
		return dbconn.getComponentsFor("class-ui");
	}

	@Override
	public List<ClassMetaData> getAllBusinessComponents() {
		return dbconn.getComponentsFor("class-biz");
	}

	@Override
	public List<ClassMetaData> getAllDAOComponents() {
		return dbconn.getComponentsFor("class-dao");
	}

	@Override
	public List<ClassMetaData> getAllUtilityComponents() {
		return dbconn.getComponentsFor("class-util");
	}

	@Override
	public boolean saveClasses(String applicationCode,
			List<ClassMetaData> classes) {
		return dbconn.storeComponents(applicationCode, classes);
	}

	@Override
	public List<String> getAllImportStatements(String classKey) {
		return dbconn.getAllImportStatements(null, classKey);
	}

	@Override
	public boolean saveBusinessLayerPackages(String applicationCode,
			List<String> bizPackages) {
		return dbconn.storePackagesFor("pack-biz", applicationCode, bizPackages);
	}

	@Override
	public boolean saveDAOLayerPackages(String applicationCode,
			List<String> daoPackages) {
		return dbconn.storePackagesFor("pack-dao", applicationCode, daoPackages);
	}

	@Override
	public boolean saveUtilLayerPackages(String applicationCode,
			List<String> utilPackages) {
		return dbconn.storePackagesFor("pack-util", applicationCode, utilPackages);
	}

	@Override
	public List<String> getAllImportStatementsInUILayer(String classKey) {
		return dbconn.getAllImportStatementsFor(null, "ui");
	}

	@Override
	public List<String> getAllImportStatementsInBusinessLayer(String classKey) {
		return dbconn.getAllImportStatementsFor(null, "biz");
	}

	@Override
	public List<String> getAllImportStatementsInDAOLayer(String classKey) {
		return dbconn.getAllImportStatementsFor(null, "dao");
	}

	@Override
	public List<String> getAllImportStatementsInUtilLayer(String classKey) {
		return dbconn.getAllImportStatementsFor(null, "util");
	}


}
