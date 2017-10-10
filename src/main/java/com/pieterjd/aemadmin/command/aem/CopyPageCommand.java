package com.pieterjd.aemadmin.command.aem;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 27/06/2017.
 */
public class CopyPageCommand extends CrxCommand {
    private String destination;
    public CopyPageCommand(String path, String destination) {
        super(path);
        setDestination(destination);
    }



    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(":operation","copy"));
        params.add(new BasicNameValuePair(":applyTo",getPath()));
        params.add(new BasicNameValuePair(":dest",getDestination()));
        try {
            result = getAuthenticatedPostRequestBuilder(getPath())
                   .setEntity(new UrlEncodedFormEntity(params))
                   .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*
    @Override
    public String toString() {
        return getToStringBuilder().build();
    }
    */
}
