package mumzworld;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class mumzworldTestcases {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        Reporter.log("Browser setup completed and maximized.");
    }

    @Test
    public void testOpenWebstore() {
        driver.get("https://www.mumzworld.com");
        Assert.assertTrue(driver.getTitle().contains("#1 Mother, Child & Baby Shop in the UAE - Mumzworld"), "Failed to open Mumzworld webstore.");
        Reporter.log("Opened the Mumzworld webstore.");
    }

    @Test(dependsOnMethods = {"testOpenWebstore"})
    public void testSearchProduct() {
        WebElement searchBox = driver.findElement(By.xpath("//*[@id='root']/main/header/div[1]/div[2]/div/div[1]/form/input"));
        searchBox.sendKeys("puzzle toy");
        searchBox.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.algoliaHits-link-2iU[title='Shop All']")));
        Reporter.log("Searched for the product 'puzzle toy'.");
    }

    @Test(dependsOnMethods = {"testSearchProduct"})
    public void testAddToBag() {
        WebElement shopAll = driver.findElement(By.xpath("//*[@title='Shop All']"));
        shopAll.click();
        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".algoliaSearchContent-items-3GO")));
        WebElement firstProduct = productList.findElement(By.cssSelector(".item-root-1iS a.item-images-3BW"));
        firstProduct.click();

        WebElement addToBagButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".productFullDetail-addToCart-2cW")));
        addToBagButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".feedbackPopup-root-1VT")));
        Reporter.log("Added the product to the bag.");
    }

    @Test(dependsOnMethods = {"testAddToBag"})
    public void testGoToViewBag() {
        WebElement viewCart = driver.findElement(By.xpath("//*[@title='View Bag']"));
        viewCart.click();
        Reporter.log("Navigated to the view bag page.");
    }

    @Test(dependsOnMethods = {"testGoToViewBag"})
    public void testIncreaseQuantity() {
        WebElement quantityRoot = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-quantityRoot-1pT")));
        WebElement increaseButton = quantityRoot.findElement(By.cssSelector("button.quantity-button_increment-pgq"));
        for (int i = 1; i < 5; i++) {
            try {
                increaseButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", increaseButton);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Reporter.log("Increased the quantity to 5 items.");
    }

    @Test(dependsOnMethods = {"testIncreaseQuantity"})
    public void testProceedToCheckout() {
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.proceedToCheckoutBtn-button-3S2.button-root_highPriority-2iR.button-root-dxQ.clickable-root-orq")));
        proceedToCheckoutButton.click();
        Reporter.log("Clicked the Proceed to Checkout button.");
    }

    @Test(dependsOnMethods = {"testProceedToCheckout"})
    public void testRegisterUser() {
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.signLink-link-2ye")));
        signUpButton.click();
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
        WebElement termsCheckbox = driver.findElement(By.id("terms_and_conditions"));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
        WebElement registerButton = driver.findElement(By.cssSelector("button.createAccount-submitButton-1yp"));
        registerButton.click();
        Reporter.log("Registered a new user.");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
        Reporter.log("Browser closed.");
    }
}
