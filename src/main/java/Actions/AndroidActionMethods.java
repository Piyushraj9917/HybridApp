package Actions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.parser.Element;
import java.time.Duration;

public class AndroidActionMethods {
    AndroidDriver driver;
    public AndroidActionMethods(AndroidDriver driver)
    {
        this.driver=driver;
    }

    public void WaitforAppLaunch(WebElement Element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(Element));
    }

    public void ScrollGestureByText(String text)
    {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));

    }

    public void ScrollGestureByElement(WebElement scrollElement, String Direction, double Percent )
    {
        ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture",ImmutableMap.of
                ("elementId",((RemoteWebElement)scrollElement).getId(),
                "direction",Direction,
                        "percent",Percent));
    }



}
