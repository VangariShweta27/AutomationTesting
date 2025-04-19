package simple.com.simpleProject.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import simple.com.simpleProject.CheckoutTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Name: " + result.getName() + " – STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Execution Status " + result.getName() + " – PASSED");
        System.out.println("*********************************************");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Execution Status " + result.getName() + " – FAILED");
        System.out.println("*********************************************");
        Object testClass = result.getInstance();
        WebDriver driver = ((CheckoutTest) testClass).driver;

        // Capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = "screenshots/" + result.getName() + "_" + timestamp + ".png";

        try {
            FileHandler.copy(src, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Error while saving screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Name: " + result.getName() + " – SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}
