package com.pieterjd.aemadmin.command.crx.property;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
