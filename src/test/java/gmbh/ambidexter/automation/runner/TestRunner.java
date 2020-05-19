package gmbh.ambidexter.automation.runner;

import gmbh.ambidexter.automation.base.BaseUtil;
import gmbh.ambidexter.automation.managers.AppiumDriverManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        strict = true,
        plugin = {
                "pretty",
                "html:target/site/cucumber-pretty",
                "html:target/cucumber-reports",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml",
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        glue = {"app.choco.chocoapp.automation.steps.mobileapp",
                "app.choco.chocoapp.automation.steps.generic",
                "app.choco.chocoapp.automation.steps.rest"})
public class TestRunner {

    @BeforeClass
    public static void setUp() {
    }

    @AfterClass
    public static void tearDown() {
        String workingDirectory = System.getProperty("user.dir");
        Properties properties = new Properties();
        File allureProperties = new File(workingDirectory + "/target/allure-results/environment.properties");
        try {
            if (BaseUtil.getTestingDevice() != null) {
                if (!BaseUtil.getTestingDevice().contains("grid")) {
                    AppiumDriverManager.stopAppiumServer();
                }
            }
            allureProperties.createNewFile();
            if (BaseUtil.getOS().equals("iOS")) {
                //  properties.setProperty("Application", BaseUtil.getiOSAppName());
            } else {

                //  properties.setProperty("Application", BaseUtil.getAndroidAppName());
                properties.setProperty("App Package", BaseUtil.getAppPackageName());
            }
            properties.setProperty("OS", BaseUtil.getOS());
            properties.setProperty("Version", BaseUtil.getVersion());
            properties.setProperty("Device Name", BaseUtil.getMobileName());
            OutputStream out = new FileOutputStream(allureProperties);
            properties.store(out, "Env variables for allure testing");
        } catch (IOException | NullPointerException e) {

        }
    }
}