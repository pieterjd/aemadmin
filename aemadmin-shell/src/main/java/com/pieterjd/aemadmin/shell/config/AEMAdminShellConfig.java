package com.pieterjd.aemadmin.shell.config;


import com.pieterjd.aemadmin.service.AEMAdminService;
import com.pieterjd.aemadmin.service.AemAdminServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AEMAdminShellConfig {

    @Bean
    public AEMAdminService getAemAdminService(){
        return new AemAdminServiceImpl();
    }
}
