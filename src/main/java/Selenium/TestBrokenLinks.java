package Selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBrokenLinks {
    public static void main(String[] args) {

        // Set Chrome options to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--disable-gpu"); // Applicable to Windows OS only
        // You can add more options if needed

        // Initialize the ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Navigate to the desired URL
        driver.get("https://drt.gov.in/");

        // Get all link elements on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());

        // Iterate through each link and check its status
        for (WebElement link : links) {
            String url = link.getAttribute("href");

            // Skip if the URL is not configured or empty
            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty.");
                continue;
            }

            try {
                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode >= 400) {
                    System.out.println(url + " is a broken link. Response code: " + responseCode);
                }
//                else {
//                    System.out.println(url + " is a valid link. Response code: " + responseCode);
//                }
            } catch (IOException e) {
                System.out.println("Exception while checking URL: " + url);
                e.printStackTrace();
            }
        }

        // Close the browser
        driver.quit();
    }
}
