package gmbh.ambidexter.automation.utils;

import com.google.gson.annotations.Expose;

public class AppiumCapabilities {
    @Expose(serialize = false, deserialize = false)
    private String url;

    @Expose(serialize = false, deserialize = false)
    private String port;

    private String automationName;
    private String platformName;
    private Boolean nativeWebTap;
    private String platformVersion;
    private String deviceName;
    private Integer newCommandTimeout;
    private Boolean noReset;
    private String appActivity;
    private Boolean noSign;
    private String avd;
    private Boolean useNewWDA;
    private Integer systemPort;
    private Integer wdaLocalPort;
    private Boolean fullReset;
    private String udid;
    private String xcodeOrgId;
    private String xcodeSigningId;
    private String locale;
    private String language;
    private Boolean useKeystore;
    private String keystorePath;
    private String keystorePassword;
    private String keyAlias;
    private String keyPassword;
    private Boolean keepKeyChains;
    private String app;
    private String derivedDataPath;
    private Boolean autoGrantPermissions;
    private Boolean autoLaunch;
    private Integer remoteAppsCacheLimit;

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Boolean getNativeWebTap() {
        return nativeWebTap;
    }

    public void setNativeWebTap(Boolean nativeWebTap) {
        this.nativeWebTap = nativeWebTap;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public void setNewCommandTimeout(int newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    public Boolean getNoReset() {
        return noReset;
    }

    public void setNoReset(Boolean noReset) {
        this.noReset = noReset;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public Boolean getNoSign() {
        return noSign;
    }

    public void setNoSign(Boolean noSign) {
        this.noSign = noSign;
    }

    public String getAvd() {
        return avd;
    }

    public void setAvd(String avd) {
        this.avd = avd;
    }

    public Boolean getUseNewWDA() {
        return useNewWDA;
    }

    public void setUseNewWDA(Boolean useNewWDA) {
        this.useNewWDA = useNewWDA;
    }

    public Integer getSystemPort() {
        return systemPort;
    }

    public void setSystemPort(Integer systemPort) {
        this.systemPort = systemPort;
    }

    public Integer getWdaLocalPort() {
        return wdaLocalPort;
    }

    public void setWdaLocalPort(Integer wdaLocalPort) {
        this.wdaLocalPort = wdaLocalPort;
    }

    public Boolean getFullReset() {
        return fullReset;
    }

    public void setFullReset(Boolean fullReset) {
        this.fullReset = fullReset;
    }

    public String getXcodeOrgId() {
        return xcodeOrgId;
    }

    public void setXcodeOrgId(String xcodeOrgId) {
        this.xcodeOrgId = xcodeOrgId;
    }

    public String getXcodeSigningId() {
        return xcodeSigningId;
    }

    public void setXcodeSigningId(String xcodeSigningId) {
        this.xcodeSigningId = xcodeSigningId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getUseKeystore() {
        return useKeystore;
    }

    public void setUseKeystore(Boolean useKeystore) {
        this.useKeystore = useKeystore;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public Boolean getKeepKeyChains() {
        return keepKeyChains;
    }

    public void setKeepKeyChains(Boolean keepKeyChains) {
        this.keepKeyChains = keepKeyChains;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public void setDerivedDataPath(String derivedDataPath) {
        this.derivedDataPath = derivedDataPath;
    }

    public String getDerivedDataPath() {
        return derivedDataPath;
    }

    public Boolean getAutoGrantPermissions() {
        return autoGrantPermissions;
    }

    public void setAutoGrantPermissions(Boolean autoGrantPermissions) {
        this.autoGrantPermissions = autoGrantPermissions;
    }

    public void setNewCommandTimeout(Integer newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    public Boolean getAutoLaunch() {
        return autoLaunch;
    }

    public void setAutoLaunch(Boolean autoLaunch) {
        this.autoLaunch = autoLaunch;
    }

    public Integer getRemoteAppsCacheLimit() {
        return remoteAppsCacheLimit;
    }

    public void setRemoteAppsCacheLimit(Integer remoteAppsCacheLimit) {
        this.remoteAppsCacheLimit = remoteAppsCacheLimit;
    }
}