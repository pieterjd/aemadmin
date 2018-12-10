package com.pieterjd.aemadmin.service;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.node.CreateNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;

import java.io.IOException;

public class NodeServiceImpl implements NodeService {

    private String getStringResponse(HttpRequestCommand c){
        c.execute();
        return Boolean.toString(c.isSuccessfullyExecuted());

    }

    @Override
    public String getNode(String path) throws IOException {
        HttpRequestCommand c = new GetNodeCommand(path);
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }


    @Override
    public String deleteNode(String path) throws IOException {
        HttpRequestCommand c = new DeleteNodeCommand(path);
        return getStringResponse(c);
    }


    @Override
    public String newNode(String path, String primaryType) throws IOException {
        HttpRequestCommand c = new CreateNodeCommand(path,primaryType);
        return getStringResponse(c);
    }
}
