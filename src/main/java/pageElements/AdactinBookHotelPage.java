package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

public class AdactinBookHotelPage extends BasePage {

    SeleniumUtils utils = new SeleniumUtils();

    public AdactinBookHotelPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first_name")
    WebElement firstNameField;

    @FindBy(id = "last_name")
    WebElement lastNameField;

    @FindBy(id = "address")
    WebElement addressField;

    @FindBy(id = "cc_num")
    WebElement creditCardField;

    @FindBy(id = "cc_type")
    WebElement ccTypeEle;

    @FindBy(id = "cc_exp_month")
    WebElement ccExpMonthEle;

    @FindBy(id = "cc_exp_year")
    WebElement ccExpYearEle;

    @FindBy(id = "cc_cvv")
    WebElement cvvField;

    @FindBy(name = "book_now")
    WebElement bokNowBtn;

    @FindBy(xpath = "//td[@class='login_title']")
    WebElement bookingConfirmTitle;

    @FindBy(id = "order_no")
    WebElement orderNoField;


    public void enterFirstName(String fName) {
        utils.setText(firstNameField, fName);
    }

    public void enterLastName(String lName) {
        utils.setText(lastNameField, lName);
    }

    public void enterAddress(String address) {
        utils.setText(addressField, address);
    }

    public void enterCreditCardNo() {
        utils.setText(creditCardField, "1111 2222 3333 4444");
    }

    public void selectCreditCardType() {
        utils.selectByValue(ccTypeEle, "VISA");
    }

    public void selectExpMonth() {
        utils.selectByValue(ccExpMonthEle, "12");
    }

    public void selectExpYear() {
        utils.selectByValue(ccExpYearEle, "2025");
    }

    public void enterCVVNo() {
        utils.setText(cvvField, "111");
    }

    public void clickBookNow() {
        bokNowBtn.click();
    }

    public String verifyConfirmationTitle() {
        utils.waitUntilElementIsVisible(bookingConfirmTitle,7);
        return bookingConfirmTitle.getText();
    }

    public String verifyOrderNo() {
        utils.waitUntilElementIsVisible(orderNoField,12);
        return orderNoField.getText();
    }
}
