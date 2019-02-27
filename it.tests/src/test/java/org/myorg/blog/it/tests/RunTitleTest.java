package org.myorg.blog.it.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/main/features/title",
	plugin = { "pretty", "html:target/cucumber-title-html-report", "json:target/cucumber-title-report.json" })
public class RunTitleTest {
	// empty
}
