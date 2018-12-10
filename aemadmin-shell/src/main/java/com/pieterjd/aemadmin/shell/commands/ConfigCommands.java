package com.pieterjd.aemadmin.shell.commands;


import com.pieterjd.aemadmin.config.ConfigFactory;
import com.pieterjd.aemadmin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ConfigCommands {

    @Autowired
    private ConfigService service;
    @ShellMethod("connect to an AEM instance")
    public void connect(String baseUrl,String userName,String password,@ShellOption(defaultValue = "4502") String port){
        service.connect(baseUrl,userName,password,port);
    }

    @ShellMethod("connect to local author")
    public void connectLocalAuthor(){
        service.connectLocalAuthor();
    }
    @ShellMethod("connect to local publish")
    public void connectLocalPublish(){
        service.connectLocalPublish();
    }
}
