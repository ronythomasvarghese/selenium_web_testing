package DrtAutomation;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = 3;  // Retry up to 3 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            if (result.getThrowable().getMessage().contains("CAPTCHA MISMATCH")) {
                retryCount++;
                System.out.println("Retrying CAPTCHA test: Attempt " + (retryCount + 1));
                return true;
            }
        }
        return false;
    }
}