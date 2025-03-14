package DrtAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DrtDatePicker {
        public static void main(String[] args) throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            driver.get("https://cis.drt.gov.in/drtlive/login.php");
            driver.findElement(By.name("username")).sendKeys("listern1");
            driver.findElement(By.name("password")).sendKeys("Listern1@123");
            Thread.sleep(8000);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://cis.drt.gov.in/drtlive/pending_report.php");
            // Input: Set your target date (format: "dd-MM-yyyy")
            String targetDateStr = "15-03-2025"; // Change this to desired date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate targetDate = LocalDate.parse(targetDateStr, formatter);
            Thread.sleep(2000);


            while (true) {
                // Get currently displayed month and year
                WebElement monthYearElement = driver.findElement(By.className("datepicker-switch"));
                String displayedMonthYear = monthYearElement.getText();
                System.out.println("Displayed Month & Year: " + displayedMonthYear);

                // Convert displayed month-year to LocalDate
                DateTimeFormatter displayedFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
                LocalDate displayedDate = LocalDate.parse(displayedMonthYear, displayedFormatter);

                // Compare displayed date with target date
                if (displayedDate.getYear() == targetDate.getYear() && displayedDate.getMonth() == targetDate.getMonth()) {
                    break; // Stop when correct month & year is found
                }

                // If the target date is in the future, click Next (»), else click Previous («)
                if (displayedDate.isBefore(targetDate)) {
                    driver.findElement(By.className("next")).click();
                } else {
                    driver.findElement(By.className("prev")).click();
                }

                Thread.sleep(1000); // Wait for animation
            }

            // Select the target day
            List<WebElement> allDates = driver.findElements(By.xpath("//td[@class='day' or contains(@class,'today')]"));
            for (WebElement date : allDates) {
                if (date.getText().equals(String.valueOf(targetDate.getDayOfMonth()))) {
                    date.click();
                    break;
                }
            }

            // Close the driver
            Thread.sleep(3000);
            driver.quit();
        }
    }


