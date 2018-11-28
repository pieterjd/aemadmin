package com.pieterjd.aemadmin.shell.commands;


import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.crx.property.DeletePropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class PropertyCommands {
    private String getStringResponse(HttpRequestCommand c){
            c.execute();
            return Boolean.toString(c.isSuccessfullyExecuted());

    }
    @ShellMethod("Returns a property of a node for the given path")
    public String getProperty(String path,String propertyName) throws IOException {
        HttpRequestCommand c = new GetPropertyCommand(path,propertyName);
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }

    @ShellMethod("Deletes a property of a node for the given path")
    public String deleteProperty(String path,String propertyName) throws IOException {
        HttpRequestCommand c = new DeletePropertyCommand(path,propertyName);
        return getStringResponse(c);
    }

    @ShellMethod("Creates a property of a node for the given path")
    public String newProperty(String path,String propertyName,String propertyValue) throws IOException {
        HttpRequestCommand c = new SetPropertyCommand(path,propertyName,propertyValue);
        return getStringResponse(c);
    }
}
