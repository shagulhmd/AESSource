package org.aes.core.java.test;

import java.io.File;

import java.util.ArrayList;


import org.aes.core.java.AESJavaScanner;
import org.aes.model.metadata.app.JavaApplicationModel;
import org.aes.model.metadata.app.Model;
import org.aes.model.metadata.report.ApplicationReport;
import org.aes.validator.drools.engine.AESDroolsEngine;

import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;


public class AESTest {
	
	public static String filePath="D://DART//Lab//Projects//EL//AESSource//AES-Core-Java//src//org//aes//core//java//test//data//sample2.jsp";
	public static String rulesetPath = "//scanner-ruleset.xml";
	
	public static void main(String as[]){
		
		
		try{
			File file = new File(filePath);
			String path = AESTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			System.out.println(path);
			RuleSet rules = new RuleSetFactory().createRuleSet(path + rulesetPath);
			
			JavaApplicationModel model = new JavaApplicationModel();
			AESJavaScanner scanner = new AESJavaScanner();
	        Report report = new Report();
			//scanner.scanFile(file, model, rules, report);
			//Added a comment
			scanner.scanFile(file, model, path + rulesetPath, report);
			
			JavaApplicationModel.processMapData();
			testRuleEngine();
		}
		catch(Exception ex3){
			ex3.printStackTrace();
		}
		
        
	}
	
	
	public static void testRuleEngine(){
		
		Model model = JavaApplicationModel.getModel();
		//TechnologyModel techModel = new TechnologyModel();
		//techModel.setPrimaryDAO("Hibernate");
		//model.setTechnologyModel(techModel);
		
		ApplicationReport report = new ApplicationReport();
		AESDroolsEngine engine = new AESDroolsEngine();
		try{
			engine.triggerJavaRules(model, report);
			ArrayList<String> aList = report.getResult();
			System.out.println(aList.toString());
		}
		catch(Exception e){
			System.out.println("Unknown Error");
		}
		

		
	}

}
