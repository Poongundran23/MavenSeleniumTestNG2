package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import qaBase.BasePage;

public class AdactinLoginPage extends BasePage {

    public AdactinLoginPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    WebElement userNameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement loginBtn;


    public void login(String userName, String password) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginBtn.click();
    }

    public void verifyTheHomePageElement(String pageTitle) {
        Assert.assertEquals(getTitle(),pageTitle, "Mismatch in page title");
    }
}
