package org.aes.core.scanner.rules.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;

import org.aes.core.scanner.rules.ScannerRule;
import org.aes.core.scanner.util.AESScannerUtil;
import org.aes.metadata.root.AESMetaData;
import org.aes.metadata.root.ClassMetaData;

public class AESJavaImportScannerRule {

	public static void process(ASTImportDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, List<ScannerRule> scanRules) {
		
		for(ScannerRule rule: scanRules){
			
			if("IMPORT".equals(rule.getForType()) && rule.isActiveRule()){
				if("COMPARE".equals(rule.getScanType())){
					processCompare(node, data, metaData, classMetaData, rule);
				}
				else
				{
					processCustom(node, data, metaData, classMetaData, rule);
				}
			}
		}
		
	}

	private static void processCustom(ASTImportDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, ScannerRule rule) {

		try{
			String methodName = "process" + rule.getName();	
			Class<AESJavaImportScannerRule> clazz = AESJavaImportScannerRule.class;
			Method method = clazz.getDeclaredMethod(methodName, ASTImportDeclaration.class, Object.class, AESMetaData.class, ClassMetaData.class, ScannerRule.class);
			method.setAccessible(true);
			method.invoke(null, node, data, metaData, classMetaData, rule);
		}
		catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex2){
			System.out.println(ex2.getMessage());
		}

		
	}

	private static void processCompare(ASTImportDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, ScannerRule rule) {

		List<String> patterns = rule.getScanPatterns();
		
		for(String pattern: patterns){
			if(AESScannerUtil.comparePattern(node.getImportedName(), pattern)){
				System.out.println("Found " + rule.getName());
				AESScannerUtil.loadFoundData(classMetaData, rule.getName(), node.getBeginLine(), null);
				break;
			}
		}
		
	}
	
	
	private static void processSAMPLECUSTOM(ASTImportDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, ScannerRule rule){
		System.out.println("Sample Rule processed");
	}

}
