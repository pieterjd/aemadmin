package com.pieterjd.aemadmin.shell.commands;


import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.crx.property.DeletePropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import com.pieterjd.aemadmin.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class PropertyCommands {
    @Autowired
    private PropertyService propertyService;

    @ShellMethod("Returns a property of a node for the given path")
    public String getProperty(String path,String propertyName) throws IOException {
        return propertyService.getProperty(path, propertyName);
    }

    @ShellMethod("Deletes a property of a node for the given path")
    public String deleteProperty(String path,String propertyName) throws IOException {
        return propertyService.deleteProperty(path, propertyName);
    }

    @ShellMethod("Creates a property of a node for the given path")
    public String newProperty(String path,String propertyName,String propertyValue) throws IOException {
        return propertyService.newProperty(path, propertyName, propertyValue);
    }
}
