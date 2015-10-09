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
	
	public static String filePath="F://Java//JavaLab//AES//Codebase//AES-Core-Java//src//org//aes//core//java//test//SampleJDBC.java";
	public static String rulesetPath = "F://Java//JavaLab//AES//Codebase//AES-Core-Java//src//resources//scanner-ruleset.xml";
	
	public static void main(String as[]){
		
		
		try{
			File file = new File(filePath);
			RuleSet rules = new RuleSetFactory().createRuleSet(rulesetPath);
			
			JavaApplicationModel model = new JavaApplicationModel();
			AESJavaScanner scanner = new AESJavaScanner();
	        Report report = new Report();
			//scanner.scanFile(file, model, rules, report);
			scanner.scanFile(file, model, rulesetPath, report);
			
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
