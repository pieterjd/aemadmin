package com.pieterjd.aemadmin.command.jcr.property;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 26/06/2017.
 */
public class DeletePropertyCommand extends PropertyCommand{

    public DeletePropertyCommand(String path, String propertyName) {
        super(path, propertyName, "");
    }



    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getDeletePropertyHttpRequest(getPath(),getPropertyName());
    }
}
