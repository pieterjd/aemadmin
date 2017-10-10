package com.pieterjd.aemadmin.command;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by pdrouill on 21/06/2017.
 */
public class LoginCommand extends HttpRequestCommand {




    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("baseUrl",getBaseUrl())
                .append("port",getPort())
                .append("username",getUserName());
    }

    public HttpUriRequest getRequest() {
        HttpUriRequest result = null;
        try {
            result = RequestBuilder.post()
                    .setUri(new URI(getBaseUrl() + ":" + getPort()))
                    .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()).getBytes()))


                    .build();
        }
        catch(URISyntaxException e){
            result = null;
        }
        return result;
    }
}
