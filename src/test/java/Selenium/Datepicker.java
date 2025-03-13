package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Datepicker {

    public static void main(String[] args) throws InterruptedException {
        String provdate = "13-February-2025";
        String[] dates = provdate.split(("\\-"));

        String day= dates[0];
        String month = dates[1];
        String year = dates[2];

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Datepicker.html");
        driver.findElement(By.id("datepicker1")).click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@class= 'ui-state-default'][text()="+day+"]")).click();


    }


    public static Object dateFormatter(String input){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        // Parse the input string into a LocalDate object
        LocalDate date = LocalDate.parse(input, formatter);

        // Extract day, month, and year
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        Map<String,String> datamap = new HashMap<>();
        datamap.put("day",Integer.toString(day));
        datamap.put("Month",date.getMonth().toString().toLowerCase());
        datamap.put("Year",Integer.toString(date.getYear()));
        
        return datamap;
        
    }
}
