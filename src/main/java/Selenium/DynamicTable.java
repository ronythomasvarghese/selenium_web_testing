package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.time.Duration;

import static java.lang.System.exit;

public class DynamicTable {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://drt.gov.in/");

        for (String windowHandle : driver.getWindowHandles()) {
            System.out.println(windowHandle);
        }
 //       driver.findElement(By.id("Name")).sendKeys("Rony thomas");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("li[class='nav-item'] a[type='button']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Registry Login']")).click();

        driver.findElement(By.name("username")).sendKeys("listern1");
        driver.findElement(By.name("password")).sendKeys("Listern1@123");



        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='Reports']"));
        action.moveToElement(ele).perform();

        driver.findElement(By.xpath("//a[normalize-space()='Case Wise Report']")).click();
        driver.findElement(By.name("from_date1")).click(); // Open the date picker
        String set_from_date = "January 2025";
        int day = 28;

        // Loop until the desired month is found
        while (true) {
            String from_date = driver.findElement(By.cssSelector("div[class='datepicker-days'] th[class='datepicker-switch']")).getText();
            if (set_from_date.equals(from_date)) {
                System.out.println("Month selected: " + from_date);
                break;  // Exit loop when the correct month is found
            } else {
                // Click the NEXT button instead of PREV if needed
                driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='prev'][normalize-space()='«']")).click();
            }
            Thread.sleep(500); // Short delay to allow UI update
        }

        // Select a specific day (e.g., 15th of the month)
        driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']")).click();
        Thread.sleep(1000);

//        WebElement date_pick = driver.findElement(By.xpath("input[@id='mydate1']"));
//        action.moveToElement(date_pick).perform();
//
        driver.findElement(By.id("mydate1")).click();
        String to_date = driver.findElement(By.cssSelector("div[class='datepicker-days'] th[class='datepicker-switch']")).getText();

        driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']")).click();

        Thread.sleep(2000);
        Select case_status = new Select(driver.findElement((By.id("in_pettype"))));
        case_status.selectByVisibleText("PENDING");
        driver.findElement(By.id("gobtn")).click();

        Thread.sleep(8000);


     //   System.out.println(from_date);
       // System.out.println(to_date);

        Thread.sleep(8000);
        String tot_nos =driver.findElement(By.xpath("//table//tbody//*[@color='red']")).getText();
        System.out.println(tot_nos);
       driver.close();

    }
}
