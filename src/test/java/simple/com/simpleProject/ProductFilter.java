package simple.com.simpleProject;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ProductFilter {
	
	@Test
	 public void ProductFilteringLowToHigh() {
    	WebDriver driver = new ChromeDriver();
    	 try{
	        
    		 driver.get("https://www.saucedemo.com/v1/");
	        
	        // Login
	        driver.findElement(By.id("user-name")).sendKeys("standard_user");
	        driver.findElement(By.id("password")).sendKeys("secret_sauce");
	        driver.findElement(By.id("login-button")).click();
	
	        // Apply Filter: Price Low to High
	        WebElement filter = driver.findElement(By.className("product_sort_container"));
	        
	        //filter.sendKeys("Price (low to high)");
	        Select price=new Select(filter);
	        price.selectByValue("lohi");
	
	        // Get and print product names
	        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
	        System.out.println("***** Products after sorting (Low to High) *****");
	        for (WebElement product : productNames) {
	            System.out.println("- "+product.getText());
	        }
	    } catch (Exception e) {
	        System.out.println("Something went wrong: " + e.getMessage());
	    } finally {
	        driver.quit();
	    }

        System.out.println("--------------------------------------------------");
    }
}
