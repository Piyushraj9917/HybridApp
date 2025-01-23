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
import java.util.List;

import static javax.swing.UIManager.get;
import static utils.DatabaseUtils.ExecuteQuery;

public class FormPage extends AndroidActionMethods {
    ArrayList AppData = ExecuteQuery("select * from Fillform;");

    String[] firstRow;
        AndroidDriver driver;
        public FormPage(AndroidDriver driver) throws SQLException, IOException {
            super(driver);
            this.driver=driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        }
        @AndroidFindBy (id = "com.androidsample.generalstore:id/nameField") public WebElement Name;
        @AndroidFindBy(accessibility = "Predicted app: GeneralStore") public WebElement Applaunch;
        @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField") public WebElement NameField;
        @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale") public WebElement gender;
        @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry") public WebElement CountryDropDown;
        @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop") public WebElement LetsShop;

        public void EnterDetails() throws SQLException, IOException {
            //Applaunch.click();
            //WaitforAppLaunch(Name);
            firstRow = (String[]) AppData.get(0);
            String name = firstRow[0];
            NameField.sendKeys(name);
            driver.hideKeyboard();
            gender.click();
            CountryDropDown.click();
            String CountryName = firstRow[1];
            ScrollGestureByText(CountryName);
            driver.findElement(By.xpath("//android.widget.TextView[@text='"+CountryName+"']")).click();
            LetsShop.click();
        }
        @AndroidFindBy(id = "com.androidsample.generalstore:id/productImage") public WebElement ShoeElement;

        @AndroidFindBy(xpath = "com.androidsample.generalstore:id/productName")
        List<WebElement> ProductBunch;

        @AndroidFindBy(id= "com.androidsample.generalstore:id/productAddCart") WebElement AddToCartBtn;
        @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart") WebElement AddedToCart;
        @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='end me e-mails on discounts related to selected products in future']")
        WebElement checkbox;
        @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")
        WebElement WebApp;
        public void SelectProduct()
        {
            firstRow = (String[]) AppData.get(0);
            String ShoeName = firstRow[2];
            ScrollGestureByText(ShoeName);
            SelectItem(ShoeName);

            firstRow = (String[]) AppData.get(1);
            String SecondShoeName = firstRow[2];
            ScrollGestureByText(SecondShoeName);
            SelectItem(SecondShoeName);

            AddedToCart.click();


           // checkbox.click();
            //WebApp.click();


        }

}
