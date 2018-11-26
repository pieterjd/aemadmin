package com.pieterjd.aemadmin.config;

public class LocalPublishConfigBuilder extends PropertiesConfigBuilder{
    public LocalPublishConfigBuilder(){
        super();
        withUserName("admin");
        withPassword("admin");
        withBaseUrl("http://localhost");
        withPort(4503);
    }
}
