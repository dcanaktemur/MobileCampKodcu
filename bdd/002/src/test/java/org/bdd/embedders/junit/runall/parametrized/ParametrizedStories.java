package org.bdd.embedders.junit.runall.parametrized;

import org.bdd.calculator.WeightClassifierSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.SilentlyAbsorbingFailure;
import org.jbehave.core.io.*;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

/**
 * Example of an embedder which launches a single story.
 */
public class ParametrizedStories extends JUnitStories {

    private static final String INCLUDED_STORIES = "**/Parametrized*.story";
    private static final String EXCLUDED_STORIES = "";

    // --- Methods (JBehave) ---------------------------------------------------


    public ParametrizedStories() {
        super.configuredEmbedder().embedderControls().doIgnoreFailureInStories(false).doIgnoreFailureInView(false);
    }

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
                new WeightClassifierSteps()).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        List<String> storyPaths =  new StoryFinder().findPaths(
                CodeLocations.codeLocationFromClass(this.getClass()),
                INCLUDED_STORIES, EXCLUDED_STORIES);
        return storyPaths;
    }




}
