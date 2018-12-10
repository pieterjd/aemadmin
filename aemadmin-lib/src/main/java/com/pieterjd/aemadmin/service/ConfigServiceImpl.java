package com.pieterjd.aemadmin.service;

import com.pieterjd.aemadmin.config.ConfigFactory;
import org.springframework.shell.standard.ShellOption;

public class ConfigServiceImpl implements ConfigService {
    @Override
    public void connect(String baseUrl, String userName, String password, String port){
        ConfigFactory.getConfig().setProperty("baseUrl",baseUrl);
        ConfigFactory.getConfig().setProperty("userName",userName);
        ConfigFactory.getConfig().setProperty("password",password);
        ConfigFactory.getConfig().setProperty("port",port);
    }

    @Override
    public void connectLocalAuthor(){
        connect("http://localhost","admin","admin","4502");
    }
    @Override
    public void connectLocalPublish(){
        connect("http://localhost","admin","admin","4503");
    }
}
