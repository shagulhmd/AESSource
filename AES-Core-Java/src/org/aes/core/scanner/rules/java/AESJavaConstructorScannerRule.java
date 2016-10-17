package org.aes.core.scanner.rules.java;

import java.util.List;

import net.sourceforge.pmd.lang.java.ast.ASTConstructorDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;

import org.aes.core.scanner.rules.ScannerRule;
import org.aes.metadata.root.AESMetaData;
import org.aes.metadata.root.ClassMetaData;

public class AESJavaConstructorScannerRule {
	
	
	public static void process(ASTConstructorDeclaration node, Object data,
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

	private static void processCustom(ASTConstructorDeclaration node,
			Object data, AESMetaData metaData, ClassMetaData classMetaData,
			ScannerRule rule) {
		// TODO Auto-generated method stub
		
	}

	private static void processCompare(ASTConstructorDeclaration node,
			Object data, AESMetaData metaData, ClassMetaData classMetaData,
			ScannerRule rule) {
		// TODO Auto-generated method stub
		
	}

}
