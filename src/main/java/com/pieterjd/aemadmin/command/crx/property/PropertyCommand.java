package com.pieterjd.aemadmin.command.crx.property;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Do something with a property of a crx node.
 */
public abstract class PropertyCommand extends CrxCommand{

    private String propertyName;
    private String propertyValue;

    public PropertyCommand(String path,String propertyName,String propertyValue) {
        super(path);
        setPropertyName(propertyName);
        setPropertyValue(propertyValue);
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("propertyName",getPropertyName())
                .append("propertyValue",getPropertyValue());
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }


}
