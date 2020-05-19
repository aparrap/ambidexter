package gmbh.ambidexter.automation.cucumber;

import gmbh.ambidexter.automation.managers.AppiumDriverManager;
import gmbh.ambidexter.automation.managers.PageObjectManager;

public class MobileContext {
    private PageObjectManager mPageObjectManager;
    private AppiumDriverManager mAppiumDriverManager;

    public MobileContext() {
        mAppiumDriverManager = new AppiumDriverManager();
        mPageObjectManager = new PageObjectManager(mAppiumDriverManager.getAppiumDriver());
    }

    public AppiumDriverManager getAppiumDriverManager() {
        return mAppiumDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return mPageObjectManager;
    }
}