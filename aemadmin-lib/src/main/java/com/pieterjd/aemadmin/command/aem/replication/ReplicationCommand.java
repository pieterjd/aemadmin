package com.pieterjd.aemadmin.command.aem.replication;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
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
 * Created by pdrouill on 1/09/2017.
 */
public abstract class ReplicationCommand extends HttpRequestCommand {
    private String command;
    private String path;

    public ReplicationCommand(String command, String path) {
        setCommand(command);
        setPath(path);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("path",getPath()));
        params.add(new BasicNameValuePair("cmd",getCommand()));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder("/bin/replicate.json")
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
                .append("command",getCommand())
                .append("page",getPath());
    }
}
