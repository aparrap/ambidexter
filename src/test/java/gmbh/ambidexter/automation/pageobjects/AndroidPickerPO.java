package gmbh.ambidexter.automation.pageobjects;

import gmbh.ambidexter.automation.pageobject.MobileBasePO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidPickerPO
        extends MobileBasePO {

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    private MobileElement yearPicker;

    @AndroidFindBy(id = "android:id/date_picker_year_picker")
    private MobileElement yearWheel;

    @AndroidFindBy(id = "android:id/prev")
    private MobileElement btnPreviousMonth;

    @AndroidFindBy(id = "android:id/next")
    private MobileElement btnNextMonth;

    @AndroidFindBy(id = "android:id/month_view")
    private MobileElement monthView;

    @AndroidFindBy(id = "android:id/date_picker_header_date")
    private MobileElement selectedDate;

    public AndroidPickerPO(AppiumDriver<?> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement getYearPicker() {
        return yearPicker;
    }

    public MobileElement getYearWheel() {
        return yearWheel;
    }

    public MobileElement getBtnPreviousMonth() {
        return btnPreviousMonth;
    }

    public MobileElement getBtnNextMonth() {
        return btnNextMonth;
    }

    public MobileElement getMonthView() {
        return monthView;
    }

    public MobileElement getSelectedDate() {
        return selectedDate;
    }

    public void clickOnPreviousMonth() {
        getBtnPreviousMonth().click();
    }

    public void clickOnNextMonth() {
        getBtnNextMonth().click();
    }

    public void clickOnYearPicker() {
        getYearPicker().click();
    }
}
