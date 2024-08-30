package com.RestAPI_BDDAndJava.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/RestAPI_BDDAndJava/features",
		glue = "com.RestAPI_BDDAndJava.stepDefinitions",
		plugin = {"pretty","html:target/cucumber-reports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true
		)

public class RunALLTestcases {

}
