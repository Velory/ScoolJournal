package sirotkina.sjournal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {


    public static Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
