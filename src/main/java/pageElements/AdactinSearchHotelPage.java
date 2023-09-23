package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;

public class AdactinSearchHotelPage extends BasePage {

    public AdactinSearchHotelPage() {
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "location")
    WebElement locationBtn;
}
