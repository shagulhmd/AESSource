package org.aes.core.scanner.rules.java;

import java.util.List;

import org.aes.core.scanner.rules.ScannerRule;
import org.aes.core.scanner.util.AESScannerUtil;
import org.aes.metadata.root.AESMetaData;
import org.aes.metadata.root.ClassMetaData;

import net.sourceforge.pmd.lang.java.ast.ASTPackageDeclaration;

public class AESJavaPackageScannerRule {
	
	public static void process(ASTPackageDeclaration node, Object data, AESMetaData metaData,
			ClassMetaData classMetaData, List<ScannerRule> scanRules){
		
		for(ScannerRule rule: scanRules){
			
			if("PACKAGE".equals(rule.getForType()) && rule.isActiveRule()){
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

	private static void processCompare(ASTPackageDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, ScannerRule rule) {
		List<String> patterns = rule.getScanPatterns();
		
		for(String pattern: patterns){
			if(AESScannerUtil.comparePattern(node.getPackageNameImage(), pattern)){
				System.out.println("Found " + rule.getName());
				AESScannerUtil.loadFoundData(classMetaData, rule.getName(), node.getBeginLine(), null);
				break;
			}
		}
		
	}

	private static void processCustom(ASTPackageDeclaration node, Object data,
			AESMetaData metaData, ClassMetaData classMetaData, ScannerRule rule) {
		// TODO Auto-generated method stub
		
	}

}
