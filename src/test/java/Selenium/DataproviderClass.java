package Selenium;

import org.testng.annotations.DataProvider;

public class DataproviderClass {

    @DataProvider(name = "dp")
    public Object[][] dpclass() {

        Object [][] credentials= {
                        {"rony","1234"},
                        {"johny","1234"},
                        {"Admin","1234"},
                        {"Admin","admin123"}
                    };
        return credentials;

    }
}
