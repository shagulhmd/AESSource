package org.aes.core.engine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.PMDException;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.SourceCodeProcessor;
import net.sourceforge.pmd.processor.AbstractPMDProcessor;
import net.sourceforge.pmd.util.datasource.DataSource;

public final class AESMonoThreadProcessor extends AbstractPMDProcessor {

	public AESMonoThreadProcessor(PMDConfiguration configuration) {
		super(configuration);
	}

	private static final Logger LOG = Logger.getLogger(AESMonoThreadProcessor.class.getName());

	public void processFiles(RuleSets rs, List<DataSource> files,
			RuleContext ctx) {

		// single threaded execution

		SourceCodeProcessor processor = new SourceCodeProcessor(configuration);
		
		for (DataSource dataSource : files) {
			String niceFileName = filenameFrom(dataSource);
					
			Report report = PMD.setupReport(rs, ctx, niceFileName);
			
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("Processing " + ctx.getSourceCodeFilename());
			}
			rs.start(ctx);

			try {
				InputStream stream = new BufferedInputStream(dataSource.getInputStream());
				ctx.setLanguageVersion(null);
				processor.processSourceCode(stream, rs, ctx);
			} catch (PMDException pmde) {
			    if (LOG.isLoggable(Level.FINE)) {
			        LOG.log(Level.FINE, "Error while processing file: "+niceFileName, pmde.getCause());
			    }

				report.addError(new Report.ProcessingError(pmde.getMessage(), niceFileName));
			} catch (IOException ioe) {
				// unexpected exception: log and stop executor service
				addError(report, "Unable to read source file", ioe, niceFileName);
			} catch (RuntimeException re) {
				// unexpected exception: log and stop executor service
				addError(report, "RuntimeException while processing file", re, niceFileName);
			}

			rs.end(ctx);
		}
	}

	private void addError(Report report, String msg, Exception ex, String fileName) {
		LOG.log(Level.FINE,	msg, ex);
		report.addError(
				new Report.ProcessingError(ex.getMessage(),
				fileName)
				);
	}
}	