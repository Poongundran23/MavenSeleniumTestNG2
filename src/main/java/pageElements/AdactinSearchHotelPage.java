package pageElements;

import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;

public class AdactinSearchHotelPage extends BasePage {

    public AdactinSearchHotelPage() {
        PageFactory.initElements(driver,this);
    }
}
