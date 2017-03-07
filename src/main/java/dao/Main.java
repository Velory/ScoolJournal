package dao;
import org.flywaydb.core.Flyway;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        Properties properties = new Properties();

        try (FileReader fr = new FileReader("db.properties")){
            properties.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        flyway.setDataSource(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));

        flyway.migrate();
    }
}
