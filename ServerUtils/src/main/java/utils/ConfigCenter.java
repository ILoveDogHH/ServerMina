package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigCenter {

    private final static String CONFIG = "config/jdbc.properties";
    private static Properties config;


    public static void reloadConfig() throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(CONFIG));
        Properties properties = new Properties();
        properties.load(inputStream);
        config = properties;
    }



    public static String getConfig(String key){
        return config.getProperty(key);
    }


}
