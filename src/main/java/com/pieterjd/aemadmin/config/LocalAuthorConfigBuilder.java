package com.pieterjd.aemadmin.config;

public class LocalAuthorConfigBuilder extends PropertiesConfigBuilder{
    public LocalAuthorConfigBuilder(){
        super();
        withUserName("admin");
        withPassword("admin");
        withBaseUrl("http://localhost");
        withPort(4502);
    }
}
