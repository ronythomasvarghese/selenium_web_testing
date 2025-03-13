package Selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MouseScroll {
    WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://drt.gov.in/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.scrollBy(0,document.body.scrollHeight)");

    }
}
