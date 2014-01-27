package org.caponford.jbehave;

import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.springframework.context.ApplicationContext;

public class ScreenshootingHtmlFormat extends Format {
    private ApplicationContext context;

    public ScreenshootingHtmlFormat(ApplicationContext context) {
        super("HTML");
        this.context = context;
    }

    @Override
    public StoryReporter createStoryReporter(
            FilePrintStreamFactory factory,
            StoryReporterBuilder builder) {
        factory.useConfiguration(
                builder.fileConfiguration("html"));
        return new ScreenshootingHtmlOutput(factory.createPrintStream(),
                builder, context)
                .doReportFailureTrace(builder.reportFailureTrace())
                .doCompressFailureTrace(builder.compressFailureTrace());
    }
}
