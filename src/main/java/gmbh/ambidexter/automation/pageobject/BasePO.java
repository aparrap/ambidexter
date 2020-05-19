package gmbh.ambidexter.automation.pageobject;

import gmbh.ambidexter.automation.base.BaseUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePO {
    private WebDriver webDriver;
    private Wait<WebDriver> wait;

    public BasePO(WebDriver driver) {
        this.webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, 30);
        PageFactory.initElements(factory, this);
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void scrollUntilVisible(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getSeleniumScreenShot()
            throws IOException {
        File tempScreenShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File permScreenShot = new File(BaseUtil.getLogPath() + "/screenShot_" + BaseUtil.getDate() + ".png");
        FileUtils.copyFile(tempScreenShot, permScreenShot);

        return permScreenShot.getAbsolutePath();
    }

    public void setWaitTimeAndPollingTimeInSeconds(int waitTime, int pollingTime) {
        wait = new FluentWait<>(getDriver());
        ((FluentWait<WebDriver>) wait).withTimeout(Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofSeconds(pollingTime))
                                      .ignoring(WebDriverException.class).ignoring(ClassCastException.class).ignoring(NoSuchElementException.class);
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }
}