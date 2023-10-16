package qaBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import qaUtils.WebEventListener;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BasePage {
    protected static Properties prop;
    protected static WebDriver driver;
    protected static EventFiringWebDriver e_driver;
    protected static WebEventListener eventListener;
    protected static Logger logger;

    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream in = new FileInputStream("C:/Users/len/IdeaProjects/MavenSeleniumTestNG2/src/main/resources/configfile.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void setupDriver() throws Exception {
        if (prop.getProperty("browser").equals("chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/User/Home/Driver/geckdriver.exe");
        }

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        logger = LogManager.getLogger(BasePage.class);
    }

    /*
     * WebDriver methods
     */
    public void launchUrl(String url) {
        driver.get(url);
        logger.info("Url launched");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        Set<String> windowHandles = driver.getWindowHandles();
        logger.info("WindowHandles: " + windowHandles);
        return windowHandles;
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void switchToFrame(String frameId) {
        driver.switchTo().frame(frameId);
        logger.info("Switched to iFrame" + frameId);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void closeWindow() {
        driver.close();
    }

    public void quitBrowser() {
        driver.quit();
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    // selenium4 methods
    public void openToNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
}
