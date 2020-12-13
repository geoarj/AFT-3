package managers;

import java.io.*;
import java.util.Properties;

/**
 * class for managing properties
 */
public class TestPropManager {

    private final Properties properties = new Properties();

    private static TestPropManager INSTANCE = null;

    private TestPropManager() {
        try {
            properties.load(new FileInputStream(
                            new File("src/main/resources/" +
                            System.getProperty("env", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TestPropManager getTestPropManager() {
        if (INSTANCE == null) {
            INSTANCE = new TestPropManager();
        }
            return INSTANCE;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
