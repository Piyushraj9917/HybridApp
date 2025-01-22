package Tests;

import PageObject.Android.ValidationError;

import TestComponents.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class NegativeScenarios extends BaseTest {
    @Test
    public void Shopformvalidation() throws SQLException, IOException {
        ValidationError Validation = new ValidationError(driver);
        String ToastName = Validation.EnterNameValidation();
        Assert.assertEquals(ToastName, "Please enter your name");
        driver.navigate().back();
    }
}
