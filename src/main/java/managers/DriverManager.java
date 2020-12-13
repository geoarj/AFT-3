package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static utils.PropConst.*;

/**
 * Этот драйвер нужно запустить!
 * Он исполняет наши тесты!
 */

public class DriverManager {

    private static WebDriver driver;

    private static TestPropManager propManager = TestPropManager.getTestPropManager();

    private DriverManager() {}

    public static void initDriver() {
        switch (propManager.getProperty(TYPE_BROWSER)) {
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", propManager.getProperty(PATH_GEKO_DRIVER));
                driver = new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver", propManager.getProperty(PATH_CHROME_DRIVER));
                driver = new ChromeDriver();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null){
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }

}
