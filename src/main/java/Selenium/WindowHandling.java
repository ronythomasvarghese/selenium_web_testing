package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class WindowHandling {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://drt.gov.in/");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotSource = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("C:/Users/acer/Desktop/SCRNSHOT.png");

        try {
            FileHandler.copy(screenshotSource, dest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("li[class='nav-item'] a[type='button']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Registry Login']")).click();

        driver.findElement(By.name("username")).sendKeys("listern1");
        driver.findElement(By.name("password")).sendKeys("Listern1@123");

        Thread.sleep(8000);
        driver.findElement(By.linkText("Logout")).click();
        
        Thread.sleep(3000);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://cis.drt.gov.in/drtlive/index.php");
        //driver.close();
    }
}
