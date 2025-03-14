package DrtAutomation;
import java.time.Duration;
import java.time.YearMonth;

import DrtAutomation.utilities.ConfigReader;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CaptchaSolver {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        String url = ConfigReader.getProperty("url");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
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
        String tessdataPath = ConfigReader.getProperty("tessdata_path");
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessdataPath); // Update this path
        tesseract.setLanguage("eng"); // Set language to English

        String extractedText = tesseract.doOCR(destFile).replaceAll("[^a-zA-Z0-9]", ""); // Remove unwanted characters
        System.out.println("Extracted CAPTCHA: " + extractedText);

        // Enter CAPTCHA in input field
        WebElement captchaInput = driver.findElement(By.name("answer"));
        captchaInput.sendKeys(extractedText);
        Thread.sleep(2000);
        // Click Submit Button (Modify Locator as Needed)
        WebElement submitButton = driver.findElement(By.id("cryptstr")); // Update locator
        submitButton.click();

        // Wait for next page load
        Thread.sleep(1000);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement errorMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='text-danger']")));

            boolean loginError = errorMsg.isDisplayed();
            Assert.assertFalse(loginError, "CAPTCHA MISMATCH: LOGIN FAILED");

        } catch (TimeoutException e) {
            System.out.println("No CAPTCHA error message found. Login successful.");
        }
    }

    @Test(priority = 2,dependsOnMethods = {"solveCaptcha"})
    public void reportGeneration() throws InterruptedException {
        // Locate the "Reports" menu item
        WebElement reportsMenu = driver.findElement(By.xpath("//a[contains(text(), 'Reports')]"));
        // Create Actions class object
        Actions actions = new Actions(driver);
        // Move to "Reports" element
        actions.moveToElement(reportsMenu).perform();
        // Wait for the dropdown to appear (if applicable)
        Thread.sleep(500);
        // Click on the "Reports" menu
        reportsMenu.click();
        driver.findElement(By.xpath("//a[contains(text(),'Case Wise Report')]")).click();


    }
    @Test(priority = 3,dependsOnMethods = {"reportGeneration"})
    public void fromdateClick() throws InterruptedException {
        driver.findElement(By.name("from_date1")).click();
        String fromDate = ConfigReader.getProperty("fromDate");
        String toDate = ConfigReader.getProperty("toDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate targetDate = LocalDate.parse(fromDate, formatter);
        WebElement fromdateButton = driver.findElement(By.cssSelector("div[class='datepicker-days'] th[class='datepicker-switch']"));

        while (true) {

            String displayedMonthYear = fromdateButton.getText();
            System.out.println("Displayed Month & Year: " + displayedMonthYear);
            // Use YearMonth instead of LocalDate
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMMM yyyy");
            YearMonth displayedYearMonth = YearMonth.parse(displayedMonthYear, formatter1);
            System.out.println("Parsed Date: " + displayedYearMonth);

            if (displayedYearMonth.getYear() == targetDate.getYear() && displayedYearMonth.getMonth() == targetDate.getMonth()) {
                break;
            }

            // If the target date is in the future, click Next (»), else click Previous («)
            if (displayedYearMonth.isBefore(YearMonth.from(targetDate))) {
                driver.findElement(By.className("next")).click();
            } else {
                driver.findElement(By.className("prev")).click();
            }
            Thread.sleep(200); // Wait for animation
        }
        // Select the target day
        List<WebElement> allDates = driver.findElements(By.xpath("//td[@class='day' or contains(@class,'today')]"));
        for (WebElement date : allDates) {
            if (date.getText().equals(String.valueOf(targetDate.getDayOfMonth()))) {
                date.click();
                break;
            }
        }
    }
    @Test(priority = 4,dependsOnMethods = {"fromdateClick"})
    public void todateClick() throws InterruptedException {
        driver.findElement(By.name("to_date1")).click();

        String toDate = ConfigReader.getProperty("toDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate targetDate = LocalDate.parse(toDate, formatter);
        WebElement fromdateButton = driver.findElement(By.cssSelector("div[class='datepicker-days'] th[class='datepicker-switch']"));

        while (true) {

            String displayedMonthYear = fromdateButton.getText();
            System.out.println("Displayed Month & Year: " + displayedMonthYear);
            // Use YearMonth instead of LocalDate
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMMM yyyy");
            YearMonth displayedYearMonth = YearMonth.parse(displayedMonthYear, formatter1);
            System.out.println("Parsed Date: " + displayedYearMonth);

            if (displayedYearMonth.getYear() == targetDate.getYear() && displayedYearMonth.getMonth() == targetDate.getMonth()) {
                break;
            }

            // If the target date is in the future, click Next (»), else click Previous («)
            if (displayedYearMonth.isBefore(YearMonth.from(targetDate))) {
                driver.findElement(By.className("next")).click();
            } else {
                driver.findElement(By.className("prev")).click();
            }
            Thread.sleep(200); // Wait for animation
        }
        // Select the target day
        List<WebElement> allDates = driver.findElements(By.xpath("//td[@class='day' or contains(@class,'today')]"));
        for (WebElement date : allDates) {
            if (date.getText().equals(String.valueOf(targetDate.getDayOfMonth()))) {
                date.click();
                break;
            }
        }
    }
    @Test(priority = 5)
    public void checkPendingReport() throws InterruptedException {
        Select objSelect =new Select(driver.findElement(By.id("in_pettype")));
        objSelect.selectByVisibleText("PENDING");
        driver.findElement(By.id("gobtn")).click();
        Thread.sleep(5000);
    }
    @AfterTest
    public void tearDown() throws InterruptedException {
        Actions actions = new Actions(driver);

        for (int i = 0; i < 10; i++) {  // Adjust loop count for page length
            actions.scrollByAmount(0, 2000).perform();  // Scroll down 200 pixels
            Thread.sleep(500); // Wait for smooth effect
        }
        driver.quit();
    }
}
