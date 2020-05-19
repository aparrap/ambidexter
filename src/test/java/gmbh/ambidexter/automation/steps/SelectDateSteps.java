package gmbh.ambidexter.automation.steps;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.List;

import gmbh.ambidexter.automation.cucumber.MobileContext;
import gmbh.ambidexter.automation.pageobjects.AndroidPickerPO;
import gmbh.ambidexter.automation.utils.StringUtils;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectDateSteps {
    private MobileContext mMobileContext;
    private AndroidPickerPO mAndroidPickerPO;
    private WebDriverWait mWait;

    public SelectDateSteps(MobileContext context) {
        this.mMobileContext = context;
        mWait = new WebDriverWait(mMobileContext.getAppiumDriverManager().getAppiumDriver(), 20);
    }

    @Given("The user checks that the app is open on iOS")
    public void theUserChecksThatTheAppIsOpenOnIOS() {

    }

    @When("The user selects a date {string} on the iOS device")
    public void theUserSelectsADateOnTheIOSDevice(String date) {

    }

    @Then("The date is selected on the iOS device")
    public void theDateIsSelectedOnTheIOSDevice() {
    }

    @Given("The user checks that the app is open on Android")
    public void theUserChecksThatTheAppIsOpenOnAndroid() {
        mAndroidPickerPO = mMobileContext.getPageObjectManager().getAndroidPickerPO();
        mWait.until(ExpectedConditions.visibilityOf(mAndroidPickerPO.getMonthView()));
        assertThat(mAndroidPickerPO.getYearPicker().isDisplayed()).overridingErrorMessage("App is not loaded").isTrue();
    }

    @When("The user selects a date {string} on the Android device")
    public void theUserSelectsADateOnTheAndroidDevice(String date)
            throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        selectYear(simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getYear());
        selectMonth(simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getMonthValue());
        selectDate(date);
    }

    @Then("The date {string} is selected on the Android device")
    public void theDateIsSelectedOnTheAndroidDevice(String date)
            throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dayOfTheWeek = simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getDayOfWeek().name();
        String monthOfTheYear = simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getMonth().name();
        int dayOfTheMonth = simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getDayOfMonth();
        int year = simpleDateFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).getYear();

        String selectedDate = mAndroidPickerPO.getSelectedDate().getText();
        String[] splittedDate = StringUtils.splitString(selectedDate, ",");
        String[] splittedMonthAndDay = StringUtils.splitString(splittedDate[1], " ");

        //Assertions to validate that the selected date is the one that we looked for
        assertThat(dayOfTheWeek).containsIgnoringCase(splittedDate[0]);
        String androidVersion = mMobileContext.getAppiumDriverManager().getAppiumDriver().getCapabilities().getCapability("platformVersion").toString();

        //Validation made accordingly to Android Version
        if (androidVersion.equalsIgnoreCase("10")) {
            assertThat(monthOfTheYear).containsIgnoringCase(splittedMonthAndDay[2]);
            assertThat(dayOfTheMonth).isEqualTo(Integer.parseInt(splittedMonthAndDay[1]));
        }else {
            assertThat(monthOfTheYear).containsIgnoringCase(splittedMonthAndDay[1]);
            assertThat(dayOfTheMonth).isEqualTo(Integer.parseInt(splittedMonthAndDay[2]));
        }
        assertThat(year).isEqualTo(Integer.parseInt(mAndroidPickerPO.getYearPicker().getText()));
        mMobileContext.getAppiumDriverManager().getAppiumDriver().quit();
    }

//Method to select the corresponding day inside the Month View
    public void selectDate(String date) {
        String formattedDate = null;
        mWait.until(ExpectedConditions.visibilityOf(mAndroidPickerPO.getMonthView()));
        List<MobileElement> listOfDays = mAndroidPickerPO.getMonthView().findElementsByClassName("android.view.View");
        for (MobileElement day : listOfDays) {
            formattedDate = day.getTagName();
            if (formattedDate.startsWith("0")) {
                formattedDate = day.getTagName().substring(1);
            }
            if (formattedDate.equalsIgnoreCase(date)) {
                day.click();
                break;
            }
        }
    }

    //Validates the first entry on the Month view and compares to the month to be searched, the clicks to corresponding month
    public void selectMonth(int month)
            throws ParseException {
        mWait.until(ExpectedConditions.visibilityOf(mAndroidPickerPO.getMonthView()));

        List<MobileElement> listOfDays = mAndroidPickerPO.getMonthView().findElementsByClassName("android.view.View");
        String initialDateOnList = listOfDays.get(0).getTagName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        int monthOnScreen = simpleDateFormat.parse(initialDateOnList).toInstant().atZone(ZoneId.systemDefault()).getMonthValue();

        if (monthOnScreen < month) {
            while (monthOnScreen < month) {
                mAndroidPickerPO.clickOnNextMonth();
                listOfDays = mAndroidPickerPO.getMonthView().findElementsByClassName("android.view.View");
                initialDateOnList = listOfDays.get(0).getTagName();
                monthOnScreen = simpleDateFormat.parse(initialDateOnList).toInstant().atZone(ZoneId.systemDefault()).getMonthValue();
            }
        } else if (monthOnScreen > month) {
            while (monthOnScreen > month) {
                mAndroidPickerPO.clickOnPreviousMonth();
                listOfDays = mAndroidPickerPO.getMonthView().findElementsByClassName("android.view.View");
                initialDateOnList = listOfDays.get(0).getTagName();
                monthOnScreen = simpleDateFormat.parse(initialDateOnList).toInstant().atZone(ZoneId.systemDefault()).getMonthValue();
            }
        }
    }

    //Validates the clicks on the year picker and select the corresponding year, if year is not on the scroll view, performs a swipe action to look for the year.
    private void selectYear(int year) {
        mAndroidPickerPO.clickOnYearPicker();
        mWait.until(ExpectedConditions.visibilityOf(mAndroidPickerPO.getYearWheel()));
        List<MobileElement> yearSelection = mAndroidPickerPO.getYearWheel().findElementsById("android:id/text1");
        int initYear = Integer.parseInt(yearSelection.get(0).getText());
        int endYear = Integer.parseInt(yearSelection.get(yearSelection.size() - 2).getText());
        if (year <= initYear) {
            while (initYear > year) {
                mAndroidPickerPO.fullSwipe("up");
                yearSelection = mAndroidPickerPO.getYearWheel().findElementsById("android:id/text1");
                initYear = Integer.parseInt(yearSelection.get(0).getText());
            }
        } else if (year >= endYear) {
            while (endYear < year) {
                mAndroidPickerPO.fullSwipe("down");
                yearSelection = mAndroidPickerPO.getYearWheel().findElementsById("android:id/text1");
                endYear = Integer.parseInt(yearSelection.get(yearSelection.size() - 2).getText());
            }
        }
        for (MobileElement yearD : yearSelection) {
            if (Integer.parseInt(yearD.getText()) == year) {
                yearD.click();
                break;
            }
        }
    }
}
