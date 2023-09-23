package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;

import java.util.ArrayList;
import java.util.List;

public class PaginationElements extends BasePage {

    /*
     * This page elements belongs to the page "https://mdbootstrap.com/docs/b4/jquery/tables/pagination/"
     * This is for verifying the pagination case
     */

    public PaginationElements(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "dtBasicExample_next")
    WebElement nextEle;



    public List<String> getNames() {

        List<String> nameList = new ArrayList<String>();

        do{
            List<WebElement> nameEle = driver.findElements(By.xpath("//td[@class='sorting_1']"));

            for (WebElement w : nameEle) {
                nameList.add(w.getText());
            }

            String nextClassAttr = nextEle.getAttribute("class");

            if (!nextClassAttr.contains("disabled")) {
                nextEle.click();
            } else {
                break;
            }
        } while (true);

        return nameList;
    }
}
