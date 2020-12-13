package base;

import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTests {
    protected TestPropManager prop = TestPropManager.getTestPropManager();
    protected PageManager app = PageManager.getManagerPages();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @AfterClass
    public static void afterAll() {
//        InitManager.quitFramework();
    }
}
