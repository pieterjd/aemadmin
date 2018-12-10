package com.pieterjd.aemadmin.shell.config;


import com.pieterjd.aemadmin.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AEMAdminShellConfig {

    @Bean
    public BundleService getBundleService(){

        return new BundleServiceImpl();
    }

    @Bean
    public ConfigService getConfigService(){

        return new ConfigServiceImpl();
    }

    @Bean
    public NodeService getNodeService(){

        return new NodeServiceImpl();
    }
}
