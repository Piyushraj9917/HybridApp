package PageObject.Android;

import Actions.AndroidActionMethods;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.DatabaseUtils.ExecuteQuery;

public class ValidationError extends AndroidActionMethods {
    AndroidDriver driver;
    public ValidationError(AndroidDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(accessibility = "Predicted app: GeneralStore") private WebElement Applaunch;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField") public WebElement NameField;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale") public WebElement gender;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry") public WebElement CountryDropDown;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop") public WebElement LetsShop;
    @AndroidFindBy(xpath = "//android.widget.Toast[1]") public WebElement ToastTag;

    public String EnterNameValidation() throws SQLException, IOException {
        ArrayList AppData = ExecuteQuery("select * from Fillform;");
        String[] firstRow = (String[]) AppData.get(0);
        NameField.clear();
        gender.click();
        CountryDropDown.click();
        String CountryName = firstRow[1];
        ScrollGestureByText(CountryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+CountryName+"']")).click();
        LetsShop.click();
        driver.hideKeyboard();
        String ToastName = ToastTag.getAttribute("name");
        return ToastName;
    }

}
