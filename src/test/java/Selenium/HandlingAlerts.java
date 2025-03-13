package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandlingAlerts {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        //To click intercepted Button
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement ele =  driver.findElement(By.xpath("//button[@id = 'timerAlertButton']"));
//        js.executeScript("arguments[0].click();",ele);
//
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        System.out.println(alert.getText());
//        alert.accept();
//        Thread.sleep(1000);
//
//
//        //Click on third button
//
//        WebElement ele3 =  driver.findElement(By.xpath("//button[@id = 'confirmButton']"));
//        js.executeScript("arguments[0].click();",ele3);
//        Thread.sleep(1000);
//        WebDriverWait waitt = new WebDriverWait(driver,Duration.ofSeconds(6));
//        Alert alerts = waitt.until(ExpectedConditions.alertIsPresent());
//        System.out.println(alerts.getText());
//        driver.quit();


        //promtButton
        //Clicking the 4th element
        Thread.sleep(1000);
        WebElement ele4 =  driver.findElement(By.xpath("//button[@id = 'promtButton']"));
        js.executeScript("arguments[0].click();",ele4);
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
       // System.out.println(alertsss.getText());
       Alert alerta = driver.switchTo().alert();

        alerta.sendKeys("RONY THOMAS VARGHESE");
        driver.switchTo().alert().sendKeys("RONY THOMAS VARGHESE");

        Actions act = new Actions(driver);

        Thread.sleep(3000);
        driver.quit();





    }
}
