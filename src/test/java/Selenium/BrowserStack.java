package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserStack {

    WebDriver driver = new ChromeDriver();



    @Test(priority = 1, description = "Verify BrowserStack page title", groups = { "smoke" , "sanity"})

    public void verifyBSTitle() {

        driver.get("https://www.browserstack.com/");

        Assert.assertEquals(driver.getTitle(), "Most Reliable App & Cross Browser Testing Platform | BrowserStack");

    }



    @Test(priority = 2, description = "Verify Google page title", groups = { "smoke" })

    public void verifyGoogleTitle() {

        driver.get("https://www.google.com/");

        Assert.assertEquals(driver.getTitle(), "Google");

    }



    @Test(priority = 3, description = "Verify Get Started for Free click", groups = { "smoke", "regression" })

    public void clickGetStartedForFree() {

        driver.get("https://www.browserstack.com/");

        driver.findElement(By.cssSelector("a#signupModalProductButton")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("users/sign_up"));

    }



    @Test(priority = 4,invocationCount = 5, description = "Verify Pricing click", groups = { "sanity" })

    public void clickPricing() {

        driver.get("https://www.browserstack.com/");

        driver.findElement(By.cssSelector("a[title='Pricing']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("pricing"));

    }

}