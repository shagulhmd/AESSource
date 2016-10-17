package org.aes.core.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.PMDException;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.util.datasource.DataSource;
import net.sourceforge.pmd.util.datasource.FileDataSource;

import org.aes.core.java.test.AESTest;
import org.aes.core.scanner.rules.AESScannerRulesConfig;
import org.aes.metadata.root.AESMetaData;
import org.aes.model.metadata.report.ApplicationReport;
import org.aes.validator.drools.engine.AESDroolsEngine;

public class AESEngine {
	
	
	private String configPath;
	private AESConfig configs;
	private AESMetaData metaData;
	private final String MAIN_CONFIG = "//aes-config.xml";
	private final String JAVA_SCANNER_CONFIG = "//java-scanner-rules.xml";
	private AESScannerRulesConfig javaConfigs;

	public AESMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(AESMetaData metData) {
		this.metaData = metData;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	public void init(String configPath){
		this.configPath = configPath;
		loadConfigData();
		loadJavaScannerConfigData();
	}

	private void loadConfigData() {
		File configFile = new File(configPath + MAIN_CONFIG);
		if(!configFile.exists()){
			System.out.println("ERROR: No config file exists");
			return;
		}
		
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(AESConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			configs = (AESConfig) jaxbUnmarshaller.unmarshal(configFile);
			System.out.println(configs.toString());
			
		}
		catch(JAXBException ex){
			System.out.println("ERROR: Error in processing config file - JAXBException");
			ex.printStackTrace();
		}	
	}
	
	private void loadJavaScannerConfigData() {
		File configFile = new File(configPath + JAVA_SCANNER_CONFIG);
		if(!configFile.exists()){
			System.out.println("ERROR: No scanner config file exists for Java, please check");
			return;
		}
		
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(AESScannerRulesConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			javaConfigs = (AESScannerRulesConfig) jaxbUnmarshaller.unmarshal(configFile);
			System.out.println(javaConfigs.toString());
			
		}
		catch(JAXBException ex){
			System.out.println("ERROR: Error in processing config file - JAXBException");
			ex.printStackTrace();
		}	
	}
	
	public void start(){
		try{
			if(configs == null || configs.getSourceFilePath() == null || "".equals(configs.getSourceFilePath())){
				System.out.println("ERROR: Configuration file not loaded properly or missing required data (source path)");
				return;
			}
			
			if(configs == null || configs.getScannerRulesPath() == null || "".equals(configs.getScannerRulesPath())){
				System.out.println("ERROR: Configuration file not loaded properly or missing required data (scanner rules path)");
				return;
			}
			
			File file = new File(configs.getSourceFilePath());
			
			if(!file.exists()){
				System.out.println("ERROR: Source file(s) not available in specified path");
				return;
			}
			
			String workingPath = AESTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			String rulesPath = workingPath + configs.getScannerRulesPath();
			System.out.println("Load rules from:" + rulesPath);
			
			List<File> sourceFiles = AESSourceFileProcessor.processSourceFiles(configs.getSourceFilePath(), configs);
			
			processFiles(sourceFiles, rulesPath);
			
			storeMetaData(metaData);
			
			validateAESRules(metaData);
		}
		catch(Exception ex3){
			ex3.printStackTrace();
		}
	}

	private void storeMetaData(AESMetaData mData) {

		//TO DO:
		//Store all metadata values into MongoDB
		AESDataBatch batch = new AESDataBatch();
		batch.runBatch(mData);
		
	}

	private void processFiles(List<File> sourceFiles, String rulesPath) {
		
		try{
			
			List<DataSource> files = new ArrayList<DataSource>();
			
			for(File file: sourceFiles){
				files.add(new FileDataSource(file));
			}
			
			RuleContext ctx = new RuleContext();
			//Set the metadata to the context
			//so that it can be accessible in scan rules
			metaData = new AESMetaData();
			ctx.setAttribute("AESDATA", metaData);
			//Set the java scanner rules to be used inside
			//the scanner rule classes
			ctx.setAttribute("JAVACONFIG", javaConfigs);
			
			if(configs.isMultiThreadEnabled()){
				
				//Process the source files multi-threaded
				scanFilesMultiThreaded(files, rulesPath, ctx);
			}
			else
			{
				RuleSet rules = new RuleSetFactory().createRuleSet(rulesPath);
				//Process the source files single threaded
				scanFilesSingleThreaded(files, rules, ctx);
			}
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
	private void scanFilesSingleThreaded(List<DataSource> files, RuleSet ruleset, RuleContext context) throws PMDException{
		PMDConfiguration config = new PMDConfiguration();
		AESMonoThreadProcessor processor = new AESMonoThreadProcessor(config);
		RuleSets ruleSets = new RuleSets(ruleset);
		processor.processFiles(ruleSets, files, context);
	}
	
	private void scanFilesMultiThreaded(List<DataSource> files, String ruleset, RuleContext context) throws PMDException{
		PMDConfiguration config = new PMDConfiguration();
		config.setRuleSets(ruleset);
		AESMultiThreadProcessor processor = new AESMultiThreadProcessor(config);
		RuleSetFactory factory = new RuleSetFactory();
		processor.processFiles(factory, files, context);
	}
	
	
	public static void validateAESRules(AESMetaData metaData){
		
		
		ApplicationReport report = new ApplicationReport();
		AESDroolsEngine engine = new AESDroolsEngine();
		try{
			engine.triggerAESValidationRules(metaData, report);
			ArrayList<String> aList = report.getResult();
			System.out.println("RESULTS:" + aList.toString());
		}
		catch(Exception e){
			System.out.println("Unknown Error");
		}
		

		
	}
	

}
