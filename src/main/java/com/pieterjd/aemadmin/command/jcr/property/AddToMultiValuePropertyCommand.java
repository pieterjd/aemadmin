package com.pieterjd.aemadmin.command.jcr.property;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Add a value to a multivalue property.
 */
public class AddToMultiValuePropertyCommand extends UpdateMultiValuePropertyCommand{


    public AddToMultiValuePropertyCommand(String path, String propertyName, String propertyValue) {
        this(path, propertyName, propertyValue,null);
    }

    public AddToMultiValuePropertyCommand(String path, String propertyName, String propertyValue, String propertyType) {
        super(path, propertyName, propertyValue, propertyType);
    }


    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getAddToMultiValuePropertyHttpRequest(getPath(),getPropertyName(),getPropertyValue(),getPropertyType());
    }
}
