package com.pieterjd.aemadmin.command.crx.property;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class UpdateMultiValuePropertyCommand extends PropertyCommand{
    private String propertyType;

    public UpdateMultiValuePropertyCommand(String path,String propertyName,String propertyValue) {
        this(path,propertyName,propertyValue,"String");
    }

    public UpdateMultiValuePropertyCommand(String path,String propertyName,String propertyValue,String propertyType) {
        super(path,propertyName,propertyValue);
        setPropertyType(propertyType);
    }
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    protected abstract List<NameValuePair> getRequestParameters();

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        List<NameValuePair> params = getRequestParameters();
        //cf https://sling.apache.org/documentation/bundles/manipulating-content-the-slingpostservlet-servlets-post.html#patch

        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder(getPath())
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("multiValuePropertyName",getPropertyName())
                .append("addedPropertyValue",getPropertyValue());
    }
}
