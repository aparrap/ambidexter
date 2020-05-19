package gmbh.ambidexter.automation.managers;

import gmbh.ambidexter.automation.base.BaseUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.net.UrlChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Attachment;

public class AppiumDriverManager {
    private static AppiumDriverLocalService appiumService;
    private static AppiumServiceBuilder builder;
    private static ThreadLocal<AppiumDriver> mThreadLocal = new ThreadLocal<>();
    private AppiumDriver<?> appiumDriver;
    private final String IOS = "iOS";
    private final String ANDROID = "Android";
    private static final String MAC = "mac";
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUtil.class);

    public AppiumDriverManager() {
        BaseUtil.initSoftAssertion();
        BaseUtil.loadMobileCapabilities();
    }

    public AppiumDriver<?> getAppiumDriver() {
        if (appiumDriver == null) {
            try {
                appiumDriver = createAppiumDriver();
                setmThreadLocal(appiumDriver);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getThreadLocal();
    }

    public synchronized static AppiumDriver getThreadLocal() {
        return mThreadLocal.get();
    }

    public synchronized static void setmThreadLocal(AppiumDriver appiumDriver) {
        mThreadLocal.set(appiumDriver);
    }

    public static void startMacAppiumServer(String address, String port) {
        builder = (builder == null) ? builder = new AppiumServiceBuilder() : builder;

        String OS = System.getProperty("os.name");

        if (OS.toLowerCase().contains(MAC)) {
            builder.usingDriverExecutable(new File(BaseUtil.getAppiumDriverExecutable()));
            builder.withAppiumJS(new File(BaseUtil.getAppiumJS()));
        } else {
            builder.usingDriverExecutable(new File(BaseUtil.getAppiumDriverExecutable()));
            builder.withAppiumJS(new File(BaseUtil.getAppiumJS()));
        }

        builder.withIPAddress(address);
        builder.usingPort(Integer.parseInt(port));
        appiumService = builder.build();
        if (!checkIfServerIsRunning(address, Integer.parseInt(port))) {
            appiumService.start();
        }
    }

    public AppiumDriver createAppiumDriver()
            throws IOException {
        if (!BaseUtil.getTestingDevice().contains("grid")) {
            AppiumDriverManager.startAppiumServer(BaseUtil.getAppiumServerAddress(), BaseUtil.getAppiumServerPort());
        }
        try {
            String device = BaseUtil.getDesiredMobileCapabilities().getCapability("platformName").toString();
            String url = "http://" + BaseUtil.getAppiumServerAddress() + ":" + BaseUtil.getAppiumServerPort() + "/wd/hub";
            switch (device) {
                case IOS:
                    appiumDriver = new IOSDriver<>(new URL(url), BaseUtil.getDesiredMobileCapabilities());
                    appiumDriver.manage().logs().get("syslog");
                    break;
                case ANDROID:
                    appiumDriver = new AndroidDriver<>(new URL(url), BaseUtil.getDesiredMobileCapabilities());
                    appiumDriver.manage().logs().get("logcat");
                    break;
            }
        } catch (NullPointerException | WebDriverException e) {
            LOGGER.info(e.getMessage());
            System.err.println(e);
        }
        return appiumDriver;
    }

    private int generateRandomPort(int min, int max) {
        Random randomPort = new Random();
        return randomPort.nextInt((max - min) + 1) + min;
    }

    private static boolean checkIfServerIsRunning(String address, int port) {
        final String SERVER_URL = String.format("http://" + address + ":%d/wd/hub", port);
        try {
            final URL status = new URL(SERVER_URL + "/status");
            new UrlChecker().waitUntilAvailable(2000, TimeUnit.MILLISECONDS, status);
            return true;
        } catch (IOException | UrlChecker.TimeoutException e) {
            return false;
        }
    }

    public String takeScreenShot() {
        File permScreenShot = null;

        try {
            File tempScreenShot = getAppiumDriver().getScreenshotAs(OutputType.FILE);
            permScreenShot = new File(BaseUtil.getLogPath() + "/screenShot_" + BaseUtil.getDate() + ".png");
            FileUtils.copyFile(tempScreenShot, permScreenShot);
        } catch (IOException e) {

        }
        return permScreenShot.getAbsolutePath();
    }

    public static void startAppiumServer(String address, String port) {
        startMacAppiumServer(address, port);
    }

    public static void stopAppiumServer() {
        appiumService.stop();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] makeAllureScreenShot() {
        return getAppiumDriver().getScreenshotAs(OutputType.BYTES);
    }
}