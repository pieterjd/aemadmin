package com.pieterjd.aemadmin.command.aem;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
public class CopyPageCommand extends JcrCommand {
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
        params.add(new BasicNameValuePair("cmd","copyPage"));
        params.add(new BasicNameValuePair("srcPath",getPath()));
        params.add(new BasicNameValuePair("destParentPath",getDestination()));
        try {
            result = getAuthenticatedPostRequestBuilder("/bin/wcmcommand")
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
                .append("source",getPath())
                .append("destination",getDestination());
    }
}
