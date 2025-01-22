package TestComponents;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;

public class BaseTest {
    public static AndroidDriver driver;
    public static AppiumDriverLocalService service;


    @BeforeMethod
    public void setup() throws IOException, SQLException {
        // Start Appium service
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
        // Set options for the AndroidDriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("PiyushPhone");
        options.setApp(System.getProperty("user.dir"+"src/main/resources/General-Store.apk"));
        // Initialize AndroidDriver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        // Quit driver and stop Appium service after each test
        try{
            if(driver != null)
            {
                System.out.println("driver is closing ......");
                driver.quit();
                System.out.println("driver is closed");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(service != null && service.isRunning())
            {
                System.out.println("Service is getting closed......");
                service.stop();
                System.out.println("Service has been closed");
            }
        }
    }
}
