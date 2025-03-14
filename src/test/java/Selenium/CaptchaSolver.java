package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import net.sourceforge.tess4j.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CaptchaSolver {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cis.drt.gov.in/drtlive/login.php");
    }

    @Test(priority = 1)
    public void solveCaptcha() throws IOException, InterruptedException, TesseractException {

        //Enter the credentials
        driver.findElement(By.name("username")).sendKeys("listern1");
        driver.findElement(By.name("password")).sendKeys("Listern1@123");

        // Locate CAPTCHA image
        WebElement captchaImg = driver.findElement(By.cssSelector("img.imgcaptcha"));

        // Take screenshot of CAPTCHA
        File srcFile = captchaImg.getScreenshotAs(OutputType.FILE);
        File destFile = new File("captcha.png");
        FileHandler.copy(srcFile, destFile);
        System.out.println("CAPTCHA image saved at: " + destFile.getAbsolutePath());

        // Solve CAPTCHA using Tesseract OCR
        System.out.println("TESSDATA_PREFIX: " + System.getenv("TESSDATA_PREFIX"));
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Update this path
        tesseract.setLanguage("eng"); // Set language to English

        String extractedText = tesseract.doOCR(destFile).replaceAll("[^a-zA-Z0-9]", ""); // Remove unwanted characters
        System.out.println("Extracted CAPTCHA: " + extractedText);

        // Enter CAPTCHA in input field
        WebElement captchaInput = driver.findElement(By.name("answer"));
        captchaInput.sendKeys(extractedText);
        Thread.sleep(40);
        // Click Submit Button (Modify Locator as Needed)
        WebElement submitButton = driver.findElement(By.id("cryptstr")); // Update locator
        submitButton.click();

        // Wait for next page load
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void reportGeneration() throws InterruptedException {
        // Locate the "Reports" menu item
        WebElement reportsMenu = driver.findElement(By.xpath("//a[contains(text(), 'Reports')]"));
        // Create Actions class object
        Actions actions = new Actions(driver);
        // Move to "Reports" element
        actions.moveToElement(reportsMenu).perform();
        // Wait for the dropdown to appear (if applicable)
        Thread.sleep(1000);
        // Click on the "Reports" menu
        reportsMenu.click();
        driver.findElement(By.xpath("//a[contains(text(),'Case Wise Report')]")).click();
        driver.findElement(By.name("from_date1")).click();

    }

    @AfterTest
    public void tearDown() {

        //driver.quit();
    }
}
