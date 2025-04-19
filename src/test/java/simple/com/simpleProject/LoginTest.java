package simple.com.simpleProject;

import simple.com.simpleProject.utils.ExcelUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class LoginTest {

	 public WebDriver driver;
    
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        // Reading data from Excel file
        return ExcelUtils.getExcelData("testdata/credentials.xlsx", "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        
        driver = new ChromeDriver();

        // Navigate to Sauce Demo Login Page
        driver.get("https://www.saucedemo.com/v1/");

        // Locate the username and password fields, and the login button
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Perform login using data from Excel
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Check if login is successful by looking for the logo (this is an example)
        try {
            WebElement logo = driver.findElement(By.className("app_logo"));
            if (logo.isDisplayed()) {
                System.out.println("Login successful for user: " + username);
            }
        } catch (Exception e) {
            System.out.println("Login failed for user: " + username);
            takeScreenshot(username); // Take screenshot if login fails
        }

        // Close the browser
        driver.quit();
    }

    // Method to take a screenshot
    public void takeScreenshot(String username) {
        // Create a file reference to store the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        // Define the location to save the screenshot
        File destFile = new File("screenshots/" + username + "_login_failed.png");
        
        try {
            // Create the screenshots directory if it doesn't exist
            File parentDir = destFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();  // Create necessary directories
            }
            
            // Copy the screenshot to the specified location
            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
