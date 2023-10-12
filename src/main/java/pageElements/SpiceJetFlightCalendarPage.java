package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

import java.io.IOException;
import java.util.List;

public class SpiceJetFlightCalendarPage extends BasePage {

    public SpiceJetFlightCalendarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='css-76zvg2 css-bfa6kz r-homxoj r-ubezar']")
    WebElement currentDate;

    @FindBy(xpath = "//div[contains(@class,'r-1loqt21 r-u8s1d')]")
    WebElement nextMonthBtn;


    /*
     * @param - String as dd mmm yyyy
     * e.g., "23 October 2023"
     */

    public void chooseCalenderDate(String date) throws IOException {
        String[] dat = date.split(" ");
        String day = dat[0];
        String month = dat[1];
        String year = dat[2];

        String pDate = currentDate.getText();

        currentDate.click();

        SeleniumUtils.takeScreenShot("C:/Users/len/IdeaProjects/MavenSeleniumTestNG2/src/test/java/screenShot/spiceJetscr.png");

        List<WebElement> currMonths; // = driver.findElements(By.xpath("//div[@class='css-1dbjc4n r-k8qxaj']"));

        for (int i = 1; i < 15; i = i + 2) {
            currMonths = driver.findElements(By.xpath("//div[@class='css-1dbjc4n r-k8qxaj']"));
            for (WebElement w : currMonths) {
                System.out.print(" " + currMonths.get(0).getText() + " ");
            }
            System.out.println();

            if (currMonths.get(i - 1).getText().contains(month) && currMonths.get(i - 1).getText().contains(year)) {
                driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-k8qxaj'])[" + (i) + "]/following-sibling::div[2]//div[text()='" + day + "']")).click();
                break;
            } else if (currMonths.get(i).getText().contains(month) && currMonths.get(i).getText().contains(year)) {
                driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-k8qxaj'])[" + (i + 1) + "]/following-sibling::div[2]//div[text()='" + day + "']")).click();
                break;
            } else {
                nextMonthBtn.click();
            }

        }

    }

}
