package gmbh.ambidexter.automation.utils;

import java.util.Random;

public class StringUtils {

    private static String email;

    public static String getEmail() {
        return email;
    }

    public static String[] splitString(String word, String token) {
        return word.split(token);
    }
}