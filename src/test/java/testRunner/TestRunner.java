package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {".//Features/"},
		//features = {".//Features//LoginDDTExcel.feature"},
		//features="@target/rerun.txt",
		glue = "stepDefinations",
		plugin = {"pretty",
				  "html:Reports/myreport.html",
				  "json:Reports/myreport.json",
				  "rerun:taget/rerun.txt",
		        },
		dryRun = false,
		monochrome = true,
		tags = "@sanity"
		)
public class TestRunner
{
  
}
