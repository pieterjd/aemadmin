package com.pieterjd.aemadmin.command.crx.property;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
    protected List<NameValuePair> getRequestParameters() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(getPropertyName()+"@Patch","true"));
        params.add(new BasicNameValuePair(getPropertyName(),"-"+getPropertyValue()));
        return params;
    }
}
