package org.aes.validator.drools.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsError;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;
import org.aes.model.metadata.report.ApplicationReport;
import org.aes.model.metadata.app.Model;

public class AESDroolsEngine {
	
	
	public void triggerJavaRules(Model appModel, ApplicationReport report)  {
		
		try{
			RuleBase ruleBase = initDrools();
			WorkingMemory workingMemory = initializeObjects(ruleBase, appModel, report);
			workingMemory.fireAllRules();
		}
		catch(DroolsParserException ex2){
			//TODO Log
			System.out.println("Drools Exception: " + ex2.getMessage());
		}
		catch(IOException ex){
			//TODO Log
			System.out.println("IOException: " + ex.getMessage());
		}
	}
	
	private WorkingMemory initializeObjects(RuleBase ruleBase,
			Model appModel, ApplicationReport report) {
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		workingMemory.insert(appModel);
		workingMemory.insert(report);
		
		return workingMemory;
	}

	private RuleBase initDrools() throws IOException, DroolsParserException {
		
		PackageBuilder packageBuilder = readRuleFiles();
        return addRulesToWorkingMemory(packageBuilder);
	}
	
	private RuleBase addRulesToWorkingMemory(PackageBuilder packageBuilder) {
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        org.drools.rule.Package rulesPackage = packageBuilder.getPackage();
        ruleBase.addPackage(rulesPackage);

        return ruleBase;
	}

	private PackageBuilder readRuleFiles() throws IOException, DroolsParserException {
		
		PackageBuilder packageBuilder = new PackageBuilder();
		
		String ruleFile = "/java-rules.drl";
		Reader reader = getRuleFilesAsReader(ruleFile);
		packageBuilder.addPackageFromDrl(reader);
		
		assertNoRuleErrors(packageBuilder);
		
		return packageBuilder;
	}

	private Reader getRuleFilesAsReader(String ruleFile) {
		InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
        return new InputStreamReader(resourceAsStream);
	}
	
	private void assertNoRuleErrors(PackageBuilder packageBuilder) {
        PackageBuilderErrors errors = packageBuilder.getErrors();

        if (errors.getErrors().length > 0) {
            StringBuilder errorMessages = new StringBuilder();
            errorMessages.append("Found errors in package builder\n");
            for (int i = 0; i < errors.getErrors().length; i++) {
                DroolsError errorMessage = errors.getErrors()[i];
                errorMessages.append(errorMessage);
                errorMessages.append("\n");
            }
            errorMessages.append("Could not parse knowledge");

            throw new IllegalArgumentException(errorMessages.toString());
        }
    }

}
