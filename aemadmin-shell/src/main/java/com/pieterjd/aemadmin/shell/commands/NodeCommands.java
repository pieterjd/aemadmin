package com.pieterjd.aemadmin.shell.commands;


import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class NodeCommands {
    @Autowired
    private NodeService service;
    @ShellMethod("Returns a node for the given path")
    public String getNode(String path) throws IOException {
       return service.getNode(path);
    }

    @ShellMethod("Deletes a node for the given path")
    public String deleteNode(String path) throws IOException {
        return service.deleteNode(path);
    }

    @ShellMethod("Creates a node for the given path")
    public String newNode(String path,@ShellOption(defaultValue="nt:unstructured")String primaryType) throws IOException {
       return service.newNode(path,primaryType);
    }
}
