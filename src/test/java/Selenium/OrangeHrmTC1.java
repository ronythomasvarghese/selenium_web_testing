package Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;

public class OrangeHrmTC1 {

    WebDriver driver = new ChromeDriver();
    @Test(priority = 0)
//    @Parameters({"username", "password"})
    void setupOrangeHrm() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        String urlname = driver.getTitle();
        Assert.assertEquals(urlname,"OrangeHRM");
        Thread.sleep(1000);
    }

    @Test(priority = 1,dataProvider = "dp",dataProviderClass = DataproviderClass.class)
    void Login(String username, String password) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 2,dependsOnMethods ={"setupOrangeHrm","Login"})
    void verifyLogin() throws InterruptedException {
        Boolean status  = driver.findElement(By.xpath("//img[@alt='profile picture']")).isDisplayed();
        System.out.println(status);
    }

    @Test(priority = 3)
    void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();
    }

    @Test(priority = 4)
    void finalSetup(){

        driver.quit();
    }
}
