package testRunner;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageElements.AdactinBookHotelPage;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;
import qaUtils.XLUtils;

import java.util.Properties;

public class AdactinBookHotelTest extends BasePage {
    AdactinLoginTest adactinLoginTest;
    AdactinSearchHotelsTest adactinSearchHotelsTest;
    AdactinBookHotelPage adactinBookHotelPage;
    SeleniumUtils utils;
    XLUtils xlUtils;

    @BeforeClass
    public void beforeClass() throws Exception {
        adactinSearchHotelsTest = new AdactinSearchHotelsTest();
        adactinSearchHotelsTest.setupClass(); // this invokes setupDriver
        adactinBookHotelPage = new AdactinBookHotelPage();
        utils = new SeleniumUtils();
        xlUtils = new XLUtils(prop.getProperty("AdactinNameSheet"));
    }


    @DataProvider
    public Object[][] getTestData() {
        Object data[][] = xlUtils.testData(prop.getProperty("SheetName"));
        return data;
    }


    @Test(dataProvider = "getTestData", description = "This is to verify the booking in adactin book hotel page")
    public void bookHotelTest(String fname, String lname, String addr) throws InterruptedException {
        adactinSearchHotelsTest.searchCites();
        adactinBookHotelPage.enterFirstName(fname);
        adactinBookHotelPage.enterLastName(lname);
        adactinBookHotelPage.enterAddress(addr);
        adactinBookHotelPage.enterCreditCardNo();
        adactinBookHotelPage.selectCreditCardType();
        adactinBookHotelPage.selectExpMonth();
        adactinBookHotelPage.selectExpYear();
        adactinBookHotelPage.enterCVVNo();
        adactinBookHotelPage.clickBookNow();
        Thread.sleep(9000);
        Assert.assertEquals(adactinBookHotelPage.verifyConfirmationTitle().trim(), "Booking Confirmation", "Mismatch in confirmatin title");
        System.out.println(adactinBookHotelPage.verifyOrderNo());
    }


    @AfterClass
    public void afterClass() {
        quitBrowser();
    }

}

