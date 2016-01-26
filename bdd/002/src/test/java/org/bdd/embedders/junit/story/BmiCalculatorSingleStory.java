package org.bdd.embedders.junit.story;

import java.net.URL;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.bdd.calculator.MetricBMICalculatorSteps;

/**
 * Example of an embedder which launches a single story.
 * 
 */
public class BmiCalculatorSingleStory extends JUnitStory {

	// --- Constructors --------------------------------------------------------
	
	public BmiCalculatorSingleStory() {

		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties rendering = new Properties();
		rendering.put("decorateNonHtml", "true");
		URL codeLocation = CodeLocations.codeLocationFromClass(embeddableClass);

		Configuration configuration = new MostUsefulConfiguration();

		// where to find the stories
		configuration.useStoryLoader(new LoadFromClasspath(this.getClass()));
		// CONSOLE and TXT reporting
		StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder();
		storyReporterBuilder.withCodeLocation(codeLocation);
		storyReporterBuilder.withDefaultFormats().withViewResources(rendering)
				.withFormats(Format.CONSOLE, Format.TXT)
				.withFailureTrace(false);

		configuration.useStoryReporterBuilder(storyReporterBuilder);
		// path resolver
		StoryPathResolver storyPathResolver = new UnderscoredCamelCaseResolver(
				".story");
		configuration.useStoryPathResolver(storyPathResolver);
		configuration.useStepMonitor(new SilentStepMonitor());

		useConfiguration(configuration);
		addSteps(new InstanceStepsFactory(configuration(),
				new MetricBMICalculatorSteps()).createCandidateSteps());

		configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
	}
}
