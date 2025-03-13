package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class WindowHandles {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
        //fetch handle of this
        String currHandle=driver.getWindowHandle();
        System.out.println(currHandle);
        assertNotNull(currHandle);

        //click on link to open a new window
        driver.findElement(By.linkText("Open new window")).click();
        //fetch handles of all windows, there will be two, [0]- default, [1] - new window

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        Thread.sleep(3);
        //assert on title of new window
        String title=driver.getTitle();
        assertEquals("Simple Page",title);

        //closing current window
        driver.close();
        //Switch back to the old tab or window
        driver.switchTo().window((String) windowHandles[0]);

        //Opens a new tab and switches to new tab
        driver.switchTo().newWindow(WindowType.TAB);
        assertEquals("",driver.getTitle());
        Thread.sleep(300);
        //Opens a new window and switches to new window
        driver.switchTo().newWindow(WindowType.WINDOW);
        assertEquals("",driver.getTitle());
        Thread.sleep(300);
        //quitting driver
        driver.quit(); //close all windows

    }
}
