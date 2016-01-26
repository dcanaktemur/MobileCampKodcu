package org.bdd.embedders.junit.unparameter;

import org.bdd.WeightClassifier;
import org.bdd.calculator.MetricBMICalculatorSteps;
import org.bdd.calculator.WeightClassifierSteps;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.List;


@RunWith(AnnotatedEmbedderRunner.class)
//
@Configure(//
storyControls = AnnotatedNotParametrizedEmbedder.MyStoryControls.class,//
storyLoader = AnnotatedNotParametrizedEmbedder.MyStoryLoader.class,//
storyReporterBuilder = AnnotatedNotParametrizedEmbedder.MyReportBuilder.class,//
parameterConverters = {})
//
@UsingEmbedder(//
embedder = Embedder.class,//
generateViewAfterStories = true)//
//ignoreFailureInStories = true,//
//ignoreFailureInView = true)
//
@UsingSteps(instances = { MetricBMICalculatorSteps.class, WeightClassifierSteps.class})
public class AnnotatedNotParametrizedEmbedder extends InjectableEmbedder {

	// --- Constants -----------------------------------------------------------

	private static final String INCLUDED_STORIES = "**/NotParametrizedStory.story";
	private static final String EXCLUDED_STORIES = "";

	// --- Methods (JUnit) -----------------------------------------------------

	@Test
	public void run() {
		StoryFinder storyFinder = new StoryFinder();
		URL codeLocations = CodeLocations
				.codeLocationFromClass(this.getClass());
		List<String> storyPaths = storyFinder.findPaths(codeLocations,
				INCLUDED_STORIES, EXCLUDED_STORIES);
		injectedEmbedder().runStoriesAsPaths(storyPaths);
	}

	// --- Nested Classes (Auxliary) -------------------------------------------

	public static class MyStoryControls extends StoryControls {
		public MyStoryControls() {
			doDryRun(false);// matching okay or not
			doSkipScenariosAfterFailure(false);
		}
	}

	public static class MyStoryLoader extends LoadFromClasspath {
		public MyStoryLoader() {
			super(AnnotatedNotParametrizedEmbedder.class.getClassLoader());
		}
	}

	public static class MyReportBuilder extends StoryReporterBuilder {
		public MyReportBuilder() {
			this.withFormats(org.jbehave.core.reporters.Format.CONSOLE,
					org.jbehave.core.reporters.Format.TXT,
					org.jbehave.core.reporters.Format.HTML)
					.withDefaultFormats();
		}
	}
}
