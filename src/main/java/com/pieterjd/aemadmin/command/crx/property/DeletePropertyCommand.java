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

    public DeletePropertyCommand(String path, String propertyName) {
        super(path, propertyName, "");
    }



    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getDeletePropertyHttpRequest(getPath(),getPropertyName());
    }
}
