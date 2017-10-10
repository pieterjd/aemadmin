package com.pieterjd.aemadmin.command.crx.property;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 26/06/2017.
 */
public class DeletePropertyCommand extends PropertyCommand{

    public DeletePropertyCommand(String path, String propertyName, String propertyValue) {
        super(path, propertyName, propertyValue);
    }



    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(getPropertyName()+"@Delete",getPropertyValue()));
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
}
