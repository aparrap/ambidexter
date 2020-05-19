package gmbh.ambidexter.automation.pageobjects;

import gmbh.ambidexter.automation.pageobject.MobileBasePO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;

public class iOSDatePickerPO extends MobileBasePO {

    @iOSXCUITFindAll ({
        @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypePicker/XCUIElementTypePickerWheel[1]"),
        @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[1]")})
    private MobileElement dayPickerWheel;

    @iOSXCUITFindAll ({
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypePicker/XCUIElementTypePickerWheel[2]"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[2]")})
    private MobileElement hourPickerWheel;

    @iOSXCUITFindAll ({
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypePicker/XCUIElementTypePickerWheel[3]"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[3]")})
    private MobileElement minutePickerWheel;

    @iOSXCUITFindAll ({
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypePicker/XCUIElementTypePickerWheel[4]"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeApplication[@name=\"TestApplication\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[4]")})
    private MobileElement timePickerWheel;

    public iOSDatePickerPO(AppiumDriver<?> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement getDayPickerWheel() {
        return dayPickerWheel;
    }

    public MobileElement getHourPickerWheel() {
        return hourPickerWheel;
    }

    public MobileElement getMinutePickerWheel() {
        return minutePickerWheel;
    }

    public MobileElement getTimePickerWheel() {
        return timePickerWheel;
    }
}
