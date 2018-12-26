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
    @Bean
    public PackageService getPackageService(){

        return new PackageServiceImpl();
    }
    @Bean
    public PropertyService getPropertyService(){

        return new PropertyServiceImpl();
    }

    @Bean
    public QueryService getQueryService(){

        return new QueryServiceImpl();
    }
}
