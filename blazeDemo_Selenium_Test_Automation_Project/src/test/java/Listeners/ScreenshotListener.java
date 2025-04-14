package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

import base.BaseTest;

public class ScreenshotListener implements ITestListener{
	
    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        String testName = result.getName();

        ScreenshotUtil.takeScreenshot(driver, testName);
    }

}
