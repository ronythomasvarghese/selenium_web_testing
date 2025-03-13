package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class SliderMethods {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.automationtesting.in/Slider.html");

        Actions act = new Actions(driver);
        WebElement min_slider = driver.findElement(By.xpath("//a[contains(@class, 'ui-slider-handle')]"));
        ////a[@class='ui-slider-handle ui-state-default ui-corner-all ui-state-hover ui-state-focus']
        Point minSliderLocation = min_slider.getLocation();
        System.out.println(minSliderLocation);

        act.dragAndDropBy(min_slider,199,100).build().perform();
        Thread.sleep(1000);
        Point minSliderLocationafter = min_slider.getLocation();
        System.out.println(minSliderLocationafter);
       // Thread.sleep(2000);
        driver.close();
    }
}
