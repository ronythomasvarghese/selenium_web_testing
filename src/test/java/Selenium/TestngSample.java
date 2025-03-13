package Selenium;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLOutput;


public class TestngSample {
    @BeforeMethod
    void first(){
        System.out.println("BEFORE CLASSE");
    }

    //@parameter
    //
    @Test(priority = 2,dependsOnMethods = {"setup","logout"},groups = {"regression"})
    void login() {
    System.out.println("IN LOGIN");
    }

    @Test(priority = 1)
    void setup(){
        System.out.println("SETUP OKEEE");
    }

    @Test(priority = 0)
    void logout() {
        System.out.println("LOGOUT OKEEE");
    }

    @AfterMethod
    void quitting(){
        System.out.println("AFTER CLASSE");
    }
}
