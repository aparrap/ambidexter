package gmbh.ambidexter.automation.managers;

import java.util.HashMap;

public class RestManager {

    private static Object handler;
    private static HashMap<String, Object> restProperties;
    private static Object otherInfo;

    public static HashMap<String, Object> getRestProperties() {
        return restProperties;
    }

    public static void setRestProperties(HashMap<String, Object> restProp) {
        restProperties = restProp;
    }

    public static Object getHandler() {
        return handler;
    }

    public static void setHandler(Object object) {
        handler = object;
    }

    public static Object getOtherInfo() {
        return otherInfo;
    }

    public static void setOtherInfo(Object object) {
        otherInfo = object;
    }
}
