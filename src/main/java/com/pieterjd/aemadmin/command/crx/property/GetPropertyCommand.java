package com.pieterjd.aemadmin.command.crx.property;


import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetPropertyCommand extends PropertyCommand {
    public GetPropertyCommand(String path, String propertyName) {
        super(path, propertyName, null);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder(getPath()+".json").build();
    }
    public String getPropertyValue(){
        try {
            return getHttpResponseAsJSON().getString(getPropertyName());
        } catch (IOException e) {

        }
        return null;
    }
}
