package com.pieterjd.aemadmin.service;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.property.DeletePropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

public class PropertyServiceImpl implements PropertyService {

    private String getStringResponse(HttpRequestCommand c){
        c.execute();
        return Boolean.toString(c.isSuccessfullyExecuted());

    }

    public String getProperty(String path,String propertyName) throws IOException {
        HttpRequestCommand c = new GetPropertyCommand(path,propertyName);
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }

    public String deleteProperty(String path,String propertyName) throws IOException {
        HttpRequestCommand c = new DeletePropertyCommand(path,propertyName);
        return getStringResponse(c);
    }

    public String newProperty(String path,String propertyName,String propertyValue) throws IOException {
        HttpRequestCommand c = new SetPropertyCommand(path,propertyName,propertyValue);
        return getStringResponse(c);
    }
}
