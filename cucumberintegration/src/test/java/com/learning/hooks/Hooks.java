package com.learning.hooks;


import com.learning.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    private WebDriverContext context;

    public Hooks(WebDriverContext context) {
        this.context = context;
    }

    @After
    public void afterHook(final Scenario scenario) {
        // Add the logic to capture the screenshot
        var data = ScreenshotUtil.takeScreenshot(context.getDriver());
        scenario.attach(data, "image/png", scenario.getName());
        if (context.getDriver() != null) {
            context.getDriver().close();
            context.getDriver().quit();
        }
    }

}
