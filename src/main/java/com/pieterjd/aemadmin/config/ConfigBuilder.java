package com.pieterjd.aemadmin.config;

import java.util.Properties;

public abstract class ConfigBuilder {
    public abstract void withUserName(String userName);
    public abstract void withPassword(String password);
    public abstract void withBaseUrl(String baseUrl);
    public abstract void withPort(int port);
    public abstract Properties build();
}
