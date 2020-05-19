package gmbh.ambidexter.automation.runner.ngrunner;

import gmbh.ambidexter.automation.base.BaseUtil;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        monochrome = true,
        strict = true,
        plugin = {
                "pretty",
                "html:target/site/cucumber-pretty",
                "html:target/cucumber-reports",
                "json:target/cucumber.json",
                "rerun:rerun/failed_scenarios.txt",
                "testng:target/cucumber-results.xml",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        glue = {"com.ghm.free2move.automation.steps.mobileapp",
                "com.ghm.free2move.automation.steps.generic",
                "com.ghm.free2move.automation.steps.rest"})
public class TestTrialNG
        extends AbstractTestNGCucumberTests {

    @BeforeSuite()
    public static void setUp() {
    }

    @AfterSuite
    public static void tearDown() {
        String workingDirectory = System.getProperty("user.dir");
        Properties properties = new Properties();
        File allureProperties = new File(workingDirectory + "/target/allure-results/environment.properties");

        try {
            allureProperties.createNewFile();

            OutputStream out = new FileOutputStream(allureProperties);
            properties.store(out, "Env variables for allure testing");
        } catch (IOException e) {

        }
    }
}