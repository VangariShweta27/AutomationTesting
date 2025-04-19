package simple.com.simpleProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(simple.com.simpleProject.utils.TestListener.class)
public class CheckoutTest {

    public WebDriver driver;
    @Test
    public void testCheckoutProcess() {
    	driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().window().maximize();

        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));

            // Step 1: Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Step 2: Apply filter - Price (low to high)
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
            Select select = new Select(dropdown);
            select.selectByValue("lohi");

            // Step 3: Select first product and add to cart
            List<WebElement> productNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
            List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));

            if (!productNames.isEmpty() && !addToCartButtons.isEmpty()) {
                String productName = productNames.get(0).getText();
                addToCartButtons.get(0).click();

                WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
                System.out.println("Product added to cart and present in checkout: " + productName);
                System.out.println("Cart badge count: " + cartBadge.getText());
                System.out.println("Product successfully added to cart!");
            } else {
                System.out.println("Cart is empty – product not added.");
            }

            // Step 4: Go to cart
            driver.findElement(By.className("shopping_cart_link")).click();

            // Step 5: Click on checkout button
            wait.until(ExpectedConditions.elementToBeClickable(By.className("checkout_button"))).click();

            // Step 6: Fill in checkout form
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Doe");
            driver.findElement(By.id("postal-code")).sendKeys("123456");
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Step 7: Click on Finish
            wait.until(ExpectedConditions.elementToBeClickable(By.className("cart_button"))).click();

            // Step 8: Verify order success
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
            if (confirmation.getText().contains("THANK YOU FOR YOUR ORDER")) {
              System.out.println("Order placed successfully!");
            } else {
              System.out.println("Order not completed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Checkout test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
        System.out.println("--------------------------------------------------");
    }
}
