package org.aes.core.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.PMDException;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.cpd.Renderer;
import net.sourceforge.pmd.renderers.XMLRenderer;
import net.sourceforge.pmd.util.datasource.DataSource;
import net.sourceforge.pmd.util.datasource.FileDataSource;

import org.aes.model.metadata.app.JavaApplicationModel;

public class AESJavaScanner {
	
	public void scanFile(File file, JavaApplicationModel model, RuleSet ruleSet, Report report){
		List<DataSource> files = new ArrayList<DataSource>();
		
		if(file != null && file.exists()){
			
			try
			{
				files.add(new FileDataSource(file));
				RuleContext ctx = new RuleContext();
		        //ctx.setSourceCodeFilename(file.getName());
		        
		        scanFileWithRules(files, ruleSet, ctx, report, model);
		        
		        
			}
			catch(PMDException ex2){
				//to do log
				System.out.println("ERROR: PMD Scanner Exception: " + ex2.getMessage());
			}
			
			
		}
		else
		{
			//to do log
			System.out.println("ERROR : Invalid file or no file");
		}
		
	}
	
	
	public void scanFile(File file, JavaApplicationModel model, String ruleSet, Report report){
		List<DataSource> files = new ArrayList<DataSource>();
		
		if(file != null && file.exists()){
			
			try
			{
				for(int i=0; i<100; i++){
				files.add(new FileDataSource(file));
				}
				RuleContext ctx = new RuleContext();
		       // ctx.setSourceCodeFilename(file.getName());
		        
		        scanFileWithRules(files, ruleSet, ctx);
		        
		        
			}
			catch(PMDException ex2){
				//to do log
				System.out.println("ERROR: PMD Scanner Exception: " + ex2.getMessage());
			}
			
			
		}
		else
		{
			//to do log
			System.out.println("ERROR : Invalid file or no file");
		}
		
	}
	
	
	private void scanFileWithRules(List<DataSource> files, RuleSet ruleset, RuleContext context, Report report, JavaApplicationModel model) throws PMDException{
		PMD p = new PMD();
		PMDConfiguration config = new PMDConfiguration();
		AESMonoThreadProcessor processor = new AESMonoThreadProcessor(config);
		//context.setReport(report);
		RuleSets ruleSets = new RuleSets(ruleset);
		processor.processFiles(ruleSets, files, context);
		//p.processFile(reader, ruleSets, context);
	}
	
	private void scanFileWithRules(List<DataSource> files, String ruleset, RuleContext context) throws PMDException{
		PMD p = new PMD();
		PMDConfiguration config = new PMDConfiguration();
		config.setRuleSets(ruleset);
		AESMultiThreadProcessor processor = new AESMultiThreadProcessor(config);
		RuleSetFactory factory = new RuleSetFactory();
		processor.processFiles(factory, files, context);
		//p.processFile(reader, ruleSets, context);
	}

}
