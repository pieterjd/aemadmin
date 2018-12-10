package com.pieterjd.aemadmin.shell.config;


import com.pieterjd.aemadmin.service.BundleService;
import com.pieterjd.aemadmin.service.BundleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AEMAdminShellConfig {

    @Bean
    public BundleService getBundleService(){
        return new BundleServiceImpl();
    }
}
