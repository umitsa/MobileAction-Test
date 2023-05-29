package com.mobileAction.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "html:target/SystemTestReports/html",
                //"junit:target/SystemTestReports/junit/report.xml"
                },
        monochrome = false,
        features  = "src/test/resources/features",
        glue      = "com/mobileAction/stepDefinitions",
        dryRun    = false,
        tags      = "@login_with_credential_on_public_site"

)
public class MARunner {
}
