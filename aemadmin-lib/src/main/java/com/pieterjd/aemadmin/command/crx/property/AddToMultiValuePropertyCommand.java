package com.pieterjd.aemadmin.command.crx.property;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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
    protected List<NameValuePair> getRequestParameters() {
        List<NameValuePair> params = new ArrayList<>();
        if(getPropertyType() != null){
            params.add(new BasicNameValuePair(getPropertyName()+"@TypeHint",getPropertyType()+"[]"));
        }
        params.add(new BasicNameValuePair(getPropertyName()+"@Patch","true"));
        params.add(new BasicNameValuePair(getPropertyName(),"+"+getPropertyValue()));
        return params;
    }


}
