package org.aes.core.java.rules;

import org.aes.model.metadata.app.JavaApplicationModel;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceType;
import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;




public class TechnologyScannerRule extends AbstractJavaRule {
	
	
	private boolean isHibernateSessionImported = false;
	
	
	@Override
	public Object visit(ASTClassOrInterfaceType type, Object data){
		
		if("Statement".equals(type.getImage()) && isHibernateSessionImported){
			
			JavaApplicationModel.putData("TechnologyModel.PrimaryDAO", "Hibernate");
		}
		
		return super.visit(type, data);
	}
	
	@Override
	public Object visit(ASTImportDeclaration importDeclar, Object data) {
		String importPkg = importDeclar.getImportedName();
		if (importPkg != null) {

				if (importPkg.contains("java.sql.Statement")) {
					isHibernateSessionImported = true;
				}
		}
		return super.visit(importDeclar, data);
	}

}
