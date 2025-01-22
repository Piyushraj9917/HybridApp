package Tests;

import PageObject.Android.FormPage;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PurchaseProduct extends BaseTest {

    @Test
    public void FillformPostiveScenario () throws SQLException, IOException {
        FormPage fp = new FormPage(driver);
        fp.EnterDetails();
        fp.SelectProduct();


    }

}
