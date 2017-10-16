package com.pieterjd.aemadmin.config;

import java.util.Properties;

public abstract class ConfigBuilder {
    public abstract ConfigBuilder withUserName(String userName);
    public abstract ConfigBuilder withPassword(String password);
    public abstract ConfigBuilder withBaseUrl(String baseUrl);
    public abstract ConfigBuilder withPort(int port);
    public abstract Properties build();
}
