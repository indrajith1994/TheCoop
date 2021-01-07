package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"/Users/indrajit/IdeaProjects/cucumbertestone/features/TheCoop.feature"},
        glue={"stepdef","TheCoop"},
        monochrome=true)
public class runner {
}
