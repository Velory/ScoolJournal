//TODO use some package
import org.flywaydb.core.Flyway;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Flyway flyway = new Flyway();
        Properties properties = new Properties();
        //TODO do not use absolute path
        //TODO how will you close FileReader?
        properties.load(new FileReader("D:\\Java\\ScoolJournal\\src\\main\\resources\\db.properties"));

        flyway.setDataSource(properties.getProperty("Flyway.url"),
                properties.getProperty("Flyway.user"), properties.getProperty("Flyway.password"));

        /*flyway.setDataSource("jdbc:mysql://localhost:3306/mydb",
                "root", "marishach");*/

        flyway.migrate();
    }
}
