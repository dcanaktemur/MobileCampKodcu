package com.kodcu.jbehave;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

public class StackScenarios extends JUnitStories {


    private static final String INCLUDED_STORIES = "**/*.story";
    private static final String EXCLUDED_STORIES = "";

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
		return new InstanceStepsFactory(configuration(), new StackSteps())
				.createCandidateSteps();
	}

    @Override
    protected List<String> storyPaths() {
        List<String> storyPaths =  new StoryFinder().findPaths(
                CodeLocations.codeLocationFromClass(this.getClass()),
                INCLUDED_STORIES, EXCLUDED_STORIES);
        return storyPaths;
    }


	
	@Override
	@Test
	public void run() {
		try {
			super.run();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
