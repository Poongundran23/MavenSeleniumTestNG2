package tests;

import net.bytebuddy.implementation.bytecode.Throw;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageElements.SpiceJetFlightCalendarPage;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

import java.io.IOException;

public class SpiceJetDateChooserTest extends BasePage {

    SpiceJetFlightCalendarPage spiceJetFlightCalendarPage;
    SeleniumUtils seleniumUtils;

    @BeforeClass
    public void setUpClass() throws Exception {
        setupDriver();
        spiceJetFlightCalendarPage = new SpiceJetFlightCalendarPage();
        seleniumUtils = new SeleniumUtils();
    }

    @Test(description = "This method demos the date picker in the indigo page")
    public void chooseDate() throws IOException {
        String bookingDate = "23 January 2024";
        launchUrl(prop.getProperty("spiceJetUrl"));
        spiceJetFlightCalendarPage.chooseCalenderDate(bookingDate);
    }

    @AfterClass()
    public void afterClass() {
        seleniumUtils.quitBrowser();
    }

}
