package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageElements.DynamicTableHandlingPage;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;
import qaUtils.XLUtils;

import java.util.List;

public class DynamicTableTest extends BasePage {

    DynamicTableHandlingPage dynamicTableHandlingPage;
    SeleniumUtils seleniumUtils;
    XLUtils xlUtils;

    @BeforeClass
    public void setUpClass() throws Exception {
        setupDriver();
        dynamicTableHandlingPage = new DynamicTableHandlingPage();
        seleniumUtils = new SeleniumUtils();
        xlUtils = new XLUtils(prop.getProperty("XLpath"));
    }

    @DataProvider
    public Object[][] getTestData() {
        Object data[][] = xlUtils.testData(prop.getProperty("DemoQASheet"));
        return data;
    }

    public void launchUrl() {
        seleniumUtils.launchUrl(prop.getProperty("WebtableUrl"));
    }

    public List<String> getFirstNameList() {
        return dynamicTableHandlingPage.getFirstNameList();
    }

    @Test(dataProvider = "getTestData", description = "This method is to insert new data record in the table")
    public void insertData(String fName, String lName, String age, String email, String salary, String dept) {
        launchUrl();
        dynamicTableHandlingPage.insertNewRecord(fName,lName,age,email,salary,dept);
    }

    @Test(description = "This method returns the details of the searched employee")
    public void searchAndVerifyTheEmployeeDetail() throws InterruptedException {
        launchUrl();
        dynamicTableHandlingPage.insertNewRecord("Test","Me","36", "test@me.com","10000","Fashion");
        List<String> emp = dynamicTableHandlingPage.searchAndVerifyTheUserDetailsInTheTable("Test");
        logger.info("Employee Details: "+emp);
        Assert.assertEquals(emp.get(2),"36","Mismatch in the employee detail");
    }
}
