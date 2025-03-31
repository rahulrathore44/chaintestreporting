package com.learning.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber-report.html", "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"},
        glue = {"com.learning.stepdfn", "com.learning.hooks"}
)
public class BugZillaRunner extends AbstractTestNGCucumberTests {

}
