package com.pieterjd.aemadmin.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Basic Properties backed implementation
 *
 */
public class PropertiesConfigBuilder extends ConfigBuilder {
    private Properties properties;

    /**
     * Constructs a config builder with an empty properties object
     */
    public PropertiesConfigBuilder(){
        this(new Properties());
    }

    /**
     * Constructs a config builder starting with the given properties
     * @param properties Properties you want to init the object with
     */
    public PropertiesConfigBuilder(Properties properties){
        this.properties = properties;
    }

    /**
     * Constructs a config builder starting from a properties file
     * @param fileName the file you want to read from
     * @throws FileNotFoundException When you point to an non-existing file
     * @throws IOException When something goes wrong with the IO
     */
    public PropertiesConfigBuilder(String fileName) throws FileNotFoundException,IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(fileName));
        this.properties = properties;
    }

    @Override
    /**
     * Updates the userName property
     */
    public ConfigBuilder withUserName(String userName) {
        properties.put("userName",userName);
        return this;
    }

    @Override
    /**
     * updates the password property
     */
    public ConfigBuilder withPassword(String password) {
        properties.put("password",password);
        return this;
    }

    @Override
    /**
     * updates the baseUrl property
     */
    public ConfigBuilder withBaseUrl(String baseUrl) {
        properties.put("baseUrl",baseUrl);
        return this;
    }

    @Override
    /**
     * updates the port property
     */
    public ConfigBuilder withPort(int port) {
        properties.put("port",String.valueOf(port));
        return this;
    }

    @Override
    public Properties build() {
        return (Properties) properties.clone();
    }
}
