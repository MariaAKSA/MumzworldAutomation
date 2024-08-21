package mumzworld;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class mumzworldTesting {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and WebDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Maximize browser window and open the website
            driver.manage().window().maximize();
            driver.get("https://www.mumzworld.com");

            // Search for a product
            WebElement searchBox = driver.findElement(By.xpath("//*[@id='root']/main/header/div[1]/div[2]/div/div[1]/form/input"));
            searchBox.sendKeys("puzzle toy");
            searchBox.submit();

            // Wait for and select the 'Shop All' option
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.algoliaHits-link-2iU[title='Shop All']")));
            WebElement shopAll = driver.findElement(By.xpath("//*[@title='Shop All']"));
            shopAll.click();

            // Select the first product from the list
            WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".algoliaSearchContent-items-3GO")));
            WebElement firstProduct = productList.findElement(By.cssSelector(".item-root-1iS a.item-images-3BW"));
            firstProduct.click();

            // Add product to the shopping bag
            WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Add to bag']")));
            cartIcon.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".feedbackPopup-root-1VT")));
            
            // View the cart
            WebElement viewCart = driver.findElement(By.xpath("//*[@title='View Bag']"));
            viewCart.click();

            // Increase quantity of the item to 5
            WebElement quantityRoot = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-quantityRoot-1pT")));
            WebElement increaseButton = quantityRoot.findElement(By.cssSelector("button.quantity-button_increment-pgq"));
            for (int i = 1; i < 5; i++) {
                try {
                    increaseButton.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", increaseButton);
                }
                Thread.sleep(500);
            }

            // Proceed to checkout
            WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.proceedToCheckoutBtn-button-3S2.button-root_highPriority-2iR.button-root-dxQ.clickable-root-orq")));
            proceedToCheckoutButton.click();

            // Sign up for a new account
            WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.signLink-link-2ye")));
            signUpButton.click();

            // Fill in registration details
            WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#create_account_page_firstname")));
            firstNameInput.sendKeys("John");
            
            WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#create_account_page_lastname")));
            lastNameInput.sendKeys("Doe");
            
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#create_account_page_email")));
            String email = "john.doe@example.com";
            emailInput.sendKeys(email);
            
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_page_password")));
            String password = "Password@123";
            passwordField.sendKeys(password);
            
            // Ensure the terms and conditions checkbox is checked
            WebElement termsCheckbox = driver.findElement(By.id("terms_and_conditions"));
            if (!termsCheckbox.isSelected()) {
                termsCheckbox.click();
            }
            
            // Submit the registration form
            WebElement registerButton = driver.findElement(By.cssSelector("button.createAccount-submitButton-1yp"));
            registerButton.click();

            // Output success message
            System.out.println("Test completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
