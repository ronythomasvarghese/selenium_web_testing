package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumBrowserMethods {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver=new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //img[@alt='swatch-bharat-logo']
        driver.manage().window().maximize();
        driver.navigate().to("https://pmopg.gov.in/CitizenReforms/Registration/Index");
//
//        driver.findElement(By.cssSelector("li:nth-child(11) a:nth-child(1)")).click();
//        driver.findElement(By.cssSelector("img[alt='Registry Login']")).click();
//        driver.findElement(By.xpath("//img[@alt='swatch-bharat-logo']")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            System.out.println(windowHandle);
        }
        driver.findElement(By.id("Name")).sendKeys("Rony thomas");
        driver.findElement(By.id("Address1")).sendKeys("Karimpanamannil");
        driver.findElement(By.id("Address2")).sendKeys("Mallappally");
        driver.findElement(By.id("Address3")).sendKeys("Noorommavu");
        driver.findElement(By.id("Pincode")).sendKeys("689589");
        driver.findElement(By.id("MobileNo")).sendKeys("9400995665");
        driver.findElement(By.id("PhoneNo")).sendKeys("04842485122");
        driver.findElement(By.id("EmailAddress")).sendKeys("ronytvarghese@gmail.com");
        //driver.findElement(By.xpath("//label[text()='Female']")).click();
        //driver.findElement(By.xpath("//div[contains(@class, 'iradio_square-blue')]")).click();
       // driver.findElement(By.xpath("//label[@for='Female']")).click();
//        name="Sex" type="radio" value="M"
        driver.findElement(By.xpath("//input[@id='Sex_O']/parent::div")).click();

//        driver.findElement(By.cssSelector("input[type='radio'][value='F'][name='Sex'][id='Sex_F']")).click();
        //driver.findElement(By.xpath("//label[@for='Female']")).click();


        //Selecting the State
        Select stateSelect =new Select(driver.findElement(By.id("state")));
        stateSelect.selectByVisibleText("Kerala");
        Thread.sleep(1000);

        //Selecting the District
        Select districtSelect =new Select(driver.findElement(By.id("district")));
        districtSelect.selectByVisibleText("Kottayam");

        //Selecting the Country
        Select countrySelect =new Select(driver.findElement(By.id("country")));
        countrySelect.selectByVisibleText("India");



        Thread.sleep(3000);

        driver.close();
    }

}
