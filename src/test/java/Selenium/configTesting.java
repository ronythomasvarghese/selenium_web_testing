package Selenium;

import java.util.ResourceBundle;

public class configTesting {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String username = rb.getString("username");
        String password = rb.getString("password");
        System.out.println(username);

    }
}
