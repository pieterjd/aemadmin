package com.pieterjd.aemadmin.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory {
    private Properties properties;

    public static final String CMDLINE_PROPERTY_FILE = "propertiesFile";

    /**
     * If the propertiesFile cmd line property is set, read that file; otherwise use the local author instance
     * @return The properties of the node to connect to
     */
    private static Properties readDefaultProperties() {
        Properties result = new Properties();

        if(System.getProperty(CMDLINE_PROPERTY_FILE) != null){
            try (InputStream inputStream = new FileInputStream(System.getProperty("propertiesFile"))) {
                result.load(inputStream);
            } catch (IOException e) {
                System.out.println("Cannot read cmdline provided properties file");
                result = new LocalAuthorConfigBuilder().build();
            }
        }
        else {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("aemadmin.properties")) {
                result.load(inputStream);
            } catch (IOException e) {
                result = new LocalAuthorConfigBuilder().build();
            }
        }
        return result;
    }
    private static ConfigFactory instance;
    private ConfigFactory(){
        readDefaultProperties();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static Properties getConfig(){
        if(instance == null){
            instance = new ConfigFactory();
        }
        return instance.getProperties();
    }

}
