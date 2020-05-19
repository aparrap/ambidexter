package gmbh.ambidexter.automation.managers;

import gmbh.ambidexter.automation.base.BaseUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDriverManager {

    private WebDriver mWebDriver;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUtil.class);

    public WebDriver getWebDriver() {
        if (mWebDriver == null) {
            mWebDriver = createWebDriver();
        }
        return mWebDriver;
    }

    public WebDriver createWebDriver() {
        WebDriver driver = null;
        try {
            if (BaseUtil.getWebCapabilities().get("browserName").toString().equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (Boolean.parseBoolean(BaseUtil.getWebCapabilities().get("isHeadLess").toString())) {
                    firefoxOptions.setHeadless(true);
                }
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                driver = new FirefoxDriver(firefoxOptions);
            } else if (BaseUtil.getWebCapabilities().get("browserName").toString().equals("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("----disable-notifications");
                if (Boolean.parseBoolean(BaseUtil.getWebCapabilities().get("headless").toString())) {
                    chromeOptions.addArguments("--headless");
                }
                if (Boolean.parseBoolean(BaseUtil.getWebCapabilities().get("mobileEmulated").toString())) {
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", BaseUtil.getWebCapabilities().get("emulatedDevice").toString());
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                }
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
        } catch (NullPointerException e) {
            LOGGER.info(e.getMessage());
        }
        return driver;
    }
}
