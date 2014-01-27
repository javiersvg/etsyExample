package org.caponford.renman;

import org.caponford.jbehave.ScreenshootingHtmlFormat;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.*;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.XML;

public class RenmanDotComStories extends JUnitStories {
    
    CrossReference crossReference = new CrossReference().withJsonOnly().withOutputAfterEachStory(true)
            .excludingStoriesWithNoExecutedScenarios(true);
    ContextView contextView = new LocalFrameContextView().sized(640, 120);
    SeleniumContext seleniumContext = new SeleniumContext();
    SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView, seleniumContext,
            crossReference.getStepMonitor());
    ApplicationContext context = new SpringApplicationContextFactory("renman-steps.xml").createApplicationContext();
    private Format screenshootingFormat =
            new ScreenshootingHtmlFormat(context);
    Format[] formats = new Format[] {XML, new SeleniumContextOutput(seleniumContext), CONSOLE, screenshootingFormat };
    StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(RenmanDotComStories.class)).withFailureTrace(true)
            .withFailureTraceCompression(true).withDefaultFormats().withFormats(formats)
            .withCrossReference(crossReference);

    @Override
    public Configuration configuration() {
        return new SeleniumConfiguration().useSeleniumContext(seleniumContext)
                .useFailureStrategy(new FailingUponPendingStep())
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(false)).useStepMonitor(stepMonitor)
                .useStoryLoader(new LoadFromClasspath(RenmanDotComStories.class))
                .useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), context);
    }


    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/" + System.getProperty("storyFilter", "renman*")
                + ".story"), null);
    }

}
