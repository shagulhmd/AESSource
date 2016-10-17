package org.aes.core.scanner.rules.java;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTPackageDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import org.aes.core.scanner.rules.AESScannerRulesConfig;
import org.aes.core.scanner.rules.ScannerRule;
import org.aes.core.util.AESCoreUtil;
import org.aes.metadata.root.AESMetaData;
import org.aes.metadata.root.ClassMetaData;

public class AESJavaScannerRule extends AbstractJavaRule {
	
	private AESMetaData metaData;
	private AESScannerRulesConfig javaconfig;
	private List<ScannerRule> scanRules;
	private ClassMetaData classMetaData;
	private List<String> importStmts = new ArrayList<String>();
	
	
	@Override
	public Object visit(ASTCompilationUnit node, Object data) {

		classMetaData = new ClassMetaData();
		RuleContext ctx = (RuleContext) data;
		metaData = (AESMetaData) ctx.getAttribute("AESDATA");
		javaconfig = (AESScannerRulesConfig) ctx.getAttribute("JAVACONFIG");
		classMetaData.setApplicationCode((String) ctx.getAttribute("APPCODE"));
		
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
	
	
	@Override
	public Object visit(ASTPackageDeclaration node, Object data) {
		
		if(AESCoreUtil.isNotNullOrEmpty(node.getPackageNameImage())){
			classMetaData.setPackageName(node.getPackageNameImage());
		}
		else
		{
			classMetaData.setPackageName("default");
		}
		
		AESJavaPackageScannerRule.process(node, data, metaData, classMetaData, scanRules);

		return super.visit(node, data);
		
	}
	
	
	@Override
	public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {

		if(AESCoreUtil.isNotNullOrEmpty(node.getImage())){
			classMetaData.setClassName(node.getImage());
			classMetaData.setClassKey(classMetaData.getPackageName() + "." + node.getImage());
			metaData.getClassMetaDatas().put(classMetaData.getClassKey(), classMetaData);
		}
		
		return super.visit(node, data);
	}
	
	
	@Override
	public Object visit(ASTImportDeclaration node, Object data) {
		
		importStmts.add(node.getImportedName());
		classMetaData.addImport(node.getImportedName());
		
		AESJavaImportScannerRule.process(node, data, metaData, classMetaData, scanRules);
		
		return super.visit(node, data);
	}
	
	@Override
	public Object visit(ASTMethodDeclaration node, Object data) {
		
		classMetaData.addImport(node.getMethodName());
		
		AESJavaMethodScannerRule.process(node, data, metaData, classMetaData, scanRules);
		
		return super.visit(node, data);
	}

}
