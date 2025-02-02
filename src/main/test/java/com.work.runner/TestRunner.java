import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/features",
        glue="com.work.steps",
        plugin={"pretty","html:target/cucmber-report.html"}
)

public class TestRunner{}