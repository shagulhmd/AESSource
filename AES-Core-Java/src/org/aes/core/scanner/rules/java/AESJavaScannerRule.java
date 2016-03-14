package org.aes.core.scanner.rules.java;

import java.util.ArrayList;
import java.util.List;

import org.aes.core.scanner.rules.AESScannerRulesConfig;
import org.aes.core.scanner.rules.ScannerRule;
import org.aes.metadata.root.AESMetaData;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class AESJavaScannerRule extends AbstractJavaRule {
	
	private AESMetaData metaData;
	private AESScannerRulesConfig javaconfig;
	private List<ScannerRule> scanRules;
	
	
	@Override
	public Object visit(ASTCompilationUnit node, Object data) {

		RuleContext ctx = (RuleContext) data;
		metaData = (AESMetaData) ctx.getAttribute("AESDATA");
		javaconfig = (AESScannerRulesConfig) ctx.getAttribute("JAVACONFIG");
		
		if(javaconfig != null){
			scanRules = javaconfig.getScannerRules();
		}
		else
		{
			scanRules = new ArrayList<ScannerRule>();
		}
		System.out.println(scanRules.toString());
		return super.visit(node, data);
	}

}
