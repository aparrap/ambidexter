package gmbh.ambidexter.automation.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmbh.ambidexter.automation.constants.Constants;
import gmbh.ambidexter.automation.utils.AppiumCapabilities;
import gmbh.ambidexter.automation.utils.RestHandlerConfig;
import gmbh.ambidexter.automation.utils.WebDriverConfig;
import com.google.gson.Gson;

import org.assertj.core.api.SoftAssertions;
import org.joda.time.DateTime;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import gmbh.ambidexter.automation.utils.CapabilitiesBuilder;

public class BaseUtil {

    private static DesiredCapabilities desiredMobileCapabilities;
    private static HashMap<String, Object> webCapabilities;

    private static String logPath;
    private static String latestExecPath;
    private static String date;
    private static File targetFolder;
    private static File latestExecFolder;
    private static String appiumServerAddress;
    private static String appiumServerPort;
    private static String appiumDriverExecutable;
    private static String appiumJS;
    private static String appPackageName;
    private static String deviceName;
    private static String iOSBundleId;
    private static String OS;
    private static String localOS;
    private static String version;
    private static String mobileName;
    private static String testingDevice;
    private static String projectPath;
    private static String testingBrowser;

    private static ObjectMapper mObjectMapper;
    private static String testLocation;

    private static SoftAssertions softly;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUtil.class);

    static {
        try (FileInputStream input = new FileInputStream("properties/.properties")) {
            Properties props = new Properties();
            date = getDate();
            props.load(input);
            loadProperties(props);
            loadSystemProperties();
            setLogFolders();
            setAppiumEnvirornment(props);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static void loadMobileCapabilities() {
        Gson gson = new Gson();
        String PATH = "./JsonConfig/";
        AppiumCapabilities appiumCapabilities;
        try {
            appiumCapabilities = gson.fromJson(new FileReader(PATH + testingDevice), AppiumCapabilities.class);
            desiredMobileCapabilities = CapabilitiesBuilder.processMobileCapabilities(appiumCapabilities.getClass().getDeclaredFields(), appiumCapabilities);
        } catch (IOException e) {
            LOGGER.info("Mobile capabilities not found");
        }
    }

    public static void loadWebCapabilities() {
        mObjectMapper = new ObjectMapper();
        String PATH = "./JsonConfig/";
        try {
            WebDriverConfig webDriverConfig = mObjectMapper.readValue(new File(PATH + testingBrowser), WebDriverConfig.class);
            CapabilitiesBuilder.processWebCapabilities(webDriverConfig.getClass().getDeclaredFields(), webDriverConfig);
        } catch (IOException e) {
            LOGGER.info("Mobile capabilities not found");
        }
    }

    public static void loadRestProperties(String jsonFile) {
        mObjectMapper = new ObjectMapper();
        String PATH = "./JsonConfig/";
        try {
            RestHandlerConfig restHandlerConfig = mObjectMapper.readValue(new File(PATH + jsonFile), RestHandlerConfig.class);
            CapabilitiesBuilder.processAPICapabilities(restHandlerConfig.getClass().getDeclaredFields(), restHandlerConfig);
        } catch (IOException e) {
            LOGGER.info("Mobile capabilities not found");
        }
    }

    private static void setLogFolders() {
        targetFolder = new File(projectPath + "/log/HISTORIC/TestRun_" + date + "/");
        latestExecFolder = new File(projectPath + "/log/LAST_RUN/");

        File[] list = latestExecFolder.listFiles();
        for (File tmp : list) {
            if (!tmp.getName().contains(".gitkeep")) {
                tmp.delete();
            }
        }

        if (targetFolder.mkdir()) {
            LOGGER.info("Folder created");
        } else {
            LOGGER.info("WARNING - Folder not created");
        }
        latestExecPath = latestExecFolder.getPath();
        logPath = targetFolder.getPath();
    }

    private static void setAppiumEnvirornment(Properties props) {
        if (localOS.toLowerCase().contains("mac")) {
            appiumDriverExecutable = props.getProperty(Constants.PROPS_APPIUM_DRIVER_EXECUTABLE);
            appiumJS = props.getProperty(Constants.PROPS_APPIUM_JS);
        } else {
            appiumDriverExecutable = props.getProperty(Constants.PROPS_WIN_APPIUM_DRIVER_EXECUTABLE);
            appiumJS = props.getProperty(Constants.PROPS_WIN_APPIUM_JS);
        }
    }

    private static void loadSystemProperties() {
        projectPath = System.getProperty("user.dir");
        testingDevice = System.getProperty("testingDevice") + ".json";
        testingBrowser = System.getProperty("testingBrowser") + ".json";
        deviceName = System.getProperty("deviceName");
        localOS = System.getProperty("os.name");
        testLocation = System.getProperty("testLocation");
    }

    private static void loadProperties(Properties props) {
        appiumServerAddress = props.getProperty(Constants.PROPS_APPIUM_SERVER_ADDRESS);
        appiumServerPort = props.getProperty(Constants.PROPS_APPIUM_SERVER_PORT);
        appiumServerAddress = props.getProperty(Constants.PROPS_APPIUM_SERVER_ADDRESS);
        appiumServerPort = props.getProperty(Constants.PROPS_APPIUM_SERVER_PORT);
    }

    public static void initSoftAssertion() {
        softly = new SoftAssertions();
    }

    public static SoftAssertions getSoftly() {
        return softly;
    }

    public static DesiredCapabilities getDesiredMobileCapabilities() {
        return desiredMobileCapabilities;
    }

    public static HashMap<String, Object> getWebCapabilities() {
        return webCapabilities;
    }

    public static void setWebCapabilities(HashMap<String, Object> webCap) {
        webCapabilities = new HashMap<>();
        webCapabilities = webCap;
    }

    public static String getLogPath() {
        return logPath;
    }

    public static String getLatestExecPath() {
        return latestExecPath;
    }

    public static String getAppiumDriverExecutable() {
        return appiumDriverExecutable;
    }

    public static String getAppiumJS() {
        return appiumJS;
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static String getDate() {
        String date = "";

        DateTime dateTime = new DateTime();
        date = date + dateTime.dayOfMonth().getAsString() + ".";
        date = date + dateTime.monthOfYear().getAsString() + ".";
        date = date + dateTime.year().getAsString() + "_";
        date = date + dateTime.hourOfDay().getAsString() + ":";
        date = date + dateTime.minuteOfHour().getAsString() + ":";
        date = date + dateTime.secondOfMinute().getAsString() + ".";
        date = date + dateTime.millisOfSecond().getAsString();

        return date;
    }

    public static String getAppiumServerAddress() {
        return appiumServerAddress;
    }

    public static String getAppiumServerPort() {
        return appiumServerPort;
    }

    public static String getOS() {
        return OS;
    }

    public static void setOS(String OS) {
        BaseUtil.OS = OS;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        BaseUtil.version = version;
    }

    public static String getMobileName() {
        return mobileName;
    }

    public static void setMobileName(String mobileName) {
        BaseUtil.mobileName = mobileName;
    }

    public static String getAppPackageName() {
        return appPackageName;
    }

    public static void setAppiumServerPort(String port) {
        appiumServerPort = port;
    }

    public static void setAppiumServerAddress(String url) {
        appiumServerAddress = url;
    }

    public static String getTestingDevice() {
        return testingDevice;
    }

    public static String getiOSBundleId() {
        return iOSBundleId;
    }

    public static String getTestLocation() {
        if (testLocation == null) {
            testLocation = "Berlin Germany";
        }
        return testLocation;
    }
}