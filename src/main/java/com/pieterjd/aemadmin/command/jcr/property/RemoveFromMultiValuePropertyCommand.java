package com.pieterjd.aemadmin.command.jcr.property;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Add a value to a multivalue property.
 */
public class RemoveFromMultiValuePropertyCommand extends UpdateMultiValuePropertyCommand{


    public RemoveFromMultiValuePropertyCommand(String path, String propertyName, String propertyValue) {
        super(path, propertyName, propertyValue);
    }


    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getRemoveFromMultiValuePropertyHttpRequest(getPath(),getPropertyName(),getPropertyValue());
    }
}
