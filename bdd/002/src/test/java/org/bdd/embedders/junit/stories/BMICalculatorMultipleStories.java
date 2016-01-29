package org.bdd.embedders.junit.stories;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.bdd.calculator.MetricBMICalculatorSteps;

/**
 * Example of an embedder which launches multiple story.
 * 
 */
public class BMICalculatorMultipleStories extends JUnitStories {

	// --- Constants -----------------------------------------------------------

	private static final String INCLUDED_STORIES = "**/*-calculator-story-0*.story";
	private static final String EXCLUDED_STORIES = "";

	// --- Methods (JBehave) ---------------------------------------------------

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(
						new StoryReporterBuilder().withCodeLocation(
								CodeLocations.codeLocationFromClass(this
										.getClass())).withFormats(
								Format.CONSOLE, Format.TXT, Format.HTML));
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(),
				new MetricBMICalculatorSteps()).createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
        List<String> storyPaths =  new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()),
				INCLUDED_STORIES, EXCLUDED_STORIES);
        return storyPaths;
	}
}
