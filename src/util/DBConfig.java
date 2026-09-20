package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
* Database Configuration
* */
public class DBConfig {

    /*
    * Get enviroment value
    *
    * @return properties
    * */
    public static Properties loadConfig() {
        Properties prop = new Properties();

        try (InputStream input = DBConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")
        ) {
            if (input == null) {
                throw new RuntimeException("File config not found");
            }

            prop.load(input);

        }catch(IOException e) {
            throw new RuntimeException("Failed to load file", e);

        }

        return prop;
    }

    /*
    * Get database url
    *
    * @return url
    * */
    public static String getUrl() {
        return loadConfig().getProperty("URL");
    }

    /*
    * Get database username
    *
    * @return username
    * */
    public static String getUsername() {
        return loadConfig().getProperty("DB_USERNAME");
    }


    /*
    * Get database password
    *
    * @return password
    * */
    public static String getPassword() {
        return loadConfig().getProperty("DB_PASSWORD");
    }


}
