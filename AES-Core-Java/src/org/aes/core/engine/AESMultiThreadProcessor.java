package org.aes.core.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.processor.AbstractPMDProcessor;
import net.sourceforge.pmd.processor.PmdRunnable;
import net.sourceforge.pmd.processor.PmdThreadFactory;
import net.sourceforge.pmd.renderers.Renderer;
import net.sourceforge.pmd.util.datasource.DataSource;

public class AESMultiThreadProcessor extends AbstractPMDProcessor {


	public AESMultiThreadProcessor(final PMDConfiguration configuration) {
		super(configuration);
	}

	/**
	 * Run PMD on a list of files using multiple threads.
	 */
	public void processFiles(final RuleSetFactory ruleSetFactory, final List<DataSource> files,
			final RuleContext ctx) {

		RuleSets rs = createRuleSets(ruleSetFactory);
		rs.start(ctx);
		List<Renderer> renderers = new ArrayList<Renderer>();

		PmdThreadFactory factory = new PmdThreadFactory(ruleSetFactory, ctx);
		ExecutorService executor = Executors.newFixedThreadPool(
				configuration.getThreads(), factory);

		for (DataSource dataSource : files) {
			String niceFileName = filenameFrom(dataSource);

			PmdRunnable r = new PmdRunnable(executor, configuration,
					dataSource, niceFileName, renderers);
			executor.submit(r);
		}
		executor.shutdown();
		
		rs.end(ctx);

	}

}
