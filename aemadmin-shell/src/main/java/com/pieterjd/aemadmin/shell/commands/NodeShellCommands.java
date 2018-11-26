package com.pieterjd.aemadmin.shell.commands;


import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class NodeShellCommands {
    private String getStringResponse(HttpRequestCommand c){
            c.execute();
            return Boolean.toString(c.isSuccessfullyExecuted());

    }
    @ShellMethod("Returns a node for the given path")
    public String getNode(String path) throws IOException {
        HttpRequestCommand c = new GetNodeCommand(path);
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }

    @ShellMethod("Deletes a node for the given path")
    public String deleteNode(String path) throws IOException {
        HttpRequestCommand c = new DeleteNodeCommand(path);
        return getStringResponse(c);
    }

    @ShellMethod("Creates a node for the given path")
    public String newNode(String path,@ShellOption(defaultValue="nt:unstructured")String primaryType) throws IOException {
        HttpRequestCommand c = new CreateNodeCommand(path,primaryType);
        return getStringResponse(c);
    }
}
