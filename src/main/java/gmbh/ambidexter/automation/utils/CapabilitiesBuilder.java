package gmbh.ambidexter.automation.utils;

import gmbh.ambidexter.automation.base.BaseUtil;
import gmbh.ambidexter.automation.managers.RestManager;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CapabilitiesBuilder {
    private static String URL;
    private static String PORT;
    private static DesiredCapabilities desiredCapabilities;
    private static HashMap<String, Object> webDriverProperties;
    private static HashMap<String, Object> apiProperties;
    private static final Logger LOGGER = LoggerFactory.getLogger(CapabilitiesBuilder.class);

    public static DesiredCapabilities processMobileCapabilities(Field[] capabilities, AppiumCapabilities appiumCapabilities) {
        desiredCapabilities = new DesiredCapabilities();
        for (Field capability : capabilities) {
            try {
                if (capability.getType().getName().contains("String")) {
                    if (capability.getName().equals("url")) {
                        URL = appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString();
                        BaseUtil.setAppiumServerAddress(URL);
                    } else if (capability.getName().equals("port")) {
                        PORT = appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString();
                        BaseUtil.setAppiumServerPort(PORT);
                    } else if (capability.getName().equals("app")) {
                        String path = System.getProperty("user.dir") + appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString();
                        desiredCapabilities.setCapability(capability.getName(), path);
                    } else {
                        desiredCapabilities.setCapability(capability.getName(), appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString());
                    }
                } else if (capability.getType().getName().contains("Boolean")) {
                    desiredCapabilities.setCapability(capability.getName(), Boolean.parseBoolean(appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString()));
                } else if (capability.getType().getName().contains("Int")) {
                    if (Integer.parseInt(appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString()) != 0) {
                        desiredCapabilities.setCapability(capability.getName(), Integer.parseInt(appiumCapabilities.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(appiumCapabilities).toString()));
                    }
                }
            } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.info("Capability is marked as null: " + e.getMessage());
            }
        }
        return desiredCapabilities;
    }

    public static void processWebCapabilities(Field[] capabilities, WebDriverConfig webDriverConfig) {
        webDriverProperties = new HashMap<>();
        for (Field capability : capabilities) {
            try {
                if (capability.getType().getName().contains("String")) {
                    webDriverProperties.put(capability.getName(), webDriverConfig.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(webDriverConfig).toString());
                } else if (capability.getType().getName().contains("Boolean")) {
                    webDriverProperties.put(capability.getName(), Boolean.parseBoolean(webDriverConfig.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(webDriverConfig).toString()));
                } else if (capability.getType().getName().contains("Int")) {
                    if (Integer.parseInt(webDriverConfig.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(webDriverConfig).toString()) != 0) {
                        webDriverProperties.put(capability.getName(), Integer.parseInt(webDriverConfig.getClass().getMethod("get" + capability.getName().substring(0, 1).toUpperCase() + capability.getName().substring(1)).invoke(webDriverConfig).toString()));
                    }
                }
            } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.info("Capability is marked as null: " + e.getMessage());
            }
        }
        BaseUtil.setWebCapabilities(webDriverProperties);
    }

    public static void processAPICapabilities(Field[] properties, RestHandlerConfig restHandlerConfig) {
        apiProperties = new HashMap<>();
        for (Field property : properties) {
            try {
                if (property.getType().getName().contains("String")) {
                    apiProperties.put(property.getName(), restHandlerConfig.getClass().getMethod("get" + property.getName().substring(0, 1).toUpperCase() + property.getName().substring(1)).invoke(restHandlerConfig).toString());
                }
            } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.info("Property is marked as null: " + e.getMessage());
            }
        }
        RestManager.setRestProperties(apiProperties);
    }
}