package Selenium;


import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class practicejava {
    public static void main(String[] args) throws InterruptedException {
        String email = "john@exac.mple.com.123";

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        Matcher mat = pat.matcher(email);

        if (mat.matches())
            System.out.println("Valid email");
        else
            System.out.println("Invalid email");

    }
}
