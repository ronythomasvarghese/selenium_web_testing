package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {

    public static void main(String[] args) throws IOException {
        // Initialize WebDriver (ChromeDriver in this example)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the desired URL
        driver.get("https://pfms.nic.in/SitePages/Users/LoginDetails/Login.aspx");

        // Find all anchor elements on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Loop through each link
        for (WebElement link : links) {
            // Use getDomAttribute to fetch the 'href' attribute
            String urls = link.getDomAttribute("href");
            if (urls != null) {
                try {
                    // Create a URL object
                    URL url = new URL(urls);

                    // Open an HTTP connection
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();

                    // Get the HTTP response code
                    int responseCode = httpURLConnection.getResponseCode();

                    // Check if the response code indicates a broken link (HTTP 4xx or 5xx)
                    if (responseCode >= 400) {
                        System.out.println("BROKEN LINK: " + urls + " --- " + httpURLConnection.getResponseMessage());
                    } else {
                        System.out.println("LINK is ok: " + urls + " --- " + httpURLConnection.getResponseMessage());
                    }

                    // Disconnect after processing the link
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    System.out.println("Exception for URL: " + urls + " --- " + e.getMessage());
                }
            }
        }

        // Close the browser once done
        driver.quit();
    }
}
