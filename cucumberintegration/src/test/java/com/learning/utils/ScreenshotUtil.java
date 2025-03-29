package com.learning.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static byte[] takeScreenshot(WebDriver driver) {
        // Convert WebDriver object to TakeScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Call getScreenshotAs method to create image file and return as byte array
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }

}
