package testRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageElements.AdactinSearchHotelPage;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

public class AdactinSearchHotelsTest extends BasePage {
    AdactinLoginTest adactinLoginTest;
    AdactinSearchHotelPage adactinSearchHotelPage;
    SeleniumUtils utils;

    @BeforeClass
    public void setupClass() throws Exception {
        adactinLoginTest = new AdactinLoginTest();
        adactinLoginTest.beforeClass(); // it will invoke the setupDriver method
        adactinSearchHotelPage = new AdactinSearchHotelPage();
        utils = new SeleniumUtils();
    }


    @Test(description = "This case get the cities option in the adactin hotel booking page")
    public void searchCites() throws InterruptedException {
        adactinLoginTest.loginTest();
        System.out.println(adactinSearchHotelPage.getCities());
        adactinSearchHotelPage.selectCity("Sydney");
        adactinSearchHotelPage.selectHotel("Hotel Cornice");
        adactinSearchHotelPage.selectRoomType("Deluxe");
        adactinSearchHotelPage.selectNoOfRoom("1");
        adactinSearchHotelPage.enterCheckInDate("22/11/2025");
        adactinSearchHotelPage.enterCheckoutDate("25/11/2025");
        adactinSearchHotelPage.enterNoOfAdults("2");
        adactinSearchHotelPage.clickSearch();
        adactinSearchHotelPage.confirmAndContinue();
    }

    @AfterClass
    public void afterClasss() {
        quitBrowser();
    }
}
