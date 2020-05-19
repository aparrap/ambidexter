package gmbh.ambidexter.automation.managers;

import gmbh.ambidexter.automation.pageobjects.AndroidPickerPO;
import gmbh.ambidexter.automation.pageobjects.iOSDatePickerPO;
import io.appium.java_client.AppiumDriver;

public class PageObjectManager {

    private AppiumDriver<?> mAppiumDriver;
    private AndroidPickerPO mAndroidPickerPO;
    private iOSDatePickerPO mIOSDatePickerPO;

    public PageObjectManager(AppiumDriver<?> appiumDriver) {
        this.mAppiumDriver = appiumDriver;
    }

    public AndroidPickerPO getAndroidPickerPO() {
        return (mAndroidPickerPO == null) ? mAndroidPickerPO = new AndroidPickerPO(mAppiumDriver) : mAndroidPickerPO;
    }

    public iOSDatePickerPO getIOSDatePickerPO() {
        return (mIOSDatePickerPO == null) ? mIOSDatePickerPO = new iOSDatePickerPO(mAppiumDriver) : mIOSDatePickerPO;
    }
}
