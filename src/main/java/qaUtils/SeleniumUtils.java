package qaUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import qaBase.BasePage;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils extends BasePage {

    public static TakesScreenshot tk;
    public static JavascriptExecutor js;
    public static Actions actions;
    public static Robot robot;
    public static Alert alert;
    public static Rectangle rect;

    public SeleniumUtils() {
        try {
            tk = (TakesScreenshot) driver;
            js = (JavascriptExecutor) driver;
            actions = new Actions(driver);
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * WebElement methods
     */
    public void click(WebElement element) {
        element.click();
        logger.info(element + " clicked");
    }

    public String getText(WebElement element) {
        String text = element.getText();
        logger.info("Text Got: " + text);
        return text;
    }

    public void setText(WebElement element, String keys) {
        element.clear();
        element.sendKeys(keys);
        logger.info("Keys send : " + keys);
    }

    public String getAttribute(WebElement element, String attribute) {
        String attributeValue = element.getAttribute(attribute);
        logger.info("Attribute Value: " + attributeValue);
        return attributeValue;
    }

    public String getCssValue(WebElement element, String propertyName) {
        String cssValue = element.getCssValue(propertyName);
        logger.info("Css Value: " + cssValue);
        return cssValue;
    }

    public String getTagName(WebElement element, String attribute) {
        String tagName = element.getTagName();
        logger.info("Attribute Value: " + tagName);
        return tagName;
    }

    public Boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public Boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public Boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    /*
     * Rect methods
     */
    public org.openqa.selenium.Point getPoint(WebElement element) { // This method uses Rectangle method
        rect = element.getRect();
        return rect.getPoint();
    }

    public Dimension getDimension(WebElement element) { // This method uses Rectangle method
        rect = element.getRect();
        return rect.getDimension();
    }

    /*
     * Select class methods
     */
    public List<String> getOptions(WebElement element) {
        Select select = new Select(element);
        List<WebElement> op = select.getOptions();
        ArrayList<String> options = new ArrayList<>();
        for (WebElement o : op) {
            options.add(o.getText());
        }
        return options;
    }

    public void selectByIndex(WebElement element, Integer... index) {
        Select select = new Select(element);
        for (Integer i : index) {
            select.selectByIndex(i);
        }
    }

    public void selectByValue(WebElement element, String... value) {
        Select select = new Select(element);
        for (String s : value) {
            select.selectByValue(s);
        }
    }

    public void selectByVisibleText(WebElement element, String... text) {
        Select select = new Select(element);
        for (String s : text) {
            select.selectByVisibleText(s);
        }
    }

    public void deSelectByIndex(WebElement element, Integer... index) {
        Select select = new Select(element);
        for (Integer i : index) {
            select.deselectByIndex(i);
        }
    }

    public void deSelectByValue(WebElement element, String... value) {
        Select select = new Select(element);
        for (String s : value) {
            select.deselectByValue(s);
        }
    }

    public void deSelectByVisibleText(WebElement element, String... text) {
        Select select = new Select(element);
        for (String s : text) {
            select.deselectByVisibleText(s);
        }
    }

    public void deSelectAll(WebElement element) {
        Select select = new Select(element);
        select.deselectAll();
    }

    public List<WebElement> getAllSelectedOptions(WebElement element) {
        Select select = new Select(element);
        return select.getAllSelectedOptions();
    }

    /* Take screenshot */
    public static void takeScreenShot() throws IOException {
        File srcFile = tk.getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        logger.info("current dir: "+currentDir);
        String destination = currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png";
        logger.info("dest: "+ destination);
        File desFile = new File(destination);
        FileUtils.copyFile(srcFile, desFile);
    }

    /* Using JavaScript */
    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickByJavaScript(WebElement element) {
        highlightByJavaScript(element);
        js.executeScript("arguments[0].click();", element);
    }

    public void setTextByJavaScript(WebElement element, String text) {
        js.executeScript("arguments[0].setAttribute('value','" + text + ")", element);
    }

    public void highlightByJavaScript(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style','border: solid 2px red');", element);
    }

    /*
     * Actions class methods
     */

    public void mouseHoverTo(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    /*
     * Robot Class
     */

    public void keyDown() {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
    }

    public void keyEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /*
     * Alert methods
     */

    public void acceptAlert() {
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText() {
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
//        logger.info("Alert Text: " + alertText);
        return alertText;
    }

    public void sendKeysToAlert(String keys) {
        alert = driver.switchTo().alert();
        alert.sendKeys(keys);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /*
     * Waits
     */

    public void implicitWait(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitUntilElementIsVisible(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementToBeClicked(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilAlertIsPresent(long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilElementIsSelected(WebElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitUntilVisisbilityOfAllElements(long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements());
    }

    public void fluentWait(long timeOutInSec, long pollFreqInSecs) {
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeOutInSec)).pollingEvery(Duration.ofMillis(pollFreqInSecs)).ignoring(Exception.class);
    }

    public Boolean checkIsTheBrokenLink(String link) throws IOException {
        Boolean isTheLinkBroken;
        URL url = new URL(link);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        if (!(con.getResponseCode() == 200)) {
            isTheLinkBroken = Boolean.TRUE;
        } else {
            isTheLinkBroken = Boolean.FALSE;
        }
        return isTheLinkBroken;
    }

}
