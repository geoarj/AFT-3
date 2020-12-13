package managers;

import java.util.concurrent.TimeUnit;

import static managers.DriverManager.getDriver;
import static managers.DriverManager.quitDriver;
import static utils.PropConst.*;

/**
 * Инициализирует драйвер!
 */
public class InitManager {

    private static TestPropManager proper = TestPropManager.getTestPropManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(proper.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(proper.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().get(proper.getProperty(APP_URL));
    }

    public static void quitFramework() {
        quitDriver();
    }
}
