package gmbh.ambidexter.automation.pageobject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import gmbh.ambidexter.automation.base.BaseUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.offset.ElementOption.element;

/**
 * Created by aeparra on 7/06/17.
 */
public class MobileBasePO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileBasePO.class);
    private AppiumDriver<?> appiumDriver;
    private static JavascriptExecutor js;
    private static HashMap<String, Object> params;
    private Wait<AppiumDriver> wait;

    private static final String DIRECTION_RIGHT = "right";
    private static final String DIRECTION_LEFT = "left";
    private static final String DIRECTION_UP = "up";
    private static final String DIRECTION_DOWN = "down";

    private static final String DRIVER_CONTEXT_WEB = "web";
    private static final String DRIVER_CONTEXT_NATIVE = "native";

    public MobileBasePO(AppiumDriver<?> appiumDriver) {
        this.appiumDriver = appiumDriver;
        if(this.appiumDriver != null) {
            PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver, Duration.ofSeconds(20)), this);
        }
    }

    public AppiumDriver<?> getAppiumDriver() {
        return appiumDriver;
    }

    public void swipeJS(String direction, IOSElement element) {
        js = appiumDriver;
        params = new HashMap<>();
        params.put("direction", direction);
        params.put("element", element.getId());
        js.executeScript("mobile: swipe", params);
    }

    public void switchDriverContext(String context) {
        Set<String> driverContext = getAppiumDriver().getContextHandles();
        switch (context) {
            case DRIVER_CONTEXT_WEB:
                for (String localContext : driverContext) {
                    if (localContext.contains("WEBVIEW")) {
                        getAppiumDriver().context(localContext);
                    }
                }
                break;
            case DRIVER_CONTEXT_NATIVE:
                for (String localContext : driverContext) {
                    if (localContext.contains("NATIVE")) {
                        getAppiumDriver().context(localContext);
                    }
                }
                break;
        }
    }

    public void elementTap(MobileElement mobileElement) {
        TouchAction tap = new TouchAction(getAppiumDriver()).tap(TapOptions.tapOptions().withElement(element(mobileElement)).withTapsCount(1));
        tap.perform();
    }

    public void pressByElementPosition(MobileElement mobileElement) {
        int centerX = mobileElement.getCenter().getX();
        int centerY = mobileElement.getCenter().getY();

        TouchAction press = new TouchAction(getAppiumDriver());
        press.press(PointOption.point(centerX, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).release();
        press.perform();
    }

    protected void bottomElementTap(MobileElement mobileElement) {
        int pinPointY;
        int pinPointX;

        int width = mobileElement.getSize().getWidth();
        pinPointY = mobileElement.getCenter().getY();
        int elementCenterX = mobileElement.getCenter().getX();
        pinPointX = (elementCenterX + (width / 2)) - 5;

        TouchAction tap = new TouchAction(getAppiumDriver()).tap(TapOptions.tapOptions().withPosition(PointOption.point(pinPointX, pinPointY)).withTapsCount(1));
        tap.perform();
    }

    public void elementSwipe(MobileElement mobileElement, String direction) {

        Point point;
        Dimension dimension;

        point = mobileElement.getCenter();
        dimension = mobileElement.getSize();

        int startY;
        int startX;
        int endX = appiumDriver.manage().window().getSize().getWidth();
        int endY = appiumDriver.manage().window().getSize().getHeight();

        TouchAction actions = new TouchAction(appiumDriver);

        switch (direction) {
            case DIRECTION_RIGHT:
                startY = point.getY();
                startX = (point.getX() - (dimension.getWidth() / 2)) + 1;
                actions.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endX - 1, startY));
                break;
            case DIRECTION_LEFT:
                startY = point.getY();
                startX = (point.getX() + (dimension.getWidth() / 2)) - 1;
                actions.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(0, startY - 1));
                break;
            case DIRECTION_DOWN:
                startX = point.getX();
                startY = point.getY();
                actions.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startX, (startY + (dimension.getWidth() / 3) + 1)));
                break;
            case DIRECTION_UP:
                startX = point.getX();
                startY = point.getY();
                actions.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startX, (startY - (dimension.getWidth() / 3))));
        }
        actions.release().perform();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public void swipe(String direction) {
        int height = getAppiumDriver().manage().window().getSize().getHeight();
        int width = getAppiumDriver().manage().window().getSize().getWidth();

        TouchAction swipe = new TouchAction(getAppiumDriver()).press(PointOption.point(width / 2, height / 2));

        switch (direction) {
            case DIRECTION_UP:
                swipe.waitAction(WaitOptions.waitOptions(Duration.ofMillis(600))).moveTo(PointOption.point(width / 3, 0)).release();
                break;
            case DIRECTION_DOWN:
                swipe.waitAction(WaitOptions.waitOptions(Duration.ofMillis(600))).moveTo(PointOption.point(width / 3, (height - 1))).release();
                break;
            case DIRECTION_RIGHT:
                swipe.waitAction(WaitOptions.waitOptions(Duration.ofMillis(600))).moveTo(PointOption.point(width, height / 2)).release();
                break;
            case DIRECTION_LEFT:
                swipe.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(0, height / 2)).release();
                break;
        }
        swipe.perform();
    }

    public void scrollToTheElement(MobileElement mobileElement) {
        boolean elementFound = false;
        do {
            try {
                mobileElement.isDisplayed();
                elementFound = true;
            } catch (NoSuchElementException e) {
                fullSwipe("down");
            }
        } while (!elementFound);
    }

    public void fullSwipe(String direction) {
        Dimension size = getAppiumDriver().manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        TouchAction swipe = new TouchAction(getAppiumDriver());

        switch (direction) {
            case DIRECTION_UP:
                endY = (int) (size.height * 0.75);
                startY = (int) (size.height * 0.50);
                startX = (size.width / 2);
                swipe
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                        .moveTo(PointOption.point(startX, endY))
                        .release()
                        .perform();
                break;

            case DIRECTION_DOWN:
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height * 0.38);
                startX = (size.width / 2);
                swipe
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                        .moveTo(PointOption.point(startX, endY))
                        .release()
                        .perform();
                break;

            case DIRECTION_RIGHT:
                startY = (int) (size.height / 2);
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                swipe
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;

            case DIRECTION_LEFT:
                startY = (int) (size.height / 2);
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                swipe
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public String getAppiumScreenShot(AppiumDriver<?> appiumDriver) {
        File permScreenShot = null;
        try {
            File tempScreenShot = (appiumDriver).getScreenshotAs(OutputType.FILE);
            permScreenShot = new File(BaseUtil.getLogPath() + "/screenShot_" + BaseUtil.getDate() + ".png");
            FileUtils.copyFile(tempScreenShot, permScreenShot);
        } catch (IOException e) {

        }
        return permScreenShot.getAbsolutePath();
    }

    public void setWaitTimeAndPollingTimeInSeconds(int waitTime, int pollingTime) {
        wait = new FluentWait<>(getAppiumDriver());
        ((FluentWait<AppiumDriver>) wait).withTimeout(Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofSeconds(pollingTime))
                                         .ignoring(WebDriverException.class).ignoring(ClassCastException.class).ignoring(NoSuchElementException.class);
    }

    public Wait<AppiumDriver> getWait() {
        return wait;
    }

    public void selectIOSDate(String date) {
        String[] dOB = date.split(" ");
        List<MobileElement> dateList = (List<MobileElement>) getAppiumDriver().findElementsByClassName("XCUIElementTypePickerWheel");
        dateList.get(0).sendKeys(dOB[0]);
        dateList.get(1).sendKeys(dOB[1]);
        dateList.get(2).sendKeys(dOB[2]);
    }

    private File getImageFile(String imageName) {
        return new File("img/" + imageName + ".png");
    }

    public byte[] getReferenceImageB64(String imageName)
            throws IOException {
        return Base64.getEncoder().encode(Files.readAllBytes(getImageFile(imageName).toPath()));
    }

    public String getReferenceImageB64AsString(String imageName)
            throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(getImageFile(imageName).toPath()));
    }
}