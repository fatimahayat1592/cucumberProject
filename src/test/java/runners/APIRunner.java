package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "APIStepDef",
        dryRun = false,
        tags ="@dynamic",
        monochrome = true,

        plugin = {"pretty"}
)
public class APIRunner {
}
