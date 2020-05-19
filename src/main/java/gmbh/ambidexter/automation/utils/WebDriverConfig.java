package gmbh.ambidexter.automation.utils;

public class WebDriverConfig {
    private String browserName;
    private Boolean headless;
    private Boolean mobileEmulated;
    private String emulatedDevice;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Boolean getHeadless() {
        return headless;
    }

    public void setHeadless(Boolean headless) {
        this.headless = headless;
    }

    public Boolean getMobileEmulated() {
        return mobileEmulated;
    }

    public void setMobileEmulated(Boolean mobileEmulated) {
        this.mobileEmulated = mobileEmulated;
    }

    public String getEmulatedDevice() {
        return emulatedDevice;
    }

    public void setEmulatedDevice(String emulatedDevice) {
        this.emulatedDevice = emulatedDevice;
    }
}
