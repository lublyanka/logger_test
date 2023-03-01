package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertiesLoader {
    private static final Logger log = LogManager.getLogger(PropertiesLoader.class);

    private static PropertiesLoader instance;
    private final static Properties properties = new Properties();

    private PropertiesLoader() {
        try {
            properties.load(new FileInputStream(Constants.SETTINGS_FILE));
            String url = properties.getProperty("connection.url");
            if (url.isBlank())
                throw new IllegalArgumentException();
            log.info(url.substring(0,url.indexOf("password")));
/*            log.trace("trace");
            log.debug("debug");
            log.warn("warn");
            log.error("err");
            log.fatal("fatal");*/
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("Property file wasn't found");
            throw new IllegalArgumentException("");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static PropertiesLoader getInstance() {
        if (instance == null) {
            instance = new PropertiesLoader();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
