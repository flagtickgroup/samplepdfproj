package com.app.flagtick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    public Properties loadProperties(String fileName) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                LOGGER.error("Sorry, unable to find " + fileName);
                throw new FileNotFoundException("File not found: " + fileName);
            }

            Properties prop = new Properties();
            prop.load(input);
            return prop;
        }
    }
}