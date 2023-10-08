package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageElements.AdactinLoginPage;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

public class AdactinLoginTest extends BasePage {

    AdactinLoginPage adactinLoginPage;
    SeleniumUtils utils;

    @BeforeClass
    public void beforeClass() throws Exception {
        setupDriver();
        adactinLoginPage = new AdactinLoginPage();
        utils = new SeleniumUtils();
    }


    @Test(description = "This testcase tests login function")
    public void loginTest() throws InterruptedException {
        utils.launchUrl(prop.getProperty("CrmUrl"));
        adactinLoginPage.login(prop.getProperty("UserName"), prop.getProperty("Password"));
        Thread.sleep(5000); // to get title; there is no element to do explicit wait
//        logger.info(utils.getTitle());
        Assert.assertEquals(utils.getTitle(), "Adactin.com - Search Hotel", "Mismatch in title");
    }


    @AfterClass
    public void afterClass() {
//        quitBrowser();
    }
}

