package sirotkina.sjournal.utils;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class MigrationsUtils {

    public static void migrate (){
        Flyway flyway = new Flyway();

        Properties properties = new Properties();

        try (InputStream is = MigrationsUtils.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(is);
            flyway.setDataSource(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Migration failed. cause:" + e.getMessage());
            System.out.println("db.url = " + properties.getProperty("db.url") +
                    "\n db.user = " + properties.getProperty("db.user") +
                    "\n db.password = " + properties.getProperty("db.password"));
        }
    }

}
