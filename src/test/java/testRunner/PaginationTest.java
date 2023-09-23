package testRunner;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageElements.PaginationElements;
import qaBase.BasePage;
import qaUtils.Utils;

import java.util.List;

/*
 * This test to exemplify a pagination case
 * Taking the site for demonstration - https://mdbootstrap.com/docs/b4/jquery/tables/pagination/
 *
 * @author - poongundran
 */

public class PaginationTest extends BasePage {
    PaginationElements p;
    Utils utils;

    @BeforeClass
    public void setupMethod() throws Exception {
        setupDriver();
        p = new PaginationElements();
        utils = new Utils();
    }

    @Test(description = "This test will do the pagination and get all names in the names field")
    public void paginationTest() {
        utils.launchUrl(prop.getProperty("PagiUrl"));

        List<String> nameList = p.getNames();

        System.out.println("Total no of entries: \n" + nameList.size());
        System.out.println("NameList: \n" + nameList);

        Assert.assertEquals(nameList.size(), 57, "Mismatch in number of entries");
        Assert.assertTrue(nameList.contains("Michael Bruce"));
        Assert.assertFalse(nameList.contains("Rahul Dravid"));
    }

    @AfterClass
    public void afterClass() {
        quitBrowser();
    }
}
