package gmbh.ambidexter.automation.cucumber;

import gmbh.ambidexter.automation.managers.SeleniumDriverManager;
import gmbh.ambidexter.automation.managers.WebPageManager;

public class WebContext {
    private WebPageManager mWebPageManager;
    private SeleniumDriverManager mSeleniumDriverManager;

    public WebContext() {
        mSeleniumDriverManager = new SeleniumDriverManager();
        mWebPageManager = new WebPageManager(mSeleniumDriverManager.getWebDriver());
    }

    public WebPageManager getWebPageManager() {
        return mWebPageManager;
    }

    public SeleniumDriverManager getSeleniumDriverManager() {
        return mSeleniumDriverManager;
    }
}